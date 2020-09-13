package com.swap.moviescan.data;

import lombok.Data;

@Data
public class DigitalReleaseResponse {
    private int page;
    private int total;
    private DigitalReleaseItem releases;
}
