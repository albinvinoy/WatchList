package com.movie.watchList.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsResponse {

    private boolean adult;
    private Integer id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "release_date")
    private String releaseDate;
    private String status;
}
