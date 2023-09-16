package se.harbil.policeapireader.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.repository.PoliceEventRepository;

@Slf4j
@Service
@AllArgsConstructor
public class EventUtil {


    private PoliceEventRepository policeEventRepository;

    public List<PoliceEventModel> checkIfThereAnyNewEvents(List<PoliceEventModel> newPoliceEvents) {

        return newPoliceEvents.stream()
            .filter(policeEventModel ->
                !policeEventRepository.existsByIdAndDatetime(policeEventModel.getId(), policeEventModel.getDatetime()))
            .toList();
    }
}
