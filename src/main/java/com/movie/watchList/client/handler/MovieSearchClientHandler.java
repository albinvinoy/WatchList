package com.movie.watchList.client.handler;

import com.movie.watchList.client.MovieSearchClient;
import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.client.MovieSearchClientResponse;
import com.movie.watchList.model.controller.SuggestedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieSearchClientHandler {

    @Autowired
    private final MovieSearchClient movieSearchClient;

    public Flux<SuggestedResponse> getDetails(MovieApplication movieApplication) {
        return Flux.just(movieApplication)
                .map(this::fetch)
                .flatMap(this::transform)
                .onErrorReturn(SuggestedResponse.builder().build());

    }

    private Mono<MovieSearchClientResponse> fetch(MovieApplication movieApplication) {

        return movieSearchClient
                .getAllMovieSearchResults(movieApplication);
    }

    private Flux<SuggestedResponse> transform(Mono<MovieSearchClientResponse> response) {
        return response
                .log()
                .flatMapIterable(MovieSearchClientResponse::getMovieSearchResults)
                .map(m -> SuggestedResponse.builder()
                        .id(String.valueOf(m.getId()))
                        .image(m.getPosterPath())
                        .name(m.getTitle())
                        .rating(m.getAverageVote())
                        .releaseDate(m.getReleaseDate())
                        .summary(m.getOverview())
                        .build())
                .log();
    }

}
