package com.sportverein.service;

import com.sportverein.model.Mannschaft;
import com.sportverein.model.Formation;
import com.sportverein.model.Liga;
import com.sportverein.repository.MannschaftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MannschaftService {
    
    @Autowired
    private MannschaftRepository mannschaftRepository;
    
    public List<Mannschaft> getAllMannschaften() {
        return mannschaftRepository.findAll();
    }
    
    public Optional<Mannschaft> getMannschaftById(Long id) {
        return mannschaftRepository.findById(id);
    }
    
    public List<Mannschaft> getMannschaftenByLiga(Liga liga) {
        return mannschaftRepository.findByLiga(liga);
    }
    
    public Mannschaft getMannschaftByName(String name) {
        return mannschaftRepository.findByName(name);
    }
    
    public Mannschaft saveMannschaft(Mannschaft mannschaft) {
        return mannschaftRepository.save(mannschaft);
    }
    
    public void deleteMannschaft(Long id) {
        mannschaftRepository.deleteById(id);
    }
}
