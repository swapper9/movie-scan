package com.swap.moviescan;

import com.swap.moviescan.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private KinoPoiskApi kinoPoiskApi;

    @GetMapping(value = "/searchByKeyword")
    public ResponseEntity<SearchByKeywordResponse> searchByKeyword(
      @RequestParam(value = "keyword") String keyword,
      @RequestParam(value = "page") String page) {
        return kinoPoiskApi.searchByKeyword(keyword, page);
    }

    @GetMapping(value = "/films/{id}")
    public ResponseEntity<Film> filmsById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findFilmsById(id);
    }

    @GetMapping(value = "/films/like/{id}")
    public ResponseEntity<List<String>> filmsLikeId(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findFilmsLikeByFilmId(id);
    }

    @GetMapping(value = "/films/{id}/frames")
    public ResponseEntity<FilmFrameResponse> filmsFramesById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findFilmsFramesByFilmId(id);
    }

    @GetMapping(value = "/films/{id}/videos")
    public ResponseEntity<VideoResponse> filmsVideosById(
        @PathVariable(value = "id") int id) {
        return kinoPoiskApi.findVideosByFilmId(id);
    }

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
