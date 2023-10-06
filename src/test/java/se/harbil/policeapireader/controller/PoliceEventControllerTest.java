package se.harbil.policeapireader.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.harbil.policeapireader.controller.PoliceEventControllerTestData.getPoliceEvent;
import static se.harbil.policeapireader.controller.PoliceEventControllerTestData.getPoliceEvents;
import static se.harbil.policeapireader.controller.PoliceEventControllerTestData.getPoliceEventsWithExtraInfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.harbil.policeapireader.kafka.EventProducer;
import se.harbil.policeapireader.service.PoliceEventExtendedInfoService;
import se.harbil.policeapireader.service.PoliceEventRepositoryService;
import se.harbil.policeapireader.service.PoliceEventService;
import se.harbil.policeapireader.util.EventUtil;

@ExtendWith(MockitoExtension.class)
class PoliceEventControllerTest {

    @Mock
    private PoliceEventService policeEventService;
    @Mock
    private PoliceEventRepositoryService policeEventRepositoryService;
    @Mock
    private EventUtil eventUtil;
    @Mock
    private PoliceEventExtendedInfoService policeEventExtendedInfoService;
    @Mock
    private EventProducer eventProducer;
    @InjectMocks
    private PoliceEventController policeEventController;

    @Test
    void testFetchPoliceEvents() {
        when(policeEventService.call()).thenReturn(getPoliceEvents());
        when(eventUtil.checkIfThereAnyNewEvents(any())).thenReturn(getPoliceEvents());
        when(policeEventExtendedInfoService.call(any())).thenReturn(getPoliceEventsWithExtraInfo());
        when(policeEventRepositoryService.saveAll(any())).thenReturn(getPoliceEventsWithExtraInfo());

        policeEventController.fetchPoliceEvents();

        verify(eventProducer, times(1)).sendEvents(any());
    }

    @Test
    void testCallToApiThrowsException() {
        when(policeEventService.call()).thenThrow(new RuntimeException());

        policeEventController.fetchPoliceEvents();

        verify(policeEventService, times(1)).call();
        verify(eventUtil, times(0)).checkIfThereAnyNewEvents(any());
        verify(policeEventExtendedInfoService, times(0)).call(any());
        verify(policeEventRepositoryService, times(0)).saveAll(any());
        verify(eventProducer, times(0)).sendEvents(any());
    }

    @Test
    void testEventUtilThrowsException() {
        when(eventUtil.checkIfThereAnyNewEvents(any())).thenThrow(new RuntimeException());

        policeEventController.fetchPoliceEvents();

        verify(policeEventService, times(1)).call();
        verify(eventUtil, times(1)).checkIfThereAnyNewEvents(any());
        verify(policeEventExtendedInfoService, times(0)).call(any());
        verify(policeEventRepositoryService, times(0)).saveAll(any());
        verify(eventProducer, times(0)).sendEvents(any());
    }

    @Test
    void testExtendedInfoClientThrowsException() {
        when(policeEventExtendedInfoService.call(any())).thenThrow(new RuntimeException());

        policeEventController.fetchPoliceEvents();

        verify(policeEventService, times(1)).call();
        verify(eventUtil, times(1)).checkIfThereAnyNewEvents(any());
        verify(policeEventExtendedInfoService, times(1)).call(any());
        verify(policeEventRepositoryService, times(0)).saveAll(any());
        verify(eventProducer, times(0)).sendEvents(any());
    }

    @Test
    void testSaveToDbThrowsException() {
        when(policeEventRepositoryService.saveAll(any())).thenThrow(new RuntimeException());

        policeEventController.fetchPoliceEvents();

        verify(policeEventService, times(1)).call();
        verify(eventUtil, times(1)).checkIfThereAnyNewEvents(any());
        verify(policeEventExtendedInfoService, times(1)).call(any());
        verify(policeEventRepositoryService, times(1)).saveAll(any());
        verify(eventProducer, times(0)).sendEvents(any());
    }
}