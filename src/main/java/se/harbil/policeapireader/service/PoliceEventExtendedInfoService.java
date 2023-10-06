package se.harbil.policeapireader.service;

import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.client.PoliceEventExtendedInfoClient;
import se.harbil.policeapireader.model.PoliceEventModel;

/**
 * The PoliceEventExtendedInfoService class is responsible for retrieving and storing extended information
 * for a list of unsaved police events. It uses a PoliceEventExtendedInfoClient to make external API calls and
 * updates the PoliceEventModel objects with the retrieved extended information.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PoliceEventExtendedInfoService {

    private final PoliceEventExtendedInfoClient policeEventExtendedInfoClient;

    /**
     * Calls the external API to fetch extended information for a list of unsaved police events.
     *
     * @param unsavedPoliceEvents A list of unsaved PoliceEventModel objects for which extended information is to be fetched.
     * @return The same list of PoliceEventModel objects with updated extended information.
     */
    public List<PoliceEventModel> call(final List<PoliceEventModel> unsavedPoliceEvents) {
        unsavedPoliceEvents.forEach(event -> {
            try {
                String response = call(event.getUrl());
                event.setExtendedInfo(response);
            } catch (Exception e) {
                log.warn("Failed to get extended info for: {} got error: {}", event.getUrl(),
                    e.getMessage());
            }
        });

        return unsavedPoliceEvents;
    }

    private String call(final String path) throws IOException {
        log.info("Trying to get extended info for: {}", path);

        Document response = policeEventExtendedInfoClient.call(path);

        List<String> eachText = response.body()
            .getElementsByClass("text-body editorial-html")
            .eachText();

        String textBody = String.join(", ", eachText);
        log.info("Successfully got extended info for: {}", path);

        return textBody;
    }
}
