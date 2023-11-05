package se.harbil.policeapireader.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The PoliceEventClientConfig class is a Spring configuration class responsible for creating instances of
 * WebClient for interacting with the police event API and its extended information API.
 */
@Configuration
@RequiredArgsConstructor
public class PoliceEventClientConfig {

    @Value("${POLICE_API_URL}")
    private final String apiUrl;

    /**
     * Creates and configures a WebClient bean for interacting with the police event API.
     *
     * @return An instance of WebClient configured with the base URL for the police event API.
     */
    @Bean
    @Qualifier("policeEventClient")
    @SneakyThrows
    public WebClient createPoliceEventClient() {
        return WebClient.builder()
            .baseUrl(apiUrl)
            .build();
    }

}
