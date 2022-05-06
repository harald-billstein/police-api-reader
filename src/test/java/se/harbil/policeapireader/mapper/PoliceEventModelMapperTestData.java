package se.harbil.policeapireader.mapper;

import java.util.ArrayList;
import java.util.List;
import se.harbil.policeapireader.model.Location;
import se.harbil.policeapireader.model.PoliceEventResponse;

public class PoliceEventModelMapperTestData {

    public static List<PoliceEventResponse> policeEventResponsesWithNullValues() {
        List<PoliceEventResponse> policeEvents = new ArrayList<>();
        policeEvents.add(new PoliceEventResponse());
        return policeEvents;
    }

    public static List<PoliceEventResponse> policeEventResponse() {
        List<PoliceEventResponse> policeEvents = new ArrayList<>();
        String DATE_TIME = "dateTime";
        Long ID = 1L;
        String LOCATION_GPS = "locationGps";
        String LOCATION_NAME = "locationName";
        Location location = new Location(LOCATION_GPS, LOCATION_NAME);
        String NAME = "name";
        String SUMMARY = "summary";
        String TYPE = "type";
        String URL = "url";
        PoliceEventResponse policeEventResponse = new PoliceEventResponse(DATE_TIME, ID, location,
            NAME,
            SUMMARY, TYPE, URL);
        policeEvents.add(policeEventResponse);
        return policeEvents;
    }

}
