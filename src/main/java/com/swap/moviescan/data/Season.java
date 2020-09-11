package com.swap.moviescan.data;

import lombok.Data;

import java.util.List;

@Data
public class Season {
    private int number;
    private List<Episode> episodes;
}
