package se.harbil.policeapireader.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se.harbil.policeapireader.util.EventUtilTestData.EventsFromPoliceApi;
import static se.harbil.policeapireader.util.EventUtilTestData.lastEventSavedInDb;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.repository.PoliceEventRepository;

@ExtendWith(MockitoExtension.class)
class EventUtilTest {

    @Mock
    PoliceEventRepository policeEventRepository;

    @InjectMocks
    private EventUtil eventUtil;


    @Test
    void checkIfAnyNewEventFindsNoNew() {
    }

}