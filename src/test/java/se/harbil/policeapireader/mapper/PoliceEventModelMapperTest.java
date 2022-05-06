package se.harbil.policeapireader.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.harbil.policeapireader.mapper.PoliceEventModelMapperTestData.policeEventResponse;
import static se.harbil.policeapireader.mapper.PoliceEventModelMapperTestData.policeEventResponsesWithNullValues;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import se.harbil.policeapireader.model.PoliceEventModel;

class PoliceEventModelMapperTest {

    @Test
    void testMapPoliceEventResponseToPoliceEventModel() {
        PoliceEventModelMapper mapper = new PoliceEventModelMapper();

        List<PoliceEventModel> policeEventModels = mapper.map(policeEventResponse());

        assertAll(
            () -> assertEquals(policeEventModels.get(0).getName(), "name"),
            () -> assertEquals(policeEventModels.get(0).getDatetime(), "dateTime"),
            () -> assertEquals(policeEventModels.get(0).getSummary(), "summary"),
            () -> assertTrue(
                policeEventModels.get(0).getFetchedDateTime().isBefore(LocalDateTime.now())),
            () -> assertEquals(policeEventModels.get(0).getDatetime(), "dateTime"),
            () -> assertEquals(policeEventModels.get(0).getLocationGps(), "locationGps"),
            () -> assertEquals(policeEventModels.get(0).getLocationName(), "locationName"),
            () -> assertEquals(policeEventModels.get(0).getUrl(), "url"));
    }

    @Test
    void testMapPoliceEventResponseToPoliceEventModelThrowsException() {
        PoliceEventModelMapper mapper = new PoliceEventModelMapper();

        assertThrows(NullPointerException.class,
            () -> mapper.map(policeEventResponsesWithNullValues()));
    }
}