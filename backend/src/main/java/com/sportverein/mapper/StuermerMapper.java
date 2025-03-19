package com.sportverein.mapper;

import com.sportverein.entity.StuermerEntity;
import com.sportverein.model.Stuermer;

public class StuermerMapper {

    public static StuermerEntity toEntity(Stuermer model) {
        if(model == null) return null;
        return new StuermerEntity(
                model.getNachname(),
                model.getVorname(),
                model.getGeburtsdatum(),
                model.getGespielteSpiele(),
                model.isGesperrt(),
                model.getVereinsbeitritt(),
                model.getRoteKarten(),
                model.getGelbeKarten(),
                model.getGeschosseneTore(),
                model.getSchussgenauigkeit(),
                model.getChancenverwertung()
        );
    }

    public static Stuermer toModel(StuermerEntity entity) {
        if(entity == null) return null;
        return new Stuermer(
                entity.getId().intValue(),
                entity.getNachname(),
                entity.getVorname(),
                entity.getGeburtsdatum(),
                entity.getGespielteSpiele(),
                entity.isGesperrt(),
                entity.getVereinsbeitritt(),
                entity.getRoteKarten(),
                entity.getGelbeKarten(),
                entity.getGeschosseneTore(),
                entity.getSchussgenauigkeit(),
                entity.getChancenverwertung()
        );
    }
}
