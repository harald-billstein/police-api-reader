package se.harbil.policeapireader.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.harbil.policeapireader.model.PoliceEventModel;

/**
 * The PoliceEventRepository interface defines methods for interacting with a repository of PoliceEventModel objects.
 * This repository is typically used to perform CRUD (Create, Read, Update, Delete) operations on police events stored
 * in a MongoDB database.
 */
public interface PoliceEventRepository extends MongoRepository<PoliceEventModel, String> {

    /**
     * Checks if a police event with the given ID and datetime exists in the repository.
     *
     * @param id       The ID of the police event to check.
     * @param dateTime The datetime of the police event to check.
     * @return true if a police event with the specified ID and datetime exists; false otherwise.
     */
    boolean existsByIdAndDatetime(long id, String dateTime);
}
