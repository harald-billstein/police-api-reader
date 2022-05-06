package se.harbil.policeapireader.mapper;

import se.harbil.policeapireader.model.PoliceEventModel;

public class PoliceEventKafkaModelMapperTestData {

    public static PoliceEventModel policeEventModel() {
        return PoliceEventModel.builder()
            .datetime("dateTime")
            .id(1l)
            .locationGps("locationGps")
            .locationName("locationName")
            .name("name")
            .summary("summary")
            .type("type")
            .url("url")
            .extendedInfo("extendedInfo")
            .build();
    }

}
