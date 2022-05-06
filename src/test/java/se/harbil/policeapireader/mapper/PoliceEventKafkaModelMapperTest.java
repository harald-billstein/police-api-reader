package se.harbil.policeapireader.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static se.harbil.policeapireader.mapper.PoliceEventKafkaModelMapperTestData.policeEventModel;
import static se.harbil.policeapireader.mapper.PoliceEventModelMapperTestData.policeEventResponsesWithNullValues;

import org.junit.jupiter.api.Test;
import se.harbil.policeapireader.model.PoliceEventKafkaModel;

class PoliceEventKafkaModelMapperTest {

    @Test
    void testMapPoliceEventModelToPoliceEventKafkaModel() {
        PoliceEventKafkaModelMapper mapper = new PoliceEventKafkaModelMapper();

        PoliceEventKafkaModel kafkaModel = mapper.map(policeEventModel());

        assertAll(
            () -> assertEquals(kafkaModel.getDatetime(), "dateTime"),
            () -> assertEquals(kafkaModel.getId(), 1L),
            () -> assertEquals(kafkaModel.getLocationGps(), "locationGps"),
            () -> assertEquals(kafkaModel.getLocationName(), "locationName"),
            () -> assertEquals(kafkaModel.getName(), "name"),
            () -> assertEquals(kafkaModel.getSummary(), "summary"),
            () -> assertEquals(kafkaModel.getType(), "type"),
            () -> assertEquals(kafkaModel.getUrl(), "url"),
            () -> assertEquals(kafkaModel.getExtendedInfo(), "extendedInfo"));
    }

    @Test
    void testMapPoliceEventModelToPoliceEventKafkaModelThrowsException() {
        PoliceEventModelMapper mapper = new PoliceEventModelMapper();

        assertThrows(NullPointerException.class,
            () -> mapper.map(policeEventResponsesWithNullValues()));
    }
}