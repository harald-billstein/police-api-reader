package se.harbil.policeapireader.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PoliceEventExtendedInfoClient {
    public Document call(String path) throws IOException {
        return Jsoup.connect(path).get();
    }
}
