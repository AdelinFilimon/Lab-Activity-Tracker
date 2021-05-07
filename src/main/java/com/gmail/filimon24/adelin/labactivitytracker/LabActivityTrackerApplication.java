package com.gmail.filimon24.adelin.labactivitytracker;

import com.gmail.filimon24.adelin.labactivitytracker.business.DatabaseDataInitializer;
import lombok.RequiredArgsConstructor;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class LabActivityTrackerApplication {

    private final DatabaseDataInitializer databaseDataInitializer;

    public static void main(String[] args) {
        SpringApplication.run(LabActivityTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> databaseDataInitializer.init();
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormat.forPattern(CustomApplicationProperties.dateTimeFormat);
    }

}
