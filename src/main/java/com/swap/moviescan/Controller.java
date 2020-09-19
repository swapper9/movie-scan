package com.swap.moviescan;

import com.swap.moviescan.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private KinoPoiskApi kinoPoiskApi;

    @Value("${token.url}")
    private String tokenUrl;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyCloakTokenResponse> getToken(@RequestBody UserTokenRequest userTokenRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","password");
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username",userTokenRequest.getUsername());
        map.add("password", userTokenRequest.getPassword());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, KeyCloakTokenResponse.class);
    }

    @GetMapping(value = "/searchByKeyword")
    public ResponseEntity<SearchByKeywordResponse> searchByKeyword(
      @RequestParam(value = "keyword") String keyword,
      @RequestParam(value = "page") String page) {
        return kinoPoiskApi.searchByKeyword(keyword, page);
    }

    @RolesAllowed("user")
    @GetMapping(value = "/films/{id}")
    public ResponseEntity<Film> filmsById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findFilmsById(id);
    }

    @RolesAllowed("user")
    @GetMapping(value = "/films/like/{id}")
    public ResponseEntity<List<String>> filmsLikeId(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findFilmsLikeByFilmId(id);
    }

    @RolesAllowed("user")
    @GetMapping(value = "/films/{id}/frames")
    public ResponseEntity<FilmFrameResponse> filmsFramesById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findFilmsFramesByFilmId(id);
    }

    @RolesAllowed("user")
    @GetMapping(value = "/films/{id}/videos")
    public ResponseEntity<VideoResponse> filmsVideosById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findVideosByFilmId(id);
    }

    @RolesAllowed("user")
    @GetMapping(value = "/films/{id}/studios")
    public ResponseEntity<FilmStudioResponse> filmsStudiosById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findStudiosByFilmId(id);
    }

    @GetMapping(value = "/films/filters")
    public ResponseEntity<FiltersResponse> getFilters() {
        return kinoPoiskApi.getFilters();
    }


    @GetMapping(value = "/searchByFilters")
    public ResponseEntity<FilmSearchResponse> searchByFilters(
        @RequestParam(value = "country", required = false) Integer[] countries,
        @RequestParam(value = "genre", required = false) Integer[] genres,
        @RequestParam(value = "order", required = false) String order,
        @RequestParam(value = "ratingFrom", required = false) Integer ratingFrom,
        @RequestParam(value = "ratingTo", required = false) Integer ratingTo,
        @RequestParam(value = "yearFrom", required = false) Integer yearFrom,
        @RequestParam(value = "yearTo", required = false) Integer yearTo,
        @RequestParam(value = "page", required = false) String page) {
        return kinoPoiskApi.searchByFilters(countries, genres, order, ratingFrom, ratingTo, yearFrom, yearTo, page);
    }

    @GetMapping(value = "/films/top")
    public ResponseEntity<FilmTopResponse> getTopFilms(
        @RequestParam(value = "type", required = false) String type,
        @RequestParam(value = "page", required = false) int page,
        @RequestParam(value = "listId", required = false) int listId) {
        return kinoPoiskApi.getTopFilms(type, page, listId);
    }

    @GetMapping(value = "/films/releases")
    public ResponseEntity<DigitalReleaseResponse> getFilmsReleases(
        @RequestParam(value = "year") int year,
        @RequestParam(value = "month") int month,
        @RequestParam(value = "page", required = false) int page) {
        return kinoPoiskApi.getFilmsReleases(year, month, page);
    }
}
