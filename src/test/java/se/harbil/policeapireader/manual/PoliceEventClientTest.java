package se.harbil.policeapireader.manual;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.harbil.policeapireader.client.PoliceEventClient;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.service.PoliceEventRepositoryService;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@Disabled("Manual test")
class PoliceEventClientTest {

    @Autowired
    PoliceEventClient policeEventClient;

    @Autowired
    PoliceEventRepositoryService policeEventRepositoryService;

    @Test
    void call() {
        //List<PoliceEventModel> response = policeEventClient.call();
        //policeEventRepositoryService.saveAll(response);

        //log.info(response.toString());
    }


}