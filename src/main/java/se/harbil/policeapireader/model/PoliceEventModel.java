package se.harbil.policeapireader.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Builder
@Getter
public class PoliceEventModel {

    private String datetime;
    private Long id;
    private String name;
    private String locationGps;
    private String locationName;

    private String summary;
    private String type;
    private String url;
    private LocalDateTime fetchedDateTime;
    @Id
    private String dbId;
    @Setter
    private String extendedInfo;
}
