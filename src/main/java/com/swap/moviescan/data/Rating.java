package com.swap.moviescan.data;

import lombok.Data;

@Data
public class Rating {
    private double rating;
    private int ratingVoteCount;
    private double ratingImdb;
    private int ratingImdbVoteCount;
    private String ratingFilmCritics;
    private int ratingFilmCriticsVoteCount;
    private String ratingAwait;
    private int ratingAwaitCount;
    private String ratingRfCritics;
    private int ratingRfCriticsVoteCount;
}
