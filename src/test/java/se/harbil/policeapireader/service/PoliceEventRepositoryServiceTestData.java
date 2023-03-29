package se.harbil.policeapireader.service;

import se.harbil.policeapireader.model.PoliceEventModel;

import java.util.List;

public class PoliceEventRepositoryServiceTestData {

    public static List<PoliceEventModel> policeEvents() {
        return List.of(PoliceEventModel.builder().build());
    }

    public static PoliceEventModel policeEvent() {
        return PoliceEventModel.builder().build();
    }
}
