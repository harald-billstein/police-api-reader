//package se.harbil.policeapireader.util;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import se.harbil.policeapireader.model.PoliceEventModel;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static se.harbil.policeapireader.util.EventUtilTestData.EventsFromPoliceApi;
//import static se.harbil.policeapireader.util.EventUtilTestData.lastEventSavedInDb;
//
//class EventUtilTest {
//
//    private EventUtil eventUtil;
//
//    @BeforeEach
//    void setup() {
//        eventUtil = new EventUtil();
//    }
//
//    @Test
//    void checkIfAnyNewEventsFindsOneNew() {
//        Optional<PoliceEventModel> lastSavedEvent = lastEventSavedInDb(2L);
//        List<PoliceEventModel> eventsFromPoliceApi = EventsFromPoliceApi();
//
//        List<PoliceEventModel> checkedEvents = eventUtil.checkIfThereAnyNewEvents(eventsFromPoliceApi, lastSavedEvent);
//
//        assertEquals(1, checkedEvents.size());
//    }
//
//    @Test
//    void checkIfAnyNewEventFindsNoNew() {
//        Optional<PoliceEventModel> lastSavedEvent = lastEventSavedInDb(3L);
//        List<PoliceEventModel> eventsFromPoliceApi = EventsFromPoliceApi();
//
//        List<PoliceEventModel> checkedEvents = eventUtil.checkIfThereAnyNewEvents(eventsFromPoliceApi, lastSavedEvent);
//
//        assertEquals(0, checkedEvents.size());
//    }
//
//    @Test
//    void checkIfAnyNewEveFindsNoNew2() {
//        Optional<PoliceEventModel> lastSavedEvent = lastEventSavedInDb(1L);
//        List<PoliceEventModel> eventsFromPoliceApi = EventsFromPoliceApi();
//
//        List<PoliceEventModel> checkedEvents = eventUtil.checkIfThereAnyNewEvents(eventsFromPoliceApi, lastSavedEvent);
//
//        assertEquals(2, checkedEvents.size());
//    }
//}