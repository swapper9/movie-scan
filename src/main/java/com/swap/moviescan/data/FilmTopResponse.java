package com.swap.moviescan.data;

import lombok.Data;

@Data
public class FilmTopResponse {
    private int pagesCount;
    private FilmTopResponseFilms films;
}
