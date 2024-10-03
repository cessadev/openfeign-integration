package com.cessadev.api_integration_and_tests.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;
import com.cessadev.api_integration_and_tests.service.IWeatherService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherController {

    private final IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    // Endpoint v1:
    /* 
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponseDTO> getWeatherByCity(@PathVariable String city) {
        WeatherResponseDTO weatherResponseDTO = weatherService.getWeather(city);
        return ResponseEntity.ok(weatherResponseDTO);
    }
    */

    /**
     * Endpoint v2:
     * http://localhost:8080/api/weather/{city}
     * 
     * Endpoint to obtain the weather of a specific city.
     *
     * @param city Name of the city. It cannot be empty and must contain only alphabetic characters.
     * @return A ResponseEntity object with weather information for the requested city.
     */
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponseDTO> getWeatherByCity(
            @PathVariable @NotBlank(message = "City name cannot be empty") 
            @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City name should contain only alphabetical characters") String city) {
        
        try {
            // Service call for weather
            WeatherResponseDTO weatherResponseDTO = weatherService.getWeather(city);

            // Additional validation to ensure that the response has been obtained.
            if (weatherResponseDTO == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found for the city: " + city);
            }

            // Successful response
            return ResponseEntity.ok(weatherResponseDTO);

        } catch (IllegalArgumentException exception) {
            // Handling invalid argument errors
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid city name: " + city, exception);
        } catch (Exception exception) {
            // Capture any other unexpected errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", exception);
        }
    }
    
}
