package se.harbil.policeapireader.client;

import se.harbil.policeapireader.model.Location;
import se.harbil.policeapireader.model.PoliceEventResponse;

public class PoliceEventClientTestData {
    public static PoliceEventResponse[] successfulResponse() {
        PoliceEventResponse[] responses = new PoliceEventResponse[2];
        Location firstLocation = new Location("gps1", "name1");
        Location secondLocation = new Location("gps2", "name2");
        PoliceEventResponse firstEvent = new PoliceEventResponse("dateTime1", 1L,
            firstLocation, "name1", "summary1", "type1", "url1");
        PoliceEventResponse secondEvent = new PoliceEventResponse("dateTime2", 2L,
            secondLocation, "name2", "summary2", "type2", "url2");
        responses[0] = firstEvent;
        responses[1] = secondEvent;
        return responses;
    }
}