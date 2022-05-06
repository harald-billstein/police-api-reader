package se.harbil.policeapireader.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PoliceEventKafkaModel {

    private String datetime;
    private Long id;
    private String locationGps;
    private String locationName;
    private String name;
    private String summary;
    private String type;
    private String url;
    private String extendedInfo;
}
