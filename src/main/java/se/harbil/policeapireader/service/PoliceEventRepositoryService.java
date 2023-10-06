package se.harbil.policeapireader.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.exception.RepositoryException;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.repository.PoliceEventRepository;

/**
 * The PoliceEventRepositoryService class provides a service layer for saving police events to a database
 * using a PoliceEventRepository. It handles the saving of events and logs relevant information, and in case
 * of an error, throws a RepositoryException.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PoliceEventRepositoryService {

    private final PoliceEventRepository policeEventRepository;

    /**
     * Saves a list of PoliceEventModel objects to the database using a repository.
     *
     * @param policeEvents A list of PoliceEventModel objects to be saved in the database.
     * @return A list of saved PoliceEventModel objects.
     * @throws RepositoryException If there is an error while saving events to the database.
     */
    public List<PoliceEventModel> saveAll(final List<PoliceEventModel> policeEvents) {
        try {
            log.info("Trying to save: {} event(s) in db", policeEvents.size());
            List<PoliceEventModel> savedEvents = policeEventRepository.saveAll(policeEvents);
            log.info("Successfully saved: {} event(s) in db", savedEvents.size());
            return savedEvents;
        } catch (Exception e) {
            log.warn("Failed to save event(s) in db, got error: {}", e.getMessage());
            throw new RepositoryException(e);
        }
    }
}
