package com.sportverein.mapper;

import com.sportverein.entity.VerteidigerEntity;
import com.sportverein.model.Verteidiger;

public class VerteidigerMapper {

    public static VerteidigerEntity toEntity(Verteidiger model) {
        if(model == null) return null;
        return new VerteidigerEntity(
                model.getNachname(),
                model.getVorname(),
                model.getGeburtsdatum(),
                model.getGespielteSpiele(),
                model.isGesperrt(),
                model.getVereinsbeitritt(),
                model.getRoteKarten(),
                model.getGelbeKarten(),
                model.getGeblockteAngriffe(),
                model.getGewonneneZweikaempfe(),
                model.getPassquote()
        );
    }

    public static Verteidiger toModel(VerteidigerEntity entity) {
        if(entity == null) return null;
        return new Verteidiger(
                entity.getId().intValue(),
                entity.getNachname(),
                entity.getVorname(),
                entity.getGeburtsdatum(),
                entity.getGespielteSpiele(),
                entity.isGesperrt(),
                entity.getVereinsbeitritt(),
                entity.getRoteKarten(),
                entity.getGelbeKarten(),
                entity.getGeblockteAngriffe(),
                entity.getGewonneneZweikaempfe(),
                entity.getPassquote()
        );
    }
}
