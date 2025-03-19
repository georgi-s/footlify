package com.sportverein.service;

import com.sportverein.entity.LigaEntity;
import com.sportverein.entity.MannschaftEntity;
import com.sportverein.entity.SpielEntity;
import com.sportverein.mapper.SpielMapper;
import com.sportverein.model.Spiel;
import com.sportverein.model.SpielStatus;
import com.sportverein.repository.LigaRepository;
import com.sportverein.repository.MannschaftRepository;
import com.sportverein.repository.SpielRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementierung des SpielService-Interfaces
 */
@Service
public class SpielServiceImpl implements SpielService {
    
    private final SpielRepository spielRepository;
    private final MannschaftRepository mannschaftRepository;
    private final LigaRepository ligaRepository;
    private final SpielMapper spielMapper;
    
    /**
     * Konstruktor mit Dependency Injection
     */
    public SpielServiceImpl(
            SpielRepository spielRepository,
            MannschaftRepository mannschaftRepository,
            LigaRepository ligaRepository,
            SpielMapper spielMapper) {
        this.spielRepository = spielRepository;
        this.mannschaftRepository = mannschaftRepository;
        this.ligaRepository = ligaRepository;
        this.spielMapper = spielMapper;
    }
    
    @Override
    public List<Spiel> getAllSpiele() {
        List<SpielEntity> entities = spielRepository.findAll();
        return entities.stream()
                .map(spielMapper::toModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public Spiel getSpielById(Long id) {
        SpielEntity entity = spielRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spiel mit ID " + id + " nicht gefunden"));
        return spielMapper.toModel(entity);
    }
    
    @Override
    public Spiel createSpiel(Spiel spiel) {
        // Validierungen
        if (spiel.getHeimMannschaft() == null || spiel.getHeimMannschaft().getId() == null) {
            throw new RuntimeException("Heimmannschaft muss angegeben werden");
        }
        
        if (spiel.getGastMannschaft() == null || spiel.getGastMannschaft().getId() == null) {
            throw new RuntimeException("Gastmannschaft muss angegeben werden");
        }
        
        if (spiel.getHeimMannschaft().getId().equals(spiel.getGastMannschaft().getId())) {
            throw new RuntimeException("Heimmannschaft und Gastmannschaft dürfen nicht identisch sein");
        }
        
        // Entität erstellen
        SpielEntity entity = spielMapper.toEntity(spiel);
        
        // Liga setzen, falls vorhanden
        if (spiel.getLiga() != null && !spiel.getLiga().isEmpty()) {
            LigaEntity liga = ligaRepository.findByName(spiel.getLiga())
                    .orElseThrow(() -> new RuntimeException("Liga mit Name " + spiel.getLiga() + " nicht gefunden"));
            entity.setLiga(liga);
        }
        
        // Speichern
        SpielEntity savedEntity = spielRepository.save(entity);
        return spielMapper.toModel(savedEntity);
    }
    
    @Override
    public Spiel updateSpiel(Long id, Spiel spiel) {
        // Prüfen, ob das Spiel existiert
        if (!spielRepository.existsById(id)) {
            throw new RuntimeException("Spiel mit ID " + id + " nicht gefunden");
        }
        
        // Validierungen
        if (spiel.getHeimMannschaft() == null || spiel.getHeimMannschaft().getId() == null) {
            throw new RuntimeException("Heimmannschaft muss angegeben werden");
        }
        
        if (spiel.getGastMannschaft() == null || spiel.getGastMannschaft().getId() == null) {
            throw new RuntimeException("Gastmannschaft muss angegeben werden");
        }
        
        if (spiel.getHeimMannschaft().getId().equals(spiel.getGastMannschaft().getId())) {
            throw new RuntimeException("Heimmannschaft und Gastmannschaft dürfen nicht identisch sein");
        }
        
        // Entität erstellen und ID setzen
        SpielEntity entity = spielMapper.toEntity(spiel);
        entity.setId(id);
        
        // Liga setzen, falls vorhanden
        if (spiel.getLiga() != null && !spiel.getLiga().isEmpty()) {
            LigaEntity liga = ligaRepository.findByName(spiel.getLiga())
                    .orElseThrow(() -> new RuntimeException("Liga mit Name " + spiel.getLiga() + " nicht gefunden"));
            entity.setLiga(liga);
        }
        
        // Speichern
        SpielEntity updatedEntity = spielRepository.save(entity);
        return spielMapper.toModel(updatedEntity);
    }
    
    @Override
    public void deleteSpiel(Long id) {
        // Prüfen, ob das Spiel existiert
        if (!spielRepository.existsById(id)) {
            throw new RuntimeException("Spiel mit ID " + id + " nicht gefunden");
        }
        
        // Löschen
        spielRepository.deleteById(id);
    }
    
    @Override
    public List<Spiel> getSpieleByMannschaft(Long mannschaftId) {
        // Mannschaft finden
        MannschaftEntity mannschaft = mannschaftRepository.findById(mannschaftId)
                .orElseThrow(() -> new RuntimeException("Mannschaft mit ID " + mannschaftId + " nicht gefunden"));
        
        // Spiele finden, bei denen die Mannschaft beteiligt ist
        List<SpielEntity> entities = spielRepository.findByHeimMannschaftOrGastMannschaft(mannschaft, mannschaft);
        
        // In Modelle konvertieren und zurückgeben
        return entities.stream()
                .map(spielMapper::toModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Spiel> getSpieleByLiga(String ligaName) {
        // Spiele nach Liga filtern
        List<SpielEntity> entities = spielRepository.findByLiga_Name(ligaName);
        
        // In Modelle konvertieren und zurückgeben
        return entities.stream()
                .map(spielMapper::toModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Spiel> getSpieleByStatus(String status) {
        try {
            // Status in Enum konvertieren
            SpielStatus spielStatus = SpielStatus.valueOf(status);
            
            // Spiele nach Status filtern
            List<SpielEntity> entities = spielRepository.findByStatus(spielStatus);
            
            // In Modelle konvertieren und zurückgeben
            return entities.stream()
                    .map(spielMapper::toModel)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ungültiger Status: " + status);
        }
    }
    
    @Override
    public List<Spiel> getSpieleByDatumBereich(LocalDate von, LocalDate bis) {
        // Validierung der Datumswerte
        if (von == null || bis == null) {
            throw new RuntimeException("Von- und Bis-Datum müssen angegeben werden");
        }
        
        if (von.isAfter(bis)) {
            throw new RuntimeException("Von-Datum muss vor oder gleich Bis-Datum sein");
        }
        
        // Spiele nach Datumsbereich filtern
        List<SpielEntity> entities = spielRepository.findByDatumBetween(von, bis);
        
        // In Modelle konvertieren und zurückgeben
        return entities.stream()
                .map(spielMapper::toModel)
                .collect(Collectors.toList());
    }
}
