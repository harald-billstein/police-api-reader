package se.harbil.policeapireader.client;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import se.harbil.policeapireader.model.PoliceEventResponse;

/**
 * The PoliceEventClient class is responsible for making HTTP requests to an external event API and
 * retrieving a list of PoliceEventResponse objects in response.
 */

@Component
@RequiredArgsConstructor
public class PoliceEventClient {

    @Qualifier("policeEventClient")
    private final WebClient webClient;

    /**
     * Makes an HTTP GET request to the external event API and retrieves a list of PoliceEventResponse objects.
     *
     * @return A list of PoliceEventResponse objects representing the retrieved police events.
     */

    public List<PoliceEventResponse> call() {
        PoliceEventResponse[] response = webClient.get()
            .retrieve()
            .bodyToMono(PoliceEventResponse[].class)
            .block();
        return response == null ? null : Arrays.asList(response);
    }
}
