package se.harbil.policeapireader.service;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.client.PoliceEventExtendedInfoClient;
import se.harbil.policeapireader.model.PoliceEventModel;

@Slf4j
@Service
public class PoliceEventExtendedInfoService {

    private final PoliceEventExtendedInfoClient policeEventExtendedInfoClient;

    public PoliceEventExtendedInfoService(
        PoliceEventExtendedInfoClient policeEventExtendedInfoClient) {
        this.policeEventExtendedInfoClient = policeEventExtendedInfoClient;
    }

    public List<PoliceEventModel> call(List<PoliceEventModel> unsavedPoliceEvents) {
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

    private String call(String path) throws IOException {
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
