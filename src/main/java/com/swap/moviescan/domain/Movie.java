package com.swap.moviescan.domain;

import lombok.Data;

@Data
public class Movie {
  private int id;
  private String url;
  private String title_russian;
  private String title_original;
  private String year;
  private String poster_small;
  private String poster_big;
}

