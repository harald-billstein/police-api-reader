package se.harbil.policeapireader.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static se.harbil.policeapireader.client.PoliceEventExtendedInfoClientTestData.BASE_URL;
import static se.harbil.policeapireader.client.PoliceEventExtendedInfoClientTestData.PATH;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class PoliceEventExtendedInfoClientTest {


    @Mock
    private Connection connection;

    private PoliceEventExtendedInfoClient policeEventExtendedInfoClient;

    @BeforeEach
    void setup() {
        policeEventExtendedInfoClient = new PoliceEventExtendedInfoClient(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        reset(connection);
    }

    @Test
    void testClientCallSuccessfulReturnsDocument() throws IOException {
        try (MockedStatic<Jsoup> jsoupMockedStatic = mockStatic(Jsoup.class)) {
            Document document = new Document(BASE_URL + PATH);

            when(connection.get()).thenReturn(document);
            jsoupMockedStatic.when(() -> Jsoup.connect(PATH)).thenReturn(connection);

            Document doc = policeEventExtendedInfoClient.call(PATH);

            assertEquals(document, doc);
        }
    }

    @Test
    void testWebclientThrowsExceptionShouldReturnException() {
        try (MockedStatic<Jsoup> mockedStaticJsoup = mockStatic(Jsoup.class)) {

            when(connection.get()).thenThrow(new RuntimeException());
            mockedStaticJsoup.when(() -> Jsoup.connect(any())).thenReturn(connection);

            policeEventExtendedInfoClient.call(PATH);

            fail();
        } catch (Exception e) {
            assertInstanceOf(RuntimeException.class, e);
        }
    }
}
