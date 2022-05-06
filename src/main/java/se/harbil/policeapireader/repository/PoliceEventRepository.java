package se.harbil.policeapireader.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import se.harbil.policeapireader.model.PoliceEventModel;

public interface PoliceEventRepository extends MongoRepository<PoliceEventModel, String> {

    List<PoliceEventModel> findTop500ByOrderByFetchedDateTimeDesc();
}
