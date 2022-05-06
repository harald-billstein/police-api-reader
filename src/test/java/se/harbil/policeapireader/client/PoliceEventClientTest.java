package se.harbil.policeapireader.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.empty;
import static reactor.core.publisher.Mono.just;
import static se.harbil.policeapireader.client.PoliceEventClientTestData.successfulResponse;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import se.harbil.policeapireader.model.PoliceEventResponse;

@ExtendWith(MockitoExtension.class)
class PoliceEventClientTest {

    @Mock
    private WebClient webClient;
    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    private PoliceEventClient policeEventClient;

    @BeforeEach
    void setUp() {
        policeEventClient = new PoliceEventClient(webClient);
    }

    @AfterEach
    void tearDown() {
        reset(webClient);
    }

    @Test
    void testCallReturnsValidResponse() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(webClient.get().retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PoliceEventResponse[].class)).thenReturn(
            just(successfulResponse()));

        List<PoliceEventResponse> response = policeEventClient.call();

        assertEquals("dateTime1", response.get(0).getDatetime());
        assertEquals("name1", response.get(0).getName());
        assertEquals("name1", response.get(0).getLocation().getName());
        assertEquals("gps1", response.get(0).getLocation().getGps());
        assertEquals("summary1", response.get(0).getSummary());
        assertEquals("type1", response.get(0).getType());
        assertEquals("url1", response.get(0).getUrl());
        assertEquals(1L, response.get(0).getId());

        assertEquals("dateTime2", response.get(1).getDatetime());
        assertEquals("name2", response.get(1).getName());
        assertEquals("name2", response.get(1).getLocation().getName());
        assertEquals("gps2", response.get(1).getLocation().getGps());
        assertEquals("summary2", response.get(1).getSummary());
        assertEquals("type2", response.get(1).getType());
        assertEquals("url2", response.get(1).getUrl());
        assertEquals(2L, response.get(1).getId());
    }

    @Test
    void testCallReturnsNullResponse() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(webClient.get().retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(PoliceEventResponse[].class)).thenReturn(empty());

        List<PoliceEventResponse> response = policeEventClient.call();

        assertNull(response);
    }
}