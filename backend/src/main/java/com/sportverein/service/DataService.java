// backend/src/main/java/com/sportverein/service/DataService.java
package com.sportverein.service;

import com.sportverein.entity.*;
import com.sportverein.repository.SpielerRepository;
import com.sportverein.repository.MannschaftRepository;
import com.sportverein.repository.TurnierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DataService {
    private final SpielerRepository spielerRepository;
    private final MannschaftRepository mannschaftRepository;
    private final TurnierRepository turnierRepository;

    public DataService(
            SpielerRepository spielerRepository,
            MannschaftRepository mannschaftRepository,
            TurnierRepository turnierRepository) {
        this.spielerRepository = spielerRepository;
        this.mannschaftRepository = mannschaftRepository;
        this.turnierRepository = turnierRepository;
    }

    // Spieler-Management
    public List<Spieler> getAllSpieler() {
        return spielerRepository.findAll();
    }

    public Optional<Spieler> getSpielerById(Long id) {
        return spielerRepository.findById(id);
    }

    public Spieler saveSpieler(Spieler spieler) {
        return spielerRepository.save(spieler);
    }

    public void deleteSpieler(Spieler spieler) {
        spielerRepository.delete(spieler);
    }

    // Mannschaft-Management
    public List<Mannschaft> getAllMannschaften() {
        return mannschaftRepository.findAll();
    }

    public Optional<Mannschaft> getMannschaftById(Long id) {
        return mannschaftRepository.findById(id);
    }

    public Mannschaft saveMannschaft(Mannschaft mannschaft) {
        return mannschaftRepository.save(mannschaft);
    }

    public void deleteMannschaft(Mannschaft mannschaft) {
        mannschaftRepository.delete(mannschaft);
    }

    // Turnier-Management
    public List<Turnier> getAllTurniere() {
        return turnierRepository.findAll();
    }

    public Optional<Turnier> getTurnierById(Long id) {
        return turnierRepository.findById(id);
    }

    public Turnier saveTurnier(Turnier turnier) {
        return turnierRepository.save(turnier);
    }

    public void deleteTurnier(Turnier turnier) {
        turnierRepository.delete(turnier);
    }
}
