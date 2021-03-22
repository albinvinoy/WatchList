package com.movie.watchList.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class MovieApplication {

    private String queryDescription;
    private String type;

}
