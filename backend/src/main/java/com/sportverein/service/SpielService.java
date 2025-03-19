package com.sportverein.service;

import com.sportverein.model.Spiel;
import java.time.LocalDate;
import java.util.List;

/**
 * Service-Interface für Spielplan-Funktionen
 */
public interface SpielService {
    /**
     * Gibt alle Spiele zurück
     * @return Liste aller Spiele
     */
    List<Spiel> getAllSpiele();
    
    /**
     * Gibt ein Spiel anhand seiner ID zurück
     * @param id Die ID des gesuchten Spiels
     * @return Das gefundene Spiel
     */
    Spiel getSpielById(Long id);
    
    /**
     * Erstellt ein neues Spiel
     * @param spiel Das zu erstellende Spiel
     * @return Das erstellte Spiel mit ID
     */
    Spiel createSpiel(Spiel spiel);
    
    /**
     * Aktualisiert ein vorhandenes Spiel
     * @param id Die ID des zu aktualisierenden Spiels
     * @param spiel Die aktualisierten Spiel-Daten
     * @return Das aktualisierte Spiel
     */
    Spiel updateSpiel(Long id, Spiel spiel);
    
    /**
     * Löscht ein Spiel anhand seiner ID
     * @param id Die ID des zu löschenden Spiels
     */
    void deleteSpiel(Long id);
    
    /**
     * Filtert Spiele nach Mannschaft
     * @param mannschaftId Die ID der Mannschaft
     * @return Liste der gefilterten Spiele
     */
    List<Spiel> getSpieleByMannschaft(Long mannschaftId);
    
    /**
     * Filtert Spiele nach Liga
     * @param ligaName Der Name der Liga
     * @return Liste der gefilterten Spiele
     */
    List<Spiel> getSpieleByLiga(String ligaName);
    
    /**
     * Filtert Spiele nach Status
     * @param status Der Status der Spiele
     * @return Liste der gefilterten Spiele
     */
    List<Spiel> getSpieleByStatus(String status);
    
    /**
     * Filtert Spiele nach Datum
     * @param von Startdatum
     * @param bis Enddatum
     * @return Liste der gefilterten Spiele
     */
    List<Spiel> getSpieleByDatumBereich(LocalDate von, LocalDate bis);
}
