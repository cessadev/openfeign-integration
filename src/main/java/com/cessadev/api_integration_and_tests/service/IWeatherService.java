package com.cessadev.api_integration_and_tests.service;

import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;

public interface IWeatherService {
    WeatherResponseDTO getWeather(String city);
}
