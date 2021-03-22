package com.movie.watchList.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class TvSearchResult {

    @JsonProperty(value = "poster_path")
    private String posterPath;
    private String overview;
    @JsonProperty(value = "first_air_date")
    private String releaseDate;
    @JsonProperty(value = "genre_ids")
    private List<Integer> genreIds;
    private int id;
    @JsonProperty(value = "original_name")
    private String title;
    @JsonProperty(value = "original_language")
    private String language;
    private double popularity;
    @JsonProperty(value = "vote_average")
    private double averageVote;

}
