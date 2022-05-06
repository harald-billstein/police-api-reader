package se.harbil.policeapireader.service;

import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.client.PoliceEventClient;
import se.harbil.policeapireader.exception.PoliceEventClientException;
import se.harbil.policeapireader.mapper.PoliceEventModelMapper;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.model.PoliceEventResponse;

@Slf4j
@Service
public class PoliceEventService {

    private final PoliceEventClient policeEventClient;
    private final PoliceEventModelMapper policeEventModelMapper;

    public PoliceEventService(PoliceEventClient policeEventClient,
        PoliceEventModelMapper policeEventModelMapper) {
        this.policeEventClient = policeEventClient;
        this.policeEventModelMapper = policeEventModelMapper;
    }

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
