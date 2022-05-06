package se.harbil.policeapireader.client;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PoliceEventExtendedInfoClient {

    private final String extendedInfoBaseUrl;

    public PoliceEventExtendedInfoClient(
        @Value("${POLICE_EXTENDED_BASE_URL}") String extendedInfoBaseUrl) {
        this.extendedInfoBaseUrl = extendedInfoBaseUrl;
    }

    public Document call(String path) throws IOException {
        return Jsoup.connect(extendedInfoBaseUrl + path).get();
    }
}
