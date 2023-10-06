package se.harbil.policeapireader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The `PoliceApiReaderApplication` class serves as the entry point for the Police API Reader application.
 * It is a Spring Boot application that initializes and starts the application with the specified configuration.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class PoliceApiReaderApplication {

    /**
     * The main method that starts the Police API Reader application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(PoliceApiReaderApplication.class, args);
    }

}
