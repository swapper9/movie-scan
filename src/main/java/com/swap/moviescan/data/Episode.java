package com.swap.moviescan.data;

import lombok.Data;

@Data
public class Episode {
    private int seasonNumber;
    private int episodeNumber;
    private String nameRu;
    private String nameEn;
    private String synopsis;
    private String releaseDate;
}
