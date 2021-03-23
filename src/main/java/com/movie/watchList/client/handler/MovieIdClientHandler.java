package com.movie.watchList.client.handler;

import com.movie.watchList.client.MovieIdClient;
import com.movie.watchList.model.client.MovieDetailsResponse;
import com.movie.watchList.model.controller.incoming.CreateItemRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MovieIdClientHandler {

    @Autowired
    private MovieIdClient movieIdClient;

    public Mono<MovieDetailsResponse> getDetails(CreateItemRequest createItemRequest) {
        return Mono.just(createItemRequest)
                .map(this::fetch)
                .flatMap(this::transform);
    }

    private Mono<MovieDetailsResponse> fetch(CreateItemRequest createItemRequest) {
        return movieIdClient.getMovieDetails(createItemRequest)
                .onErrorMap(throwable -> {
                            log.error("An error {}", throwable.getMessage());
                            throw new RuntimeException("An error occurred when getting movie info");
                        }
                );
    }

    private Mono<MovieDetailsResponse> transform(Mono<MovieDetailsResponse> movieDetailsResponse) {
        return movieDetailsResponse;
    }

}
