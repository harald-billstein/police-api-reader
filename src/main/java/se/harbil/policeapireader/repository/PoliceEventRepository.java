package se.harbil.policeapireader.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.harbil.policeapireader.model.PoliceEventModel;

public interface PoliceEventRepository extends MongoRepository<PoliceEventModel, String> {

    PoliceEventModel findTopByOrderByIdDesc();
}
