package com.cessadev.api_integration_and_tests.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cessadev.api_integration_and_tests.client.WeatherFeignClient;
import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;
import com.cessadev.api_integration_and_tests.service.IWeatherService;

@Service
public class WeatherService implements IWeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final WeatherFeignClient weatherFeignClient;

    public WeatherService(WeatherFeignClient weatherFeignClient) {
        this.weatherFeignClient = weatherFeignClient;
    }

    @Override
    public WeatherResponseDTO getWeather(String city) {
        return weatherFeignClient.getWeather(city, apiKey);
    }

    // Example with WebClient (If you need reactive support):

    /* 

    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org").build();
    }

    public Mono<WeatherResponseDTO> getWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/data/2.5/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponseDTO.class);
    }

     */
    
}