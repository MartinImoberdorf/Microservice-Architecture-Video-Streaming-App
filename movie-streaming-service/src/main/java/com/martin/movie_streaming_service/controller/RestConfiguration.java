package com.martin.movie_streaming_service.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){ // con este RestTemplate podemos hacer peticiones a nuestros otros servicios
        return new RestTemplate();
    }
}
