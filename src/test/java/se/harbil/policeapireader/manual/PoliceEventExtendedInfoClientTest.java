package se.harbil.policeapireader.manual;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.harbil.policeapireader.client.PoliceEventExtendedInfoClient;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@Disabled("Manual test")
class PoliceEventExtendedInfoClientTest {

    public static final String PATH = "/aktuellt/handelser/2022/maj/1/01-maj-1458-brand-ludvika/";
    @Autowired
    PoliceEventExtendedInfoClient policeEventExtendedInfoClient;


    @Test
    void call() throws IOException {
        Document response = policeEventExtendedInfoClient.call(
            PATH);

        List<String> strings = response
            .body()
            .getElementsByClass("text-body editorial-html")
            .eachText();

        System.out.println(String.join(", ", strings));


    }
}