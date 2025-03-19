package com.sportverein.controller;

import com.sportverein.dto.SpielDTO;
import com.sportverein.mapper.SpielDTOMapper;
import com.sportverein.model.Spiel;
import com.sportverein.service.SpielService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Controller für Spiel-Operationen
 */
@RestController
@RequestMapping("/api/spiele")
public class SpielController {
    
    private final SpielService spielService;
    
    /**
     * Konstruktor mit Dependency Injection
     */
    public SpielController(SpielService spielService) {
        this.spielService = spielService;
    }
    
    /**
     * Gibt alle Spiele zurück
     * GET /api/spiele
     * @return Liste aller Spiele als DTOs
     */
    @GetMapping
    public List<SpielDTO> getAllSpiele() {
        List<Spiel> spiele = spielService.getAllSpiele();
        return spiele.stream()
                .map(SpielDTOMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Gibt ein Spiel anhand seiner ID zurück
     * GET /api/spiele/{id}
     * @param id Die ID des gesuchten Spiels
     * @return Das gefundene Spiel als DTO
     */
    @GetMapping("/{id}")
    public SpielDTO getSpielById(@PathVariable Long id) {
        Spiel spiel = spielService.getSpielById(id);
        return SpielDTOMapper.toDTO(spiel);
    }
    
    /**
     * Erstellt ein neues Spiel
     * POST /api/spiele
     * @param dto Die Daten des zu erstellenden Spiels
     * @return Das erstellte Spiel als DTO
     */
    @PostMapping
    public SpielDTO createSpiel(@RequestBody SpielDTO dto) {
        Spiel spiel = SpielDTOMapper.toModel(dto);
        Spiel created = spielService.createSpiel(spiel);
        return SpielDTOMapper.toDTO(created);
    }
    
    /**
     * Aktualisiert ein bestehendes Spiel
     * PUT /api/spiele/{id}
     * @param id Die ID des zu aktualisierenden Spiels
     * @param dto Die aktualisierten Daten
     * @return Das aktualisierte Spiel als DTO
     */
    @PutMapping("/{id}")
    public SpielDTO updateSpiel(@PathVariable Long id, @RequestBody SpielDTO dto) {
        Spiel spiel = SpielDTOMapper.toModel(dto);
        Spiel updated = spielService.updateSpiel(id, spiel);
        return SpielDTOMapper.toDTO(updated);
    }
    
    /**
     * Löscht ein Spiel anhand seiner ID
     * DELETE /api/spiele/{id}
     * @param id Die ID des zu löschenden Spiels
     */
    @DeleteMapping("/{id}")
    public void deleteSpiel(@PathVariable Long id) {
        spielService.deleteSpiel(id);
    }
    
    /**
     * Filtert Spiele nach verschiedenen Kriterien
     * GET /api/spiele/filter
     * @param mannschaftId Optional: ID der Mannschaft
     * @param liga Optional: Name der Liga
     * @param status Optional: Status des Spiels
     * @param von Optional: Startdatum
     * @param bis Optional: Enddatum
     * @return Liste der gefilterten Spiele als DTOs
     */
    @GetMapping("/filter")
    public List<SpielDTO> filterSpiele(
            @RequestParam(required = false) Long mannschaftId,
            @RequestParam(required = false) String liga,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate von,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bis) {
        
        List<Spiel> filtered;
        
        // Filter anwenden
        if (mannschaftId != null) {
            filtered = spielService.getSpieleByMannschaft(mannschaftId);
        } else if (liga != null && !liga.isEmpty()) {
            filtered = spielService.getSpieleByLiga(liga);
        } else if (status != null && !status.isEmpty()) {
            filtered = spielService.getSpieleByStatus(status);
        } else if (von != null && bis != null) {
            filtered = spielService.getSpieleByDatumBereich(von, bis);
        } else {
            filtered = spielService.getAllSpiele();
        }
        
        // In DTOs konvertieren und zurückgeben
        return filtered.stream()
                .map(SpielDTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}
