package se.harbil.policeapireader.service;

import java.util.ArrayList;
import java.util.List;
import se.harbil.policeapireader.model.PoliceEventModel;

public class PoliceEventRepositoryServiceTestData {

    public static List<PoliceEventModel> policeEvents() {
        List<PoliceEventModel> events = new ArrayList<>();
        events.add(PoliceEventModel.builder().build());
        return events;
    }

}
