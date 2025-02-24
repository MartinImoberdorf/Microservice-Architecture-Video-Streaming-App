package com.martin.movie_streaming_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalaogService {
    public static final String CATALOG_SERVICE="http://movie-catalog-service"; // un unica instancia para acceder a nuestros videos
    @Autowired
    private RestTemplate restTemplate;

    public String getMoviePath(Long movieInfoId){
        var response = restTemplate.getForEntity(CATALOG_SERVICE +"/movie-info/find-path-by-id/{movieInfoId}", String.class, movieInfoId);
        return response.getBody();
    }
}
