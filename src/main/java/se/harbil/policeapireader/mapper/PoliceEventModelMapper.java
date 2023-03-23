package se.harbil.policeapireader.mapper;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.model.PoliceEventResponse;

@Service
public class PoliceEventModelMapper {


    private final String baseUrlForExtendedInfo;

    public PoliceEventModelMapper(@Value("${POLICE_EXTENDED_BASE_URL}") String baseUrlForExtendedInfo) {
        this.baseUrlForExtendedInfo = baseUrlForExtendedInfo;
    }

    public List<PoliceEventModel> map(List<PoliceEventResponse> policeEventResponse) {
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
