package se.harbil.policeapireader.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static se.harbil.policeapireader.repository.PoliceEventRepositoryTestData.insert500NewEventsAndOneOldInDb;
import static se.harbil.policeapireader.repository.PoliceEventRepositoryTestData.unWantedEvent;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import se.harbil.policeapireader.model.PoliceEventModel;

@SpringBootTest
@ActiveProfiles("test")
class PoliceEventRepositoryTest {

    @Autowired
    private PoliceEventRepository policeEventRepository;

    @Test
    public void findTop500ByOrderByFetchedDateTimeDesc(@Autowired MongoTemplate mongoTemplate) {
        insert500NewEventsAndOneOldInDb(mongoTemplate);

        List<PoliceEventModel> top500ByOrderByIdDesc = policeEventRepository.findTop500ByOrderByFetchedDateTimeDesc();
        assertEquals(500, top500ByOrderByIdDesc.size());
        assertFalse(top500ByOrderByIdDesc.contains(unWantedEvent));
    }
}