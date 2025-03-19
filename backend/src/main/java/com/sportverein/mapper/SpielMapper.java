package com.sportverein.mapper;

import com.sportverein.entity.MannschaftEntity;
import com.sportverein.entity.SpielEntity;
import com.sportverein.model.Spiel;
import com.sportverein.model.SpielStatus;
import com.sportverein.repository.MannschaftRepository;
import org.springframework.stereotype.Component;

/**
 * Mapper zum Konvertieren zwischen Spiel-Entitäten und Domänenmodellen
 */
@Component
public class SpielMapper {
    
    private final MannschaftRepository mannschaftRepository;
    
    public SpielMapper(MannschaftRepository mannschaftRepository) {
        this.mannschaftRepository = mannschaftRepository;
    }
    
    /**
     * Konvertiert eine SpielEntity in ein Spiel-Domänenmodell
     * @param entity Die zu konvertierende Entität
     * @return Das konvertierte Domänenmodell
     */
    public Spiel toModel(SpielEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Spiel model = new Spiel();
        model.setId(entity.getId());
        
        // Mannschaften konvertieren
        if (entity.getHeimMannschaft() != null) {
            model.setHeimMannschaft(MannschaftMapper.toModel(entity.getHeimMannschaft()));
        }
        
        if (entity.getGastMannschaft() != null) {
            model.setGastMannschaft(MannschaftMapper.toModel(entity.getGastMannschaft()));
        }
        
        model.setDatum(entity.getDatum());
        model.setUhrzeit(entity.getUhrzeit());
        model.setOrt(entity.getOrt());
        
        if (entity.getStatus() != null) {
            model.setStatus(entity.getStatus().name());
        }
        
        model.setHeimTore(entity.getHeimTore());
        model.setGastTore(entity.getGastTore());
        
        if (entity.getLiga() != null) {
            model.setLiga(entity.getLiga().getName());
        }
        
        return model;
    }
    
    /**
     * Konvertiert ein Spiel-Domänenmodell in eine SpielEntity
     * @param model Das zu konvertierende Domänenmodell
     * @return Die konvertierte Entität
     */
    public SpielEntity toEntity(Spiel model) {
        if (model == null) {
            return null;
        }
        
        SpielEntity entity = new SpielEntity();
        entity.setId(model.getId());
        
        // Heimmannschaft setzen
        if (model.getHeimMannschaft() != null && model.getHeimMannschaft().getId() != null) {
            MannschaftEntity heimMannschaft = mannschaftRepository.findById(model.getHeimMannschaft().getId())
                    .orElseThrow(() -> new RuntimeException("Heimmannschaft mit ID " + model.getHeimMannschaft().getId() + " nicht gefunden"));
            entity.setHeimMannschaft(heimMannschaft);
        }
        
        // Gastmannschaft setzen
        if (model.getGastMannschaft() != null && model.getGastMannschaft().getId() != null) {
            MannschaftEntity gastMannschaft = mannschaftRepository.findById(model.getGastMannschaft().getId())
                    .orElseThrow(() -> new RuntimeException("Gastmannschaft mit ID " + model.getGastMannschaft().getId() + " nicht gefunden"));
            entity.setGastMannschaft(gastMannschaft);
        }
        
        entity.setDatum(model.getDatum());
        entity.setUhrzeit(model.getUhrzeit());
        entity.setOrt(model.getOrt());
        
        if (model.getStatus() != null) {
            try {
                entity.setStatus(SpielStatus.valueOf(model.getStatus()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Ungültiger Spielstatus: " + model.getStatus());
            }
        }
        
        entity.setHeimTore(model.getHeimTore());
        entity.setGastTore(model.getGastTore());
        
        // Liga wird im Service gesetzt, da hier nur die ID vorhanden ist
        
        return entity;
    }
}
