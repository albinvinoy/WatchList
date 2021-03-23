package com.movie.watchList.client.handler;

import com.movie.watchList.client.TvShowSearchClient;
import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.client.TvSearchClientResponse;
import com.movie.watchList.model.controller.SuggestedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class TvShowSearchClientHandler {

    @Autowired
    private final TvShowSearchClient tvShowSearchClient;

    public Flux<SuggestedResponse> getDetails(MovieApplication movieApplication) {

        return Flux.just(movieApplication)
                .map(this::fetch)
                .flatMap(this::transform);

    }

    private Flux<SuggestedResponse> transform(Mono<TvSearchClientResponse> response) {

        return response
                .flatMapIterable(TvSearchClientResponse::getTvSearchResult)
                .map(r -> SuggestedResponse.builder()
                        .id(String.valueOf(r.getId()))
                        .summary(r.getOverview())
                        .releaseDate(r.getReleaseDate())
                        .rating(r.getPopularity())
                        .name(r.getTitle())
                        .image(r.getPosterPath())
                        .build());

    }

    private Mono<TvSearchClientResponse> fetch(MovieApplication movieApplication) {
        return tvShowSearchClient.retrieveTvShowRecommendation(movieApplication);
    }

}
