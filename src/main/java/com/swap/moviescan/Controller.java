package com.swap.moviescan;

import com.swap.moviescan.data.KinoPoiskApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private KinoPoiskApi api;

    @GetMapping(value = "/searchByKeyword")
    public ResponseEntity<KinoPoiskApiResponse> searchByKeyword(@RequestParam(value = "keyword") String keyword) {
        return api.scanMovie(keyword);
    }
}
