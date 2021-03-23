package com.movie.watchList.controller;

import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.controller.SuggestedResponse;
import com.movie.watchList.model.controller.incoming.CreateItemRequest;
import com.movie.watchList.service.CreateContentService;
import com.movie.watchList.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("v1/watch")
public class WatchController {

    private final SearchService searchService;
    private final CreateContentService createContentService;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<SuggestedResponse> searchMovies(
            @RequestParam("keyword") String keyword,
            @RequestParam("type") String type
    ) {

        MovieApplication movieApplication =
                MovieApplication.builder()
                        .queryDescription(keyword)
                        .type(type)
                        .build();

        return searchService.getMovieSearchResults(movieApplication);
    }


    @PostMapping(value = "/saveMovie", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Boolean> addToWatchList(
            @RequestBody() CreateItemRequest createItemRequest
    ) {

        return Mono.just(createItemRequest)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(createContentService::addNewContents);
    }


}
