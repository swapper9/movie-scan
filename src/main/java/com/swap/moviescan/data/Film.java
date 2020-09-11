package com.swap.moviescan.data;

import lombok.Data;

@Data
public class Film {
    private CommonFilmData data;
    private Rating rating;
    private Budget budget;
    private Review review;
    private ExternalId externalId;
    private Images images;
}
