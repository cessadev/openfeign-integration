package com.cessadev.api_integration_and_tests.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cessadev.api_integration_and_tests.model.dto.WeatherResponseDTO;

@FeignClient(name = "weather-client", url= "https://api.openweathermap.org")
public interface WeatherFeignClient {

    @GetMapping("/data/2.5/weather")
    WeatherResponseDTO getWeather(@RequestParam("q") String city, @RequestParam("appid") String apiKey);
}
