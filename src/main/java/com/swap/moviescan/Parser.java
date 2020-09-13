package com.swap.moviescan;

import lombok.extern.log4j.Log4j2;
import org.jsoup.HttpStatusException;
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
@Log4j2
public class Parser {

    @Value("${site.url}")
    private String siteUrl;

    public ResponseEntity<List<String>> scanPageForRecommendations(String parentUrl) {
        List<String> likeList = getElements(parentUrl, "http://www.google.com");
        List<String> secondTierLikeList = new ArrayList<>();
        for (String recUrl : likeList) {
            getElements(siteUrl + recUrl + "like/", parentUrl);
            secondTierLikeList.addAll(likeList);
        }
        secondTierLikeList.forEach(s -> s = siteUrl + s);
        return new ResponseEntity<>(secondTierLikeList, HttpStatus.OK);
    }

    private List<String> getElements(String url, String referrer) {
        Document doc;
        Elements elements;
        List<String> list = null;
        try {
            log.info("Getting " + url);
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
        } catch (HttpStatusException e) {
            log.error("Error getting " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
