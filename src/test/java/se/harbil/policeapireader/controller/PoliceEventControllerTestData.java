package se.harbil.policeapireader.controller;

import se.harbil.policeapireader.model.PoliceEventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PoliceEventControllerTestData {

    public static List<PoliceEventModel> getPoliceEventsWithExtraInfo() {
        List<PoliceEventModel> eventModels = new ArrayList<>();
        eventModels.add(PoliceEventModel.builder()
                .locationName("locationName")
                .locationGps("locationGps")
                .name("name")
                .extendedInfo("extraInfo")
                .url("url")
                .id(1L)
                .type("type")
                .summary("summary")
                .datetime("dateTime")
                .build());
        return eventModels;
    }

    public static List<PoliceEventModel> getPoliceEvents() {
        return List.of(PoliceEventModel.builder()
                .locationName("locationName")
                .locationGps("locationGps")
                .name("name")
                .url("url")
                .id(1L)
                .type("type")
                .summary("summary")
                .datetime("dateTime")
                .build());
    }

    public static Optional<PoliceEventModel> getPoliceEvent() {
        return Optional.ofNullable(PoliceEventModel.builder()
                .locationName("locationName")
                .locationGps("locationGps")
                .name("name")
                .url("url")
                .id(1L)
                .type("type")
                .summary("summary")
                .datetime("dateTime")
                .build());
    }
}
