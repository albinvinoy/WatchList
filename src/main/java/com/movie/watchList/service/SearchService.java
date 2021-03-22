package com.movie.watchList.service;

import com.movie.watchList.handler.SearchHandler;
import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.controller.SuggestedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    @Autowired
    private final SearchHandler searchHandler;

    public Flux<SuggestedResponse> getMovieSearchResults(MovieApplication movieApplication) {
        return Flux.just(movieApplication)
                .log()
                .publishOn(Schedulers.boundedElastic())
                .flatMap(n -> searchHandler.searchHandler(movieApplication))

//                .doOnNext(e -> System.out.println(Thread.currentThread().getName()))
                .log("Called movie search handler")
                .doOnError(throwable -> {
                    log.error("Error occurred", throwable.getCause());
                    throwable.printStackTrace();
                })
                .switchIfEmpty(Flux.empty());
    }
}
