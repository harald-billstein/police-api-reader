package se.harbil.policeapireader.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import se.harbil.policeapireader.mapper.PoliceEventKafkaModelMapper;
import se.harbil.policeapireader.model.PoliceEventModel;

/**
 * The EventProducer class is responsible for sending police events to a Kafka topic. It transforms and serializes
 * instances of PoliceEventModel into JSON format before sending them to the Kafka topic.
 */
@Slf4j
@Component
@AllArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final PoliceEventKafkaModelMapper policeEventKafkaModelMapper;
    @Value("${KAFKA_EVENT_TOPIC}")
    private final String kafkaEventTopic;
    private final ObjectMapper objectMapper;

    /**
     * Sends a list of PoliceEventModel objects to a Kafka topic.
     *
     * @param policeEventsModel A list of PoliceEventModel objects to be sent to Kafka.
     */
    @SuppressWarnings("FutureReturnValueIgnored")
    public void sendEvents(final List<PoliceEventModel> policeEventsModel) {
        log.info("Sending {} events to kafka", policeEventsModel.size());
        policeEventsModel.forEach(
            event -> {
                try {
                    kafkaTemplate.send(kafkaEventTopic, objectMapper.writeValueAsString(policeEventKafkaModelMapper.map(event)));
                } catch (JsonProcessingException e) {
                    log.warn("Failed sending event to kafka error: {}", e.getMessage());
                }
            });
    }
}
