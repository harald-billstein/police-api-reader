package se.harbil.policeapireader.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.harbil.policeapireader.model.PoliceEventModel;

public class EventUtilTestData {


    public static List<PoliceEventModel> oneEvent() {
        List<PoliceEventModel> events = new ArrayList<>();
        PoliceEventModel event = PoliceEventModel.builder()
            .extendedInfo("extraInfo")
            .datetime("datetime")
            .summary("summary")
            .type("type")
            .name("name")
            .locationGps("locationGps")
            .locationName("locationName")
            .dbId("dbId")
            .id(1L)
            .url("url")
            .fetchedDateTime(LocalDateTime.now())
            .build();

        events.add(event);
        return events;
    }

    public static List<PoliceEventModel> twoEvents() {
        List<PoliceEventModel> events = new ArrayList<>();
        PoliceEventModel event1 = PoliceEventModel.builder()
            .extendedInfo("extraInfo1")
            .datetime("datetime1")
            .summary("summary1")
            .type("type1")
            .name("name1")
            .locationGps("locationGps1")
            .locationName("locationName1")
            .dbId("dbId1")
            .id(1L)
            .url("url1")
            .fetchedDateTime(LocalDateTime.now())
            .build();
        PoliceEventModel event2 = PoliceEventModel.builder()
            .extendedInfo("extraInfo2")
            .datetime("datetime2")
            .summary("summary2")
            .type("type2")
            .name("name2")
            .locationGps("locationGps2")
            .locationName("locationName2")
            .dbId("dbId2")
            .id(2L)
            .url("url2")
            .fetchedDateTime(LocalDateTime.now())
            .build();

        events.add(event1);
        events.add(event2);
        return events;
    }

    public static List<PoliceEventModel> threeEvents() {
        List<PoliceEventModel> events = new ArrayList<>();
        PoliceEventModel event1 = PoliceEventModel.builder()
            .extendedInfo("extraInfo1")
            .datetime("datetime1")
            .summary("summary1")
            .type("type1")
            .name("name1")
            .locationGps("locationGps1")
            .locationName("locationName1")
            .dbId("dbId1")
            .id(1L)
            .url("url1")
            .fetchedDateTime(LocalDateTime.now())
            .build();

        PoliceEventModel event2 = PoliceEventModel.builder()
            .extendedInfo("extraInfo2")
            .datetime("datetime2")
            .summary("summary2")
            .type("type2")
            .name("name2")
            .locationGps("locationGps2")
            .locationName("locationName2")
            .dbId("dbId2")
            .id(2L)
            .url("url2")
            .fetchedDateTime(LocalDateTime.now())
            .build();

        PoliceEventModel event3 = PoliceEventModel.builder()
            .extendedInfo("extraInfo3")
            .datetime("datetime3")
            .summary("summary3")
            .type("type3")
            .name("name3")
            .locationGps("locationGps3")
            .locationName("locationName3")
            .dbId("dbId3")
            .id(3L)
            .url("url3")
            .fetchedDateTime(LocalDateTime.now())
            .build();

        events.add(event1);
        events.add(event2);
        events.add(event3);
        return events;
    }

}
