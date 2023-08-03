package com.example.demo.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {
    private final String BASE_URL;
    private final String BASE_LOCATION_URL;
    public WebClientConfig(@Value("${openweathermap.base.url}") String baseUrl,
                           @Value("${openweathermap.base.location.url}") String baseLocationUrl) {
    this.BASE_URL = baseUrl;
    this.BASE_LOCATION_URL = baseLocationUrl;
    }

    @Bean("weatherClient")
    public OpenWeatherFeignClient getFeignClient(ObjectMapper objectMapper) {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .encoder(new JacksonEncoder(objectMapper))
                .target(OpenWeatherFeignClient.class, BASE_URL);
    }

    @Bean("locationClient")
    public OpenWeatherFeignClient getFeignClientForLocation() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(OpenWeatherFeignClient.class, BASE_LOCATION_URL);
    }
}