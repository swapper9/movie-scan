package com.swap.moviescan.data;

import lombok.Data;

import java.util.List;

@Data
public class FiltersResponse {
    private List<FiltersResponseGenres> genres;
    private List<FiltersResponseCountries> countries;
}
