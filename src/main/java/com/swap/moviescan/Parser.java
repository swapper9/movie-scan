package com.swap.moviescan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Parser {

    @Value("${site.url}")
    private String siteUrl;

    public ResponseEntity<List<String>> scanPageForRecommendations(String parentUrl) {
        List<String> likeList = getElements(parentUrl, "http://www.google.com");
        List<String> recOfRecsList = new ArrayList<>();
        for (String recUrl : likeList) {
            getElements(siteUrl + recUrl + "like/", parentUrl);
            recOfRecsList.addAll(likeList);
        }
        return new ResponseEntity<>(recOfRecsList, HttpStatus.OK);
    }

    private List<String> getElements(String url, String referrer) {
        Document doc;
        Elements elements;
        List<String> list = null;
        try {
            doc = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer(referrer)
                .get();
            elements = doc
                .select(".block_left_pad")
                .select(".news")
                .select(".all");
            list = elements.stream()
                .filter(e -> !e.attr("href").isBlank())
                .map(el -> el.attr("href"))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
