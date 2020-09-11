package com.swap.moviescan;

import com.swap.moviescan.data.Film;
import com.swap.moviescan.data.SearchByKeywordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
public class KinoPoiskApi {

    @Value("${kinopoisk.api.key}")
    private String kinopoiskApiKey;

    @Value("${kinopoisk.api.url}")
    private String kinopoiskApiUrl;

    @Value("${kinopoisk.api.search-by-keyword}")
    private String searchByKeyword;

    @Value("${kinopoisk.api.films}")
    private String films;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Parser parser;

    public ResponseEntity<SearchByKeywordResponse> searchByKeyword(String keyword, String page) {
        HttpHeaders headers = getHttpHeaders();
        URI uri = UriComponentsBuilder
                .fromHttpUrl(kinopoiskApiUrl + searchByKeyword)
                .queryParam("keyword", keyword)
                .queryParam("page", page)
                .build().encode().toUri();
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, SearchByKeywordResponse.class);
    }

    public ResponseEntity<Film> filmsById(int id) {
        HttpHeaders headers = getHttpHeaders();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + films + id)
            .build().encode().toUri();
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, Film.class);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY", kinopoiskApiKey);
        headers.setContentType(MediaType.valueOf("application/json; charset=utf-8"));
        headers.setAccept(Collections.singletonList(MediaType.valueOf("application/json; charset=utf-8")));
        return headers;
    }

    public ResponseEntity<List<String>> filmsLikeById(int id) {
        return parser.scanPageForRecommendations("https://kinopoisk.ru/film/" + id + "/like");
    }
}
