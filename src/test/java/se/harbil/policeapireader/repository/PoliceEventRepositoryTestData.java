package se.harbil.policeapireader.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import se.harbil.policeapireader.model.PoliceEventModel;

public class PoliceEventRepositoryTestData {

    public static final PoliceEventModel unWantedEvent = PoliceEventModel.builder()
        .name("this object should not be returned")
        .fetchedDateTime(LocalDateTime.now().minusMinutes(1))
        .build();

    public static List<PoliceEventModel> insert500NewEventsAndOneOldInDb(
        MongoTemplate mongoTemplate) {
        List<PoliceEventModel> policeEventModels = new ArrayList<>();
        policeEventModels.add(unWantedEvent);

        for (int i = 0; i < 500; i++) {
            policeEventModels.add(PoliceEventModel.builder()
                .fetchedDateTime(LocalDateTime.now())
                .build());
        }

        mongoTemplate.insertAll(policeEventModels);
        return policeEventModels;
    }
}
