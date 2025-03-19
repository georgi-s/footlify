package com.sportverein.mapper;

import com.sportverein.dto.MannschaftDTO;
import com.sportverein.model.Formation;
import com.sportverein.model.Liga;
import com.sportverein.model.Mannschaft;
import com.sportverein.entity.MannschaftEntity;

public class MannschaftDTOMapper {

    // Konvertiert Domain in DTO
    public static MannschaftDTO toDTO(Mannschaft model) {
        if (model == null) return null;
        MannschaftDTO dto = new MannschaftDTO();
        // Falls im Domainmodell noch keine ID existiert, kann dies auch null bleiben.
        // Oft wird die ID erst beim Speichern in die Datenbank vergeben.
        dto.setId(model.getId());  
        dto.setName(model.getName());
        dto.setTrainer(model.getTrainer());
        // Da Formation und Liga Enums sind, wandeln wir sie in String um.
        dto.setFormation(model.getFormation() != null ? model.getFormation().name() : null);
        dto.setLiga(model.getLiga() != null ? model.getLiga().name() : null);
        return dto;
    }

    // Konvertiert DTO in Domain-Modell
    public static Mannschaft toModel(MannschaftDTO dto) {
        if (dto == null) return null;
        // Hier nehmen wir an, dass du in deinem Domain-Modell einen Konstruktor hast, der diese Werte akzeptiert.
        Mannschaft model = new Mannschaft(
                dto.getId(),
                dto.getName(),
                dto.getTrainer(),
                null, // Feldspieler: Diese Listen können später ergänzt werden.
                null, // Auswechselspieler
                dto.getFormation() != null ? Formation.valueOf(dto.getFormation()) : null,
                dto.getLiga() != null ? Liga.valueOf(dto.getLiga()) : null
        );
        return model;
    }

    // Optional: Konvertierung zwischen Entity und DTO mithilfe der existierenden Mapper
    public static MannschaftDTO fromEntity(MannschaftEntity entity) {
        if (entity == null) return null;
        MannschaftDTO dto = new MannschaftDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTrainer(entity.getTrainer());
        dto.setFormation(entity.getFormation() != null ? entity.getFormation().getBezeichnung() : null);
        dto.setLiga(entity.getLiga() != null ? entity.getLiga().getName() : null);
        return dto;
    }
}
