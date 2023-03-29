package se.harbil.policeapireader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policeapireader.exception.RepositoryException;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.repository.PoliceEventRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PoliceEventRepositoryService {

    private final PoliceEventRepository policeEventRepository;

    public PoliceEventRepositoryService(PoliceEventRepository policeEventRepository) {
        this.policeEventRepository = policeEventRepository;
    }

    public List<PoliceEventModel> saveAll(List<PoliceEventModel> policeEvents) {
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

    public Optional<PoliceEventModel> findLatestEvent() {
        try {
            log.info("Trying to find latest event in db");
            PoliceEventModel event = policeEventRepository.findTopByOrderByIdDesc();
            log.info("Successfully found latest event in db");
            return Optional.ofNullable(event);
        } catch (Exception e) {
            log.warn("Failed to find latest event in db, got error: {}", e.getMessage());
            throw new RepositoryException(e);
        }
    }
}
