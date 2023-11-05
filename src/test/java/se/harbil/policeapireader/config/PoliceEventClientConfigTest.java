package se.harbil.policeapireader.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

class PoliceEventClientConfigTest {

    public static final String API_URL = "http://api.se";

    @Test
    void createPoliceEventClient() {
        PoliceEventClientConfig config = new PoliceEventClientConfig(API_URL);
        WebClient policeEventClient = config.createPoliceEventClient();

        assertNotNull(policeEventClient);
    }
}