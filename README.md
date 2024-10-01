# API Integration and Tests Demo

Demo of a REST API built with Spring Boot that consumes a third-party weather data service. The application fetches weather data for a city and provides a REST endpoint for clients to access this information.

### Features

- **Integration with external API**: Consumes the OpenWeatherMap API using a Feign client, which provides weather data for a specified city.
- **Unit and integration testing**: Includes automated tests using JUnit and Testcontainers, ensuring the quality and stability of the application.
- **Resilience and error handling**: Implements the Circuit Breaker pattern using Resilience4j to handle failures in communication with the external API.
- **Flexible configuration**: Property files for production and testing configurations.

### Project Structure

- **Controller**: Defines the REST interface to interact with the service.
- **Service**: Contains business logic and manages integration with the OpenWeatherMap external API.
- **Client**: Implementation of the Feign client to make HTTP calls.
- **Model**: Representation of the JSON provided by the weather data service at openweathermap.org.
- **Tests**: Contains unit and integration tests to ensure code quality.

### Requirements

- JDK 17
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your_username/weather-api-integration-tests.git
   ```
2. Navigate to the project directory:
   ```bash
   cd weather-api-integration-tests
   ```
3. Run the project using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

### Usage

Once the application is running, you can make requests to the exposed endpoints to retrieve weather data.

### Contributions

Contributions are welcome. Feel free to open an *issue* or submit a *pull request*.
