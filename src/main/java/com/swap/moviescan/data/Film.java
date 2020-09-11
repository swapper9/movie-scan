package com.swap.moviescan.data;

import lombok.Data;

import java.util.List;

@Data
public class Film {
    private int filmId;
    private String nameRu;
    private String nameEn;
    private String type;
    private String year;
    private String description;
    private String filmLength;
    private List<Country> countries;
    private List<Genre> genres;
    private String rating;
    private int ratingVoteCount;
    private String posterUrl;
    private String posterUrlPreview;
}
