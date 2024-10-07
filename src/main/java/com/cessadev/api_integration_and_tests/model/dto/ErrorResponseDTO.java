package com.cessadev.api_integration_and_tests.model.dto;

public record ErrorResponseDTO(
    String message,
    String city,
    String timestamp,
    String errorDetails
) {
    public ErrorResponseDTO(String message, String city, String errorDetails) {
        this(message, city, java.time.Instant.now().toString(), errorDetails);
    }
}
