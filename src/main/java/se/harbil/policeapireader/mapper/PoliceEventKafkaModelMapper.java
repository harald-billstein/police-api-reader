package se.harbil.policeapireader.mapper;

import org.springframework.stereotype.Service;
import se.harbil.policeapireader.model.PoliceEventKafkaModel;
import se.harbil.policeapireader.model.PoliceEventModel;

@Service
public class PoliceEventKafkaModelMapper {

    public PoliceEventKafkaModel map(PoliceEventModel policeEventModel) {
        return PoliceEventKafkaModel.builder()
            .datetime(policeEventModel.getDatetime())
            .id(policeEventModel.getId())
            .locationName(policeEventModel.getLocationName())
            .locationGps(policeEventModel.getLocationGps())
            .name(policeEventModel.getName())
            .summary(policeEventModel.getSummary())
            .type(policeEventModel.getType())
            .url(policeEventModel.getUrl())
            .extendedInfo(policeEventModel.getExtendedInfo())
            .build();
    }
}
