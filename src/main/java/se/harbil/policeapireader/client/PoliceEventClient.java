package se.harbil.policeapireader.client;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import se.harbil.policeapireader.model.PoliceEventResponse;

@Service
public class PoliceEventClient {

    private final WebClient webClient;

    public PoliceEventClient(@Qualifier("policeEventClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<PoliceEventResponse> call() {
        PoliceEventResponse[] response = webClient.get()
            .retrieve()
            .bodyToMono(PoliceEventResponse[].class)
            .block();
        return response == null ? null : Arrays.asList(response);
    }

}
