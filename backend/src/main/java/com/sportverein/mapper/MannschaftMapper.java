package com.sportverein.mapper;

import com.sportverein.entity.FormationEntity;
import com.sportverein.entity.LigaEntity;
import com.sportverein.entity.MannschaftEntity;
import com.sportverein.model.Formation;
import com.sportverein.model.Liga;
import com.sportverein.model.Mannschaft;
import com.sportverein.repository.FormationRepository;
import com.sportverein.repository.LigaRepository;
import org.springframework.stereotype.Component;

@Component
public class MannschaftMapper {

    private static FormationRepository formationRepository;
    private static LigaRepository ligaRepository;

    public MannschaftMapper(FormationRepository formationRepository, LigaRepository ligaRepository) {
        MannschaftMapper.formationRepository = formationRepository;
        MannschaftMapper.ligaRepository = ligaRepository;
    }

    public static MannschaftEntity toEntity(Mannschaft model) {
        if(model == null) {
            return null;
        }
        MannschaftEntity entity = new MannschaftEntity();
        entity.setName(model.getName());
        entity.setTrainer(model.getTrainer());
        
        // Formation und Liga von den Enum-Werten zu Entity-Referenzen konvertieren
        if (model.getFormation() != null) {
            // Hier vereinfacht - in der Praxis würde man anhand des Formation-Enum den passenden FormationEntity suchen
            FormationEntity formationEntity = formationRepository.findByBezeichnung(model.getFormation().name())
                    .orElse(null);
            entity.setFormation(formationEntity);
        }
        
        if (model.getLiga() != null) {
            // Hier vereinfacht - in der Praxis würde man anhand des Liga-Enum den passenden LigaEntity suchen
            LigaEntity ligaEntity = ligaRepository.findByName(model.getLiga().name())
                    .orElse(null);
            entity.setLiga(ligaEntity);
        }
        
        // Optional: Hier könntest du Spieler (Feld- und Auswechselspieler) zusammenführen und mappen
        return entity;
    }

    public static Mannschaft toModel(MannschaftEntity entity) {
        if(entity == null) {
            return null;
        }
        
        // Formation und Liga von Entity-Referenzen zu Enum-Werten konvertieren
        Formation formation = null;
        if (entity.getFormation() != null) {
            try {
                formation = Formation.valueOf(entity.getFormation().getBezeichnung());
            } catch (IllegalArgumentException e) {
                // Für den Fall, dass der Bezeichnung-Wert nicht direkt dem Enum-Namen entspricht
                // In einer realen Anwendung könnte man hier eine Mapping-Logik implementieren
            }
        }
        
        Liga liga = null;
        if (entity.getLiga() != null) {
            try {
                liga = Liga.valueOf(entity.getLiga().getName());
            } catch (IllegalArgumentException e) {
                // Für den Fall, dass der Name-Wert nicht direkt dem Enum-Namen entspricht
                // In einer realen Anwendung könnte man hier eine Mapping-Logik implementieren
            }
        }
        
        // Spieler werden hier vorerst nicht gemappt (kannst du später ergänzen)
        return new Mannschaft(
                entity.getId(),
                entity.getName(),
                entity.getTrainer(),
                null,  // Feldspieler
                null,  // Auswechselspieler
                formation,
                liga
        );
    }
}
