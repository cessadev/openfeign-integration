package com.cessadev.api_integration_and_tests.service.implementation;

import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.cessadev.api_integration_and_tests.client.WeatherFeignClient;
import com.cessadev.api_integration_and_tests.model.dto.ErrorResponseDTO;
import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;
import com.cessadev.api_integration_and_tests.service.IWeatherService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class WeatherService implements IWeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherFeignClient weatherFeignClient;

    public WeatherService(WeatherFeignClient weatherFeignClient) {
        this.weatherFeignClient = weatherFeignClient;
    }

    @Override
    @CircuitBreaker(name = "weatherService", fallbackMethod = "fallbackGetWeather")
    public WeatherResponseDTO getWeather(String city) {
        return weatherFeignClient.getWeather(city, apiKey);
    }

    // Fallback method
    public ErrorResponseDTO fallbackGetWeather(String city, Throwable throwable) {
        // Get error message
        String errorMessage = (throwable != null && throwable.getMessage() != null) ? throwable.getMessage()
                : "Unknown error";

        // Handling TimeoutException
        if (throwable instanceof TimeoutException) {
            logger.warn("Timeout while fetching weather data for city: {}", city);
            return new ErrorResponseDTO("Weather data not available due to timeout", city, errorMessage);
        }

        // Handling HttpClientErrorException (errors 4xx)
        else if (throwable instanceof HttpClientErrorException) {
            logger.error("4xx error while fetching weather data for city: {}. Reason: {}", city, errorMessage);
            return new ErrorResponseDTO("Weather data not available due to client error", city, errorMessage);
        }

        // Handling others errors
        else {
            logger.error("Unexpected error while fetching weather data for city: {}. Reason: {}", city, errorMessage);
            return new ErrorResponseDTO("Weather data not available due to unexpected error", city, errorMessage);
        }
    }

    // Example with WebClient (If you need reactive support):

    /*
    
    private final WebClient webClient;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org").build();
    }

    @Override
    @CircuitBreaker(name = "weatherService", fallbackMethod = "fallbackGetWeather")
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