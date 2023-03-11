package se.harbil.policeapireader.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.mapper.PoliceEventKafkaModelMapper;
import se.harbil.policeapireader.model.PoliceEventModel;

@Slf4j
@Service
public class EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final PoliceEventKafkaModelMapper policeEventKafkaModelMapper;
    private final String kafkaEventTopic;
    private final ObjectMapper objectMapper;

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate,
        PoliceEventKafkaModelMapper policeEventKafkaModelMapper,
        @Value("${KAFKA_EVENT_TOPIC}") String kafkaEventTopic, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.policeEventKafkaModelMapper = policeEventKafkaModelMapper;
        this.kafkaEventTopic = kafkaEventTopic;
        this.objectMapper = objectMapper;
    }

    public void sendEvents(List<PoliceEventModel> policeEventsModel) {
        log.info("Sending {} events to kafka", policeEventsModel.size());
        policeEventsModel.forEach(
            event -> {
                try {
                    kafkaTemplate.send(kafkaEventTopic,
                        objectMapper.writeValueAsString(policeEventKafkaModelMapper.map(event)));
                } catch (JsonProcessingException e) {
                    log.warn("Failed sending event to kafka error: {}", e.getMessage());
                }
            });
    }
}
