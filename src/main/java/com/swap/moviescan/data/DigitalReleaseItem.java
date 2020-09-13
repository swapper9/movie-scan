package com.swap.moviescan.data;

import lombok.Data;

import java.util.List;

@Data
public class DigitalReleaseItem {
    private int filmId;
    private String nameRu;
    private String nameEn;
    private String year;
    private String posterUrl;
    private String posterUrlPreview;
    private List<Country> countries;
    private List<Genre> genres;
    private double rating;
    private int ratingVoteCount;
    private double expectationsRating;
    private int expectationsRatingVoteCount;
    private int duration;
    private String releaseDate;
}
