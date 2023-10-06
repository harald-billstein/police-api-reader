package se.harbil.policeapireader.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.repository.PoliceEventRepository;

/**
 * The EventUtil class provides utility methods for handling police event data.
 * It is responsible for checking if there are any new police events in a given list by comparing them with events
 * already stored in a repository.
 */
@Slf4j
@Component
@AllArgsConstructor
public class EventUtil {

    private final PoliceEventRepository policeEventRepository;

    /**
     * Checks if there are any new police events in the provided list.
     *
     * @param newPoliceEvents A list of PoliceEventModel objects representing new police events.
     * @return A list of PoliceEventModel objects that are not already present in the repository, indicating new events.
     */
    public List<PoliceEventModel> checkIfThereAnyNewEvents(final List<PoliceEventModel> newPoliceEvents) {
        return newPoliceEvents.stream()
            .filter(policeEventModel ->
                !policeEventRepository.existsByIdAndDatetime(policeEventModel.getId(), policeEventModel.getDatetime()))
            .toList();
    }
}
