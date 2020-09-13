package com.swap.moviescan.data;

import lombok.Data;

@Data
public class FilmStudioResponse {
    private String production;
    private String filming;
    private String imageFormat;
    private String camera;
    private String copyFormat;
    private String filmingFormat;
    private String image;
    private String language;
    private ProductionStudios productionStudios;
    private SpecialEffectsStudios specialEffectsStudios;
    private DubbingStudios dubbingStudios;
    private RentStudios rentStudios;
}
