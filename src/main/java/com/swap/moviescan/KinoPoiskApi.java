package com.swap.moviescan;

import com.swap.moviescan.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KinoPoiskApi {

    @Value("${kinopoisk.api.key}")
    private String kinopoiskApiKey;

    @Value("${kinopoisk.api.url}")
    private String kinopoiskApiUrl;

    @Value("${kinopoisk.api.version}")
    private String kinopoiskApiVersion;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Parser parser;

    public ResponseEntity<SearchByKeywordResponse> searchByKeyword(String keyword, String page) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
                .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/search-by-keyword")
                .queryParam("keyword", keyword)
                .queryParam("page", page)
                .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, SearchByKeywordResponse.class);
    }

    public ResponseEntity<Film> findFilmsById(int id) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/" + id)
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, Film.class);
    }

    public ResponseEntity<List<String>> findFilmsLikeByFilmId(int id) {
        return parser.scanPageForRecommendations("https://kinopoisk.ru/film/" + id + "/like");
    }

    public ResponseEntity<FilmFrameResponse> findFilmsFramesByFilmId(int id) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/" + id + "/frames")
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, FilmFrameResponse.class);
    }

    private HttpEntity createHttpEntity() {
        HttpHeaders headers = getHttpHeaders();
        return new HttpEntity(headers);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY", kinopoiskApiKey);
        headers.setContentType(MediaType.valueOf("application/json; charset=utf-8"));
        headers.setAccept(Collections.singletonList(MediaType.valueOf("application/json; charset=utf-8")));
        return headers;
    }

    public ResponseEntity<VideoResponse> findVideosByFilmId(int id) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/" + id + "/videos")
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, VideoResponse.class);
    }

    public ResponseEntity<FilmStudioResponse> findStudiosByFilmId(int id) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/" + id + "/studios")
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, FilmStudioResponse.class);
    }

    public ResponseEntity<FiltersResponse> getFilters() {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/filters")
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, FiltersResponse.class);
    }

    public ResponseEntity<FilmSearchResponse> searchByFilters(
        Integer[] countries, Integer[] genres, String order, Integer ratingFrom,
        Integer ratingTo, Integer yearFrom, Integer yearTo, String page) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/search-by-filters")
            .queryParam("countries", Arrays.toString(countries))
            .queryParam("genres", Arrays.toString(genres))
            .queryParam("order", order)
            .queryParam("ratingFrom", ratingFrom)
            .queryParam("ratingTo", ratingTo)
            .queryParam("yearFrom", yearFrom)
            .queryParam("yearTo", yearTo)
            .queryParam("page", page)
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, FilmSearchResponse.class);
    }

    public ResponseEntity<FilmTopResponse> getTopFilms(String type, int page, int listId) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/top")
            .queryParam("type", type)
            .queryParam("page", page)
            .queryParam("listId", listId)
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, FilmTopResponse.class);
    }

    public ResponseEntity<DigitalReleaseResponse> getFilmsReleases(int year, int month, int page) {
        HttpEntity entity = createHttpEntity();
        URI uri = UriComponentsBuilder
            .fromHttpUrl(kinopoiskApiUrl + kinopoiskApiVersion + "/films/releases")
            .queryParam("year", year)
            .queryParam("month", month)
            .queryParam("page", page)
            .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, entity, DigitalReleaseResponse.class);
    }
}
