package com.swap.moviescan.data;

import lombok.Data;

import java.util.List;

@Data
public class FilmSearchResponse {
    private String keyword;
    private int pagesCount;
    private int searchFilmsCountResult;
    private List<FilmSearchResponseFilms> films;
}
