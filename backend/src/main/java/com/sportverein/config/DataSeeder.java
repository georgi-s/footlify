package com.sportverein.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSeeder {

    @Bean
    @Profile("!prod") // Nur in nicht-prod Umgebungen ausführen
    public CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            // Prüfen ob Daten bereits existieren
            Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM liga", Long.class);
            
            if (count == null || count == 0) {
                // Daten aus data.sql werden automatisch geladen
                System.out.println("Datenbank wurde mit Testdaten initialisiert");
            } else {
                System.out.println("Datenbank enthält bereits " + count + " Ligen - Überspringe Initialisierung");
            }
        };
    }
}
