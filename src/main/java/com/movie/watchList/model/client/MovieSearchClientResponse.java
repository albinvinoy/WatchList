package com.movie.watchList.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class MovieSearchClientResponse {

    private int page;
    @JsonProperty(value = "results")
    private List<MovieSearchResult> movieSearchResults;
    @JsonProperty(value = "total_results")
    private int totalResults;
    @JsonProperty(value = "total_pages")
    private int totalPages;

}
