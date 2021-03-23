package com.movie.watchList.client;


import com.movie.watchList.application.Constants;
import com.movie.watchList.config.MovieDbConfiguration;
import com.movie.watchList.model.client.MovieDetailsResponse;
import com.movie.watchList.model.controller.incoming.CreateItemRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MovieIdClient {

    @Autowired
    @Qualifier("theMovieDB")
    private WebClient theMovieDBClient;

    @Autowired
    private MovieDbConfiguration movieDbConfiguration;

    public Mono<MovieDetailsResponse> getMovieDetails(CreateItemRequest createItemRequest) {

        return theMovieDBClient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(Constants.API.MOVIE_DETAILS)
                        .queryParam("api_key", movieDbConfiguration.getApiKey())
                        .queryParam("language", "en-US")
                        .build(createItemRequest.getContentId()))
                .retrieve()
                .bodyToMono(MovieDetailsResponse.class);
    }
}
