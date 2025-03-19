package com.sportverein.service;

import com.sportverein.entity.SpielerEntity;
import com.sportverein.mapper.SpielerMapper;
import com.sportverein.model.Spieler;
import com.sportverein.repository.SpielerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpielerService {

    private final SpielerRepository repository;

    public SpielerService(SpielerRepository repository) {
        this.repository = repository;
    }

    // Alle Spieler abrufen
    public List<Spieler> getAllSpieler() {
        List<SpielerEntity> entities = repository.findAll();
        return entities.stream()
                .map(SpielerMapper::toModel)
                .collect(Collectors.toList());
    }

    // Spieler anhand der ID abrufen
    public Spieler getSpielerById(Long id) {
        SpielerEntity entity = repository.findById(id).orElse(null);
        return SpielerMapper.toModel(entity);
    }

    // Neuen Spieler erstellen
    public Spieler createSpieler(Spieler spieler) {
        SpielerEntity entity = SpielerMapper.toEntity(spieler);
        SpielerEntity savedEntity = repository.save(entity);
        return SpielerMapper.toModel(savedEntity);
    }

    // Spieler aktualisieren
    public Spieler updateSpieler(Long id, Spieler updatedSpieler) {
        return repository.findById(id)
                .map(entity -> {
                    // Aktualisiere die grundlegenden Felder
                    entity.setNachname(updatedSpieler.getNachname());
                    entity.setVorname(updatedSpieler.getVorname());
                    entity.setGeburtsdatum(updatedSpieler.getGeburtsdatum());
                    entity.setGespielteSpiele(updatedSpieler.getGespielteSpiele());
                    entity.setGesperrt(updatedSpieler.isGesperrt());
                    entity.setVereinsbeitritt(updatedSpieler.getVereinsbeitritt());
                    entity.setRoteKarten(updatedSpieler.getRoteKarten());
                    entity.setGelbeKarten(updatedSpieler.getGelbeKarten());
                    // Bei Bedarf: Aktualisiere weitere Felder, z. B. positionsspezifische Attribute.
                    SpielerEntity savedEntity = repository.save(entity);
                    return SpielerMapper.toModel(savedEntity);
                })
                .orElse(null);
    }

    // Spieler löschen
    public void deleteSpieler(Long id) {
        repository.deleteById(id);
    }
}
