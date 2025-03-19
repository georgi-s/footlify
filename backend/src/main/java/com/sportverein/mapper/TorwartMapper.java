package com.sportverein.mapper;

import com.sportverein.entity.TorwartEntity;
import com.sportverein.model.Torwart;

public class TorwartMapper {

    public static TorwartEntity toEntity(Torwart model) {
        if(model == null) return null;
        return new TorwartEntity(
                model.getNachname(),
                model.getVorname(),
                model.getGeburtsdatum(),
                model.getGespielteSpiele(),
                model.isGesperrt(),
                model.getVereinsbeitritt(),
                model.getRoteKarten(),
                model.getGelbeKarten(),
                model.getSpieleOhneGegentor(),
                model.getGegentore(),
                model.getHaltequote()
        );
    }

    public static Torwart toModel(TorwartEntity entity) {
        if(entity == null) return null;
        return new Torwart(
                entity.getId().intValue(),
                entity.getNachname(),
                entity.getVorname(),
                entity.getGeburtsdatum(),
                entity.getGespielteSpiele(),
                entity.isGesperrt(),
                entity.getVereinsbeitritt(),
                entity.getRoteKarten(),
                entity.getGelbeKarten(),
                entity.getSpieleOhneGegentor(),
                entity.getGegentore(),
                entity.getHaltequote()
        );
    }
}
