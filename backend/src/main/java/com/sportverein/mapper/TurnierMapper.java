package com.sportverein.mapper;

import com.sportverein.entity.TurnierEntity;
import com.sportverein.model.Turnier;
import java.util.stream.Collectors;
import com.sportverein.mapper.MannschaftMapper;

public class TurnierMapper {

    public static TurnierEntity toEntity(Turnier model) {
        if(model == null) return null;
        TurnierEntity entity = new TurnierEntity();
        entity.setOrt(model.getOrt());
        entity.setDatum(model.getDatum());
        entity.setInsgPreisgeld(model.getInsgPreisgeld());
        if(model.getTeilnehmer() != null) {
            entity.setTeilnehmer(model.getTeilnehmer().stream()
                .map(MannschaftMapper::toEntity)
                .collect(Collectors.toList()));
        }
        // Sieger wird meist später gesetzt
        return entity;
    }

    public static Turnier toModel(TurnierEntity entity) {
        if(entity == null) return null;
        Turnier model = new Turnier(
                entity.getOrt(),
                entity.getDatum(),
                entity.getInsgPreisgeld(),
                entity.getTeilnehmer() != null ? 
                    entity.getTeilnehmer().stream()
                        .map(MannschaftMapper::toModel)
                        .collect(Collectors.toList()) : null
        );
        model.setSiegerMannschaft(entity.getSiegerMannschaft() != null ? MannschaftMapper.toModel(entity.getSiegerMannschaft()) : null);
        return model;
    }
}
