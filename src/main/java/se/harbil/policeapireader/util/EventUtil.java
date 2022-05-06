package se.harbil.policeapireader.util;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.exception.EventUtilException;
import se.harbil.policeapireader.model.PoliceEventModel;

@Slf4j
@Service
public class EventUtil {

    public List<PoliceEventModel> checkIfThereAnyNewEvents(List<PoliceEventModel> newPoliceEvents,
        List<PoliceEventModel> localDataBaseEvents) {

        try {
            log.info("Trying to compare new events with stored events");
            List<PoliceEventModel> unsavedPoliceEvents = new ArrayList<>();
            for (PoliceEventModel newPoliceEvent : newPoliceEvents) {
                boolean contains = localDataBaseEvents.contains(newPoliceEvent);

                if (!contains) {
                    unsavedPoliceEvents.add(newPoliceEvent);
                }
            }
            log.info("Successfully compared new events with stored events, found: {} new events",
                unsavedPoliceEvents.size());
            return unsavedPoliceEvents;

        } catch (Exception e) {
            log.warn("Failed when comparing new events with stored events, got error: {}",
                e.getMessage());
            throw new EventUtilException(e);
        }

    }

}
