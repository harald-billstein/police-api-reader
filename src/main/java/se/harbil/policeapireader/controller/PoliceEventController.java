package se.harbil.policeapireader.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.kafka.EventProducer;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.service.PoliceEventExtendedInfoService;
import se.harbil.policeapireader.service.PoliceEventRepositoryService;
import se.harbil.policeapireader.service.PoliceEventService;
import se.harbil.policeapireader.util.EventUtil;

@Slf4j
@Service
@AllArgsConstructor
public class PoliceEventController {

    private final PoliceEventService policeEventService;
    private final PoliceEventRepositoryService policeEventRepositoryService;
    private final EventUtil eventUtil;
    private final PoliceEventExtendedInfoService policeEventExtendedInfoService;
    private final EventProducer eventProducer;

    @Scheduled(initialDelay = 60000, fixedRate = 600000)
    public void fetchPoliceEvents() {
        log.info("Trying to fetch police events");
        try {
            List<PoliceEventModel> newPoliceEvents = policeEventService.call();

            List<PoliceEventModel> unsavedPoliceEvents = eventUtil.checkIfThereAnyNewEvents(newPoliceEvents);

            List<PoliceEventModel> eventModelsWithExtendedInfo = policeEventExtendedInfoService.call(unsavedPoliceEvents);

            List<PoliceEventModel> savedPoliceEvents = policeEventRepositoryService.saveAll(
                eventModelsWithExtendedInfo);

            eventProducer.sendEvents(eventModelsWithExtendedInfo);

            log.info("Successfully fetched {} police events and saved {} new events in DB",
                newPoliceEvents.size(), savedPoliceEvents.size());
        } catch (Exception e) {
            log.warn("Failed to fetch police events", e);
        }
    }
}
