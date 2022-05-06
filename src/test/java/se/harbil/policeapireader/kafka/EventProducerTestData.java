package se.harbil.policeapireader.kafka;

import java.util.ArrayList;
import java.util.List;
import se.harbil.policeapireader.model.PoliceEventModel;

public class EventProducerTestData {

    public static List<PoliceEventModel> getPoliceEvents() {
        List<PoliceEventModel> policeEventModels = new ArrayList<>();
        policeEventModels.add(PoliceEventModel.builder().build());
        policeEventModels.add(PoliceEventModel.builder().build());
        return policeEventModels;
    }


}
