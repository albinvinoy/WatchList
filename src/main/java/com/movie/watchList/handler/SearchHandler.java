package com.movie.watchList.handler;

import com.movie.watchList.application.Constants;
import com.movie.watchList.handler.client.MovieSearchClientHandler;
import com.movie.watchList.handler.client.TvShowSearchClientHandler;
import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.controller.SuggestedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchHandler {

    @Autowired
    private final MovieSearchClientHandler movieSearchClientHandler;
    @Autowired
    private final TvShowSearchClientHandler tvShowSearchClientHandler;

    public Flux<SuggestedResponse> searchHandler(MovieApplication movieApplication) {

        return getSearchResults(movieApplication)
                .doOnNext(System.out::println);
    }

    public Flux<SuggestedResponse> getSearchResults(MovieApplication movieApplication) {
        String type = movieApplication.getType();
        if (type.equalsIgnoreCase(Constants.Types.MOVIE)) {
            return movieSearchClientHandler.getDetails(movieApplication);
        }
        return tvShowSearchClientHandler.getDetails(movieApplication);
    }
}
