package com.sportverein.service;

import com.sportverein.entity.Turnier;
import com.sportverein.repository.TurnierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnierService {
    
    @Autowired
    private TurnierRepository turnierRepository;
    
    public List<Turnier> getAllTurniere() {
        return turnierRepository.findAll();
    }
    
    public Optional<Turnier> getTurnierById(Long id) {
        return turnierRepository.findById(id);
    }
    
    public List<Turnier> getTurniereByOrt(String ort) {
        return turnierRepository.findByOrt(ort);
    }
    
    public List<Turnier> getTurniereByDatum(String datum) {
        return turnierRepository.findByDatum(datum);
    }
    
    public List<Turnier> getTurniereByTeilnehmer(Long mannschaftId) {
        return turnierRepository.findByTeilnehmerId(mannschaftId);
    }
    
    public Turnier saveTurnier(Turnier turnier) {
        return turnierRepository.save(turnier);
    }
    
    public void deleteTurnier(Long id) {
        turnierRepository.deleteById(id);
    }
}
