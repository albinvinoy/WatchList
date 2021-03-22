package com.movie.watchList.controller;

import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.controller.SuggestedResponse;
import com.movie.watchList.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("v1/watch")
public class WatchController {

    private final SearchService searchService;

    @GetMapping(value = "/search", produces = "application/json")
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
}
