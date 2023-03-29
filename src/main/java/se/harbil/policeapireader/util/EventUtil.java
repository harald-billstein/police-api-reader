package se.harbil.policeapireader.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.model.PoliceEventModel;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventUtil {

    public List<PoliceEventModel> checkIfThereAnyNewEvents(List<PoliceEventModel> newPoliceEvents,
                                                           Optional<PoliceEventModel> latestEvent) {
        return latestEvent.map(policeEventModel -> newPoliceEvents.stream()
                        .filter(newPoliceEvent -> newPoliceEvent.getId() > policeEventModel.getId())
                        .toList())
                .orElse(newPoliceEvents);
    }
}
