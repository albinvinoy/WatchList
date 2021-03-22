package com.movie.watchList.client;

import com.movie.watchList.application.Constants;
import com.movie.watchList.config.MovieDbConfiguration;
import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.client.TvSearchClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class TvShowSearchClient {

    @Autowired
    @Qualifier("theMovieDB")
    private final WebClient tvSearchClient;

    @Autowired
    private final MovieDbConfiguration movieDbConfiguration;

    public Mono<TvSearchClientResponse> retrieveTvShowRecommendation(MovieApplication application) {

        return tvSearchClient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(Constants.API.TV_SHOW_SEARCH)
                        .queryParam("api_key", movieDbConfiguration.getApiKey())
                        .queryParam("language", "en-US")
                        .queryParam("query", application.getQueryDescription())
                        .build())
                .retrieve()
                .bodyToMono(TvSearchClientResponse.class);


    }


}
