package com.movie.watchList.config;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDbConfiguration {

    @Value("${movieDb.api.apiKey}")
    private String apiKey;

}
