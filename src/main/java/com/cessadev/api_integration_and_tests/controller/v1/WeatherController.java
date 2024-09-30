package com.cessadev.api_integration_and_tests.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;
import com.cessadev.api_integration_and_tests.service.IWeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponseDTO> getWeatherByCity(@PathVariable String city) {
        WeatherResponseDTO weatherResponseDTO = weatherService.getWeather(city);
        return ResponseEntity.ok(weatherResponseDTO);
    }
    
}
