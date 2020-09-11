package com.swap.moviescan;

import com.swap.moviescan.data.KinoPoiskApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class KinoPoiskApi {

    @Value("${kinopoisk.api.key}")
    private String kinopoiskApiKey;

    @Value("${kinopoisk.api.url}")
    private String kinopoiskApiUrl;

    @Value("${kinopoisk.api.search-by-keyword}")
    private String searchByKeyword;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<KinoPoiskApiResponse> scanMovie(String keyword) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY", kinopoiskApiKey);
        headers.setContentType(MediaType.valueOf("application/json; charset=utf-8"));
        headers.setAccept(Collections.singletonList(MediaType.valueOf("application/json; charset=utf-8")));
        URI uri = UriComponentsBuilder
                .fromHttpUrl(kinopoiskApiUrl + searchByKeyword)
                .queryParam("keyword", keyword)
                .queryParam("page", "1")
                .build().encode().toUri();
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<KinoPoiskApiResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity, KinoPoiskApiResponse.class);
        return response;
    }
}
