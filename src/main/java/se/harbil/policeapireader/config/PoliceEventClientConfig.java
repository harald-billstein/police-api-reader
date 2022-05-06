package se.harbil.policeapireader.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PoliceEventClientConfig {

    private final String apiUrl;
    private final String extendedInfoBaseUrl;

    public PoliceEventClientConfig(@Value("${POLICE_API_URL}") String apiUrl,
        @Value("${POLICE_EXTENDED_BASE_URL}") String extendedInfoBaseUrl) {
        this.apiUrl = apiUrl;
        this.extendedInfoBaseUrl = extendedInfoBaseUrl;
    }

    @Bean
    @Qualifier("policeEventClient")
    public WebClient createPoliceEventClient() {
        return WebClient.builder()
            .baseUrl(apiUrl)
            .build();
    }

    @Bean
    @Qualifier("policeEventExtendedInfoClient")
    public WebClient createPoliceEventExtendedInfoClient() {
        return WebClient.builder()
            .baseUrl(extendedInfoBaseUrl)
            .build();
    }
}
