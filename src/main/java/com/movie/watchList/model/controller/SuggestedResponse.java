package com.movie.watchList.model.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuggestedResponse {

    private String name;
    private String summary;
    private String releaseDate;
    private String id;
    private Double rating;
    private String image;

}
