package se.harbil.policeapireader.kafka;

import com.google.gson.Gson;
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

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate,
        PoliceEventKafkaModelMapper policeEventKafkaModelMapper,
        @Value("${KAFKA_EVENT_TOPIC}") String kafkaEventTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.policeEventKafkaModelMapper = policeEventKafkaModelMapper;
        this.kafkaEventTopic = kafkaEventTopic;
    }

    public void sendEvents(List<PoliceEventModel> policeEventsModel) {
        log.info("Sending {} events to kafka", policeEventsModel.size());
        policeEventsModel.forEach(
            event ->
                kafkaTemplate.send(kafkaEventTopic, new Gson()
                    .toJson(policeEventKafkaModelMapper.map(event))));

    }

}
