package com.example.demo.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service(value = "feign")
@ConditionalOnProperty(prefix = "demo", name = "feign", havingValue = "true")
public class OpenWeatherServiceFeignImpl implements OpenWeatherService {
    private final OpenWeatherFeignClient client;

    private final String apiKey;

    public OpenWeatherServiceFeignImpl(OpenWeatherFeignClient client, @Value("${openweathermap.apiKey}") String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    @Override
    public Forecast getForecast(String longitude, String latitude, String units) {
        return client.getForecast(longitude, latitude, units, apiKey);
    }
}