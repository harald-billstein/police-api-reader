package se.harbil.policeapireader.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static se.harbil.policeapireader.service.PoliceEventExtendedInfoServiceTestData.EXPECTED_TEST;
import static se.harbil.policeapireader.service.PoliceEventExtendedInfoServiceTestData.documentResponse;
import static se.harbil.policeapireader.service.PoliceEventExtendedInfoServiceTestData.policeEventsWithExtendedInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.harbil.policeapireader.client.PoliceEventExtendedInfoClient;
import se.harbil.policeapireader.model.PoliceEventModel;

@ExtendWith(MockitoExtension.class)
class PoliceEventExtendedInfoServiceTest {

    @Mock
    private PoliceEventExtendedInfoClient policeEventExtendedInfoClient;
    private PoliceEventExtendedInfoService policeEventExtendedInfoService;

    @BeforeEach
    void setup() {
        policeEventExtendedInfoService = new PoliceEventExtendedInfoService(
            policeEventExtendedInfoClient);
    }

    @AfterEach
    void teardown() {
        reset(policeEventExtendedInfoClient);
    }

    @Test
    void testNoEventsShouldReturnEmptyList() throws IOException {
        when(policeEventExtendedInfoClient.call(any())).thenReturn(null);

        List<PoliceEventModel> events = policeEventExtendedInfoService.call(new ArrayList<>());

        assertTrue(events.isEmpty());
    }

    @Test
    void testTwoEventsHasExtraInfo() throws IOException {
        when(policeEventExtendedInfoClient.call(any())).thenReturn(documentResponse());

        List<PoliceEventModel> events = policeEventExtendedInfoService.call(
            policeEventsWithExtendedInfo());

        assertFalse(events.get(0).getExtendedInfo().isEmpty());
        assertFalse(events.get(1).getExtendedInfo().isEmpty());
    }

    @Test
    void testExtendedClientThrowsException() throws IOException {
        when(policeEventExtendedInfoClient.call(any())).thenReturn(documentResponse())
            .thenThrow(new RuntimeException());

        List<PoliceEventModel> events = policeEventExtendedInfoService.call(
            policeEventsWithExtendedInfo());

        assertEquals(EXPECTED_TEST, events.get(0).getExtendedInfo());
        assertNull(events.get(1).getExtendedInfo());
    }
}