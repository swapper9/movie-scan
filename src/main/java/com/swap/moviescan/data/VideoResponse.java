package com.swap.moviescan.data;

import lombok.Data;

@Data
public class VideoResponse {
    private VideoResponseTrailers trailers;
    private VideoResponseTeasers teasers;
}
