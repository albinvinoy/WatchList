package com.movie.watchList.config;

import com.movie.watchList.application.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfiguration {

    @Bean(name = "theMovieDB")
    public WebClient movieDbApi() {
        return WebClient.builder()
                .baseUrl(Constants.API.BASE_PATH)
                .build();
    }

}
