package se.harbil.policeapireader.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static se.harbil.policeapireader.util.EventUtilTestData.oneEvent;
import static se.harbil.policeapireader.util.EventUtilTestData.threeEvents;
import static se.harbil.policeapireader.util.EventUtilTestData.twoEvents;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.harbil.policeapireader.exception.EventUtilException;
import se.harbil.policeapireader.model.PoliceEventModel;

class EventUtilTest {

    private EventUtil eventUtil;

    @BeforeEach
    void setup() {
        eventUtil = new EventUtil();
    }

    @Test
    void checkIfThereAnyNewEveFindsOneNew() {
        List<PoliceEventModel> newEvents = oneEvent();
        List<PoliceEventModel> oldEvents = twoEvents();
        List<PoliceEventModel> checkedEvents = eventUtil.checkIfThereAnyNewEvents(newEvents,
            oldEvents);

        assertEquals(1, checkedEvents.size());
    }

    @Test
    void checkIfThereAnyNewEveFindsNoNew() {
        List<PoliceEventModel> newEvents = threeEvents();
        List<PoliceEventModel> oldEvents = threeEvents();
        List<PoliceEventModel> checkedEvents = eventUtil.checkIfThereAnyNewEvents(newEvents,
            oldEvents);

        assertEquals(0, checkedEvents.size());
    }

    @Test
    void checkIfThereAnyNewEveFindsNoNew2() {
        List<PoliceEventModel> oldEvents = threeEvents();

        assertThrows(EventUtilException.class, () -> eventUtil.checkIfThereAnyNewEvents(null,
            oldEvents));
    }
}