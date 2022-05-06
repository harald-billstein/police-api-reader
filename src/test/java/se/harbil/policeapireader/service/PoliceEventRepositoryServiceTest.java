package se.harbil.policeapireader.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static se.harbil.policeapireader.service.PoliceEventRepositoryServiceTestData.policeEvents;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.harbil.policeapireader.exception.RepositoryException;
import se.harbil.policeapireader.model.PoliceEventModel;
import se.harbil.policeapireader.repository.PoliceEventRepository;


@ExtendWith(MockitoExtension.class)
class PoliceEventRepositoryServiceTest {

    @Mock
    private PoliceEventRepository policeEventRepository;
    private PoliceEventRepositoryService policeEventRepositoryService;

    @BeforeEach
    void setup() {
        policeEventRepositoryService = new PoliceEventRepositoryService(policeEventRepository);
    }

    @AfterEach
    void tearDown() {
        reset(policeEventRepository);
    }

    @Test
    void testFind500LastDocuments() {
        when(policeEventRepository.findTop500ByOrderByFetchedDateTimeDesc()).thenReturn(
            policeEvents());

        List<PoliceEventModel> documents = policeEventRepositoryService.find500LastDocument();

        assertFalse(documents.isEmpty());
    }

    @Test
    void testFind500LastDocumentsThrowsException() {
        when(policeEventRepository.findTop500ByOrderByFetchedDateTimeDesc())
            .thenThrow(new RuntimeException());

        assertThrows(RepositoryException.class,
            () -> policeEventRepositoryService.find500LastDocument());
    }

    @Test
    void testSaveAllDocuments() {
        when(policeEventRepository.saveAll(any())).thenReturn(policeEvents());

        List<PoliceEventModel> documents = policeEventRepositoryService.saveAll(policeEvents());

        assertFalse(documents.isEmpty());
    }


    @Test
    void testSaveAllDocumentsThrowsException() {
        when(policeEventRepository.saveAll(any())).thenThrow(new RuntimeException());

        assertThrows(RepositoryException.class,
            () -> policeEventRepositoryService.saveAll(policeEvents()));
    }


}