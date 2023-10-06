package se.harbil.policeapireader.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.client.PoliceEventClient;
import se.harbil.policeapireader.exception.PoliceEventClientException;
import se.harbil.policeapireader.mapper.PoliceEventModelMapper;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.model.PoliceEventResponse;

/**
 * The PoliceEventService class is responsible for calling an external event API and mapping the responses to
 * a list of PoliceEventModel objects.
 */

@Slf4j
@Service
@AllArgsConstructor
public class PoliceEventService {

    private final PoliceEventClient policeEventClient;
    private final PoliceEventModelMapper policeEventModelMapper;

    /**
     * Calls the external event API and maps the responses to a list of PoliceEventModel objects.
     *
     * @return A list of PoliceEventModel objects representing the retrieved police events.
     * @throws PoliceEventClientException If there is an error while calling the external event API.
     */

    public List<PoliceEventModel> call() {
        try {
            log.info("Trying to call event API");
            List<PoliceEventResponse> response = policeEventClient.call();
            Objects.requireNonNull(response);
            List<PoliceEventModel> policeEventModels = policeEventModelMapper.map(response);
            log.info("Successfully called event API");
            return policeEventModels;
        } catch (Exception e) {
            log.warn("Failed to call event API, got error: {}", e.getMessage());
            throw new PoliceEventClientException(e);
        }
    }
}
