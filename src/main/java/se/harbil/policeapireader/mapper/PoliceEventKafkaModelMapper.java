package se.harbil.policeapireader.mapper;

import org.springframework.stereotype.Component;
import se.harbil.policeapireader.model.PoliceEventKafkaModel;
import se.harbil.policeapireader.model.PoliceEventModel;

/**
 * The PoliceEventKafkaModelMapper class is responsible for mapping instances of PoliceEventModel to
 * corresponding instances of PoliceEventKafkaModel. This mapping is useful when transforming data for
 * use with Kafka messages or other purposes.
 */
@Component
public class PoliceEventKafkaModelMapper {

    /**
     * Maps a PoliceEventModel object to a PoliceEventKafkaModel object.
     *
     * @param policeEventModel The PoliceEventModel object to be mapped.
     * @return A mapped PoliceEventKafkaModel object with data from the input PoliceEventModel.
     */
    public PoliceEventKafkaModel map(final PoliceEventModel policeEventModel) {
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
