
package se.harbil.policeapireader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PoliceEventResponse {

    private String datetime;
    private Long id;
    private Location location;
    private String name;
    private String summary;
    private String type;
    private String url;
}
