package se.harbil.policeapireader.mapper;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.model.PoliceEventResponse;

/**
 * The PoliceEventModelMapper class is responsible for mapping instances of PoliceEventResponse to corresponding
 * instances of PoliceEventModel. This mapping is used to transform external API responses into a more structured
 * format for further processing within the application.
 */
@Component
@AllArgsConstructor
public class PoliceEventModelMapper {

    @Value("${POLICE_EXTENDED_BASE_URL}")
    private final String baseUrlForExtendedInfo;

    /**
     * Maps a list of PoliceEventResponse objects to a list of PoliceEventModel objects.
     *
     * @param policeEventResponse A list of PoliceEventResponse objects to be mapped.
     * @return A list of mapped PoliceEventModel objects with data from the input PoliceEventResponse objects.
     */
    public List<PoliceEventModel> map(final List<PoliceEventResponse> policeEventResponse) {
        return policeEventResponse.stream().map(policeEvent -> PoliceEventModel.builder()
                .id(policeEvent.getId())
                .datetime(policeEvent.getDatetime())
                .type(policeEvent.getType())
                .url(baseUrlForExtendedInfo + policeEvent.getUrl())
                .summary(policeEvent.getSummary())
                .name(policeEvent.getName())
                .fetchedDateTime(LocalDateTime.now())
                .locationGps(policeEvent.getLocation().getGps())
                .locationName(policeEvent.getLocation().getName())
                .build())
            .toList();
    }

}
