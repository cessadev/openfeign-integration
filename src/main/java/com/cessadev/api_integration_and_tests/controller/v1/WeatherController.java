package com.cessadev.api_integration_and_tests.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;
import com.cessadev.api_integration_and_tests.service.IWeatherService;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    /**
     * Endpoint v1:
     * http://localhost:8080/api/v1/weather/{city}
     * 
     * Endpoint to obtain the weather of a specific city.
     *
     * @param city Name of the city. It cannot be empty and must contain only alphabetic characters.
     * @return A ResponseEntity object with weather information for the requested city.
     */
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponseDTO> getWeatherByCity(@PathVariable String city) {
        WeatherResponseDTO weatherResponseDTO = weatherService.getWeather(city);
        return ResponseEntity.ok(weatherResponseDTO);
    }
    
}
