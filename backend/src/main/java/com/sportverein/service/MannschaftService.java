package com.sportverein.service;

import com.sportverein.entity.MannschaftEntity;
import com.sportverein.mapper.MannschaftMapper;
import com.sportverein.model.Mannschaft;
import com.sportverein.repository.MannschaftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MannschaftService {

    private final MannschaftRepository repository;

    public MannschaftService(MannschaftRepository repository) {
        this.repository = repository;
    }

    // Alle Mannschaften abrufen
    public List<Mannschaft> getAllMannschaften() {
        List<MannschaftEntity> entities = repository.findAll();
        return entities.stream()
                .map(MannschaftMapper::toModel)
                .collect(Collectors.toList());
    }

    // Eine neue Mannschaft erstellen
    public Mannschaft createMannschaft(Mannschaft mannschaft) {
        MannschaftEntity entity = MannschaftMapper.toEntity(mannschaft);
        MannschaftEntity savedEntity = repository.save(entity);
        Mannschaft saved = MannschaftMapper.toModel(savedEntity);
        saved.setId(savedEntity.getId());  // Übertrage die generierte ID ins Domain-Modell
        return saved;
    }

    // Mannschaft anhand der ID abrufen
    public Mannschaft getMannschaftById(Long id) {
        MannschaftEntity entity = repository.findById(id).orElse(null);
        Mannschaft model = MannschaftMapper.toModel(entity);
        if(model != null) {
            model.setId(entity.getId());
        }
        return model;
    }

    // Mannschaft aktualisieren
    public Mannschaft updateMannschaft(Long id, Mannschaft updatedMannschaft) {
        return repository.findById(id).map(entity -> {
            entity.setName(updatedMannschaft.getName());
            entity.setTrainer(updatedMannschaft.getTrainer());
            // Formation und Liga werden über den Mapper konvertiert
            MannschaftEntity updatedEntity = MannschaftMapper.toEntity(updatedMannschaft);
            entity.setFormation(updatedEntity.getFormation());
            entity.setLiga(updatedEntity.getLiga());
            MannschaftEntity savedEntity = repository.save(entity);
            Mannschaft updatedModel = MannschaftMapper.toModel(savedEntity);
            updatedModel.setId(savedEntity.getId());
            return updatedModel;
        }).orElse(null);
    }

    // Mannschaft löschen
    public void deleteMannschaft(Long id) {
        repository.deleteById(id);
    }
}
