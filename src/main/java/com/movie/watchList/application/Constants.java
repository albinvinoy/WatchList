package com.movie.watchList.application;

public interface Constants {

    interface API {
        String BASE_PATH = "https://api.themoviedb.org/3";
        String MOVIE_SEARCH = "/search/movie";
        String TV_SHOW_SEARCH = "/search/tv";
    }

    interface Types {
        String MOVIE = "movie";
        String TV_SHOW = "tv";
    }

}
