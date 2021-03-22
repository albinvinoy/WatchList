package com.movie.watchList.client;

import com.movie.watchList.application.Constants;
import com.movie.watchList.config.MovieDbConfiguration;
import com.movie.watchList.model.MovieApplication;
import com.movie.watchList.model.client.MovieSearchClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieSearchClient {

    @Autowired
    @Qualifier("theMovieDB")
    private WebClient movieDbClient;

    @Autowired
    private final MovieDbConfiguration movieDbConfiguration;

    public Mono<MovieSearchClientResponse> getAllMovieSearchResults(MovieApplication movieApplication) {
        Map<String, String> headerValues = new HashMap<>();
        return movieDbClient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(Constants.API.MOVIE_SEARCH)
                        .queryParam("api_key", movieDbConfiguration.getApiKey())
                        .queryParam("language", "en-US")
                        .queryParam("query", movieApplication.getQueryDescription())
                        .build()
                )
                .headers(httpHeaders -> httpHeaders.setAll(headerValues))
                .retrieve()
                .bodyToMono(MovieSearchClientResponse.class);
    }


}
