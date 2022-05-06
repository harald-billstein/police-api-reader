package se.harbil.policeapireader.service;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import se.harbil.policeapireader.model.PoliceEventModel;

public class PoliceEventExtendedInfoServiceTestData {

    public static final String EXPECTED_TEST = "test";

    public static List<PoliceEventModel> policeEventsWithExtendedInfo() {
        List<PoliceEventModel> policeEventModels = new ArrayList<>();
        PoliceEventModel event1 = PoliceEventModel.builder()
            .url("url")
            .build();
        PoliceEventModel event2 = PoliceEventModel.builder()
            .url("url")
            .build();
        policeEventModels.add(event1);
        policeEventModels.add(event2);
        return policeEventModels;
    }

    public static Document documentResponse() {
        Document document = new Document("url");
        document.body().addClass("text-body editorial-html").prependText("test");
        return document;
    }

}
