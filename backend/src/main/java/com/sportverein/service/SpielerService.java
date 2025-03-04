package com.sportverein.service;

import com.sportverein.entity.*;
import com.sportverein.repository.SpielerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpielerService {
    
    @Autowired
    private SpielerRepository spielerRepository;
    
    // Allgemeine Spieler-Methoden
    public List<Spieler> getAllSpieler() {
        return spielerRepository.findAll();
    }
    
    public Optional<Spieler> getSpielerById(Long id) {
        return spielerRepository.findById(id);
    }
    
    public List<Spieler> getSpielerByMannschaft(Long mannschaftId) {
        return spielerRepository.findByMannschaftId(mannschaftId);
    }
    
    public void deleteSpieler(Long id) {
        spielerRepository.deleteById(id);
    }
    
    // Torwart-spezifische Methoden
    public List<Torwart> getAllTorwart() {
        return spielerRepository.findAllTorwart();
    }
    
    public Optional<Torwart> getTorwartById(Long id) {
        return spielerRepository.findById(id)
                .filter(spieler -> spieler instanceof Torwart)
                .map(spieler -> (Torwart) spieler);
    }
    
    public Torwart saveTorwart(Torwart torwart) {
        return spielerRepository.save(torwart);
    }
    
    // Verteidiger-spezifische Methoden
    public List<Verteidiger> getAllVerteidiger() {
        return spielerRepository.findAllVerteidiger();
    }
    
    public Optional<Verteidiger> getVerteidigerById(Long id) {
        return spielerRepository.findById(id)
                .filter(spieler -> spieler instanceof Verteidiger)
                .map(spieler -> (Verteidiger) spieler);
    }
    
    public Verteidiger saveVerteidiger(Verteidiger verteidiger) {
        return spielerRepository.save(verteidiger);
    }
    
    // Mittelfeldspieler-spezifische Methoden
    public List<Mittelfeldspieler> getAllMittelfeldspieler() {
        return spielerRepository.findAllMittelfeldspieler();
    }
    
    public Optional<Mittelfeldspieler> getMittelfeldspielerById(Long id) {
        return spielerRepository.findById(id)
                .filter(spieler -> spieler instanceof Mittelfeldspieler)
                .map(spieler -> (Mittelfeldspieler) spieler);
    }
    
    public Mittelfeldspieler saveMittelfeldspieler(Mittelfeldspieler mittelfeldspieler) {
        return spielerRepository.save(mittelfeldspieler);
    }
    
    // Stürmer-spezifische Methoden
    public List<Stuermer> getAllStuermer() {
        return spielerRepository.findAllStuermer();
    }
    
    public Optional<Stuermer> getStuermerById(Long id) {
        return spielerRepository.findById(id)
                .filter(spieler -> spieler instanceof Stuermer)
                .map(spieler -> (Stuermer) spieler);
    }
    
    public Stuermer saveStuermer(Stuermer stuermer) {
        return spielerRepository.save(stuermer);
    }
}
