package com.swap.moviescan;

import com.swap.moviescan.data.Film;
import com.swap.moviescan.data.SearchByKeywordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return kinoPoiskApi.filmsById(id);
    }
}
