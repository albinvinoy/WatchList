package com.movie.watchList.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
@Builder
public class TvSearchClientResponse {

    private int page;
    @JsonProperty(value = "results")
    private List<TvSearchResult> tvSearchResult;
    @JsonProperty(value = "total_results")
    private int totalResults;
    @JsonProperty(value = "total_pages")
    private int totalPages;
}
