package se.harbil.policeapireader.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.harbil.policeapireader.client.PoliceEventClient;
import se.harbil.policeapireader.exception.PoliceEventClientException;
import se.harbil.policeapireader.mapper.PoliceEventModelMapper;
import se.harbil.policeapireader.model.PoliceEventModel;

@ExtendWith(MockitoExtension.class)
class PoliceEventServiceTest {

    @Mock
    private PoliceEventClient policeEventClient;
    @Mock
    private PoliceEventModelMapper policeEventModelMapper;
    private PoliceEventService policeEventService;

    @BeforeEach
    void setup() {
        policeEventService = new PoliceEventService(policeEventClient, policeEventModelMapper);
    }

    @AfterEach
    void teardown() {
        reset(policeEventClient, policeEventModelMapper);
    }

    @Test
    void testCall() {
        when(policeEventClient.call()).thenReturn(new ArrayList<>());
        when(policeEventModelMapper.map(any())).thenReturn(new ArrayList<>());

        List<PoliceEventModel> policeEventModels = policeEventService.call();

        assertNotNull(policeEventModels);
    }

    @Test
    void testCallPoliceEventClientThrowsException() {
        when(policeEventClient.call()).thenThrow(new RuntimeException());

        assertThrows(PoliceEventClientException.class, () -> policeEventService.call());
    }

    @Test
    void testCallPoliceEventModelMapperThrowsException() {
        when(policeEventClient.call()).thenReturn(new ArrayList<>());
        when(policeEventModelMapper.map(any())).thenThrow(new RuntimeException());

        assertThrows(PoliceEventClientException.class, () -> policeEventService.call());
    }
}