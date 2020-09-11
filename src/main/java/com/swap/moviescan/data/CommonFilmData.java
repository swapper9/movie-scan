package com.swap.moviescan.data;

import lombok.Data;

import java.util.List;

@Data
public class CommonFilmData {
    private int filmId;
    private String nameRu;
    private String nameEn;
    private String webUrl;
    private String posterUrl;
    private String posterUrlPreview;
    private String year;
    private String filmLength;
    private String slogan;
    private String description;
    private String type;
    private String ratingMpaa;
    private int ratingAgeLimits;
    private String premiereRu;
    private String distributors;
    private String premiereWorld;
    private String premiereDigital;
    private String premiereWorldCountry;
    private String premiereDvd;
    private String premiereBluRay;
    private String distributorRelease;
    private List<Country> countries;
    private List<Genre> genres;
    private List<Season> seasons;
}
