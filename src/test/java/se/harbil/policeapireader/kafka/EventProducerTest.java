package se.harbil.policeapireader.kafka;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.harbil.policeapireader.kafka.EventProducerTestData.getPoliceEvents;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import se.harbil.policeapireader.mapper.PoliceEventKafkaModelMapper;

@ExtendWith(MockitoExtension.class)
class EventProducerTest {

    @Mock
    private PoliceEventKafkaModelMapper policeEventKafkaModelMapper;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @Mock
    private ObjectMapper objectMapper;
    private EventProducer eventProducer;

    @BeforeEach
    void setup() {
        eventProducer = new EventProducer(kafkaTemplate, policeEventKafkaModelMapper, "topic",
            objectMapper);
    }

    @AfterEach
    void teardown() {
        reset(policeEventKafkaModelMapper, kafkaTemplate);
    }

    @Test
    void testKafkaProducer() {
        eventProducer.sendEvents(getPoliceEvents());

        verify(kafkaTemplate, times(2)).send(any(), any());
    }

    @Test
    void testThrowsException() {
        when(kafkaTemplate.send(any(), any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> eventProducer.sendEvents(getPoliceEvents()));
    }
}