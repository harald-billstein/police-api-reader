package se.harbil.policeapireader.client;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 * The PoliceEventExtendedInfoClient class is responsible for making HTTP requests to an external service
 * to retrieve extended information related to police events. It uses the Jsoup library to perform the HTTP request
 * and parse the HTML response into a Document object.
 */
@Component
public class PoliceEventExtendedInfoClient {

    /**
     * Makes an HTTP GET request to retrieve extended information for a specified URL path.
     *
     * @param path The URL path for which extended information is to be fetched.
     * @return A Document object representing the HTML response containing extended information.
     * @throws IOException If there is an error while making the HTTP request or parsing the response.
     */
    public Document call(final String path) throws IOException {
        return Jsoup.connect(path).get();
    }
}
