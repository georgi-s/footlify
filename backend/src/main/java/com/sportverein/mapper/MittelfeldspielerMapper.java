package com.sportverein.mapper;

import com.sportverein.entity.MittelfeldspielerEntity;
import com.sportverein.model.Mittelfeldspieler;

public class MittelfeldspielerMapper {

    public static MittelfeldspielerEntity toEntity(Mittelfeldspieler model) {
        if(model == null) return null;
        return new MittelfeldspielerEntity(
                model.getNachname(),
                model.getVorname(),
                model.getGeburtsdatum(),
                model.getGespielteSpiele(),
                model.isGesperrt(),
                model.getVereinsbeitritt(),
                model.getRoteKarten(),
                model.getGelbeKarten(),
                model.getAnzahlVorlagen(),
                model.getTore(),
                model.getPassquote()
        );
    }

    public static Mittelfeldspieler toModel(MittelfeldspielerEntity entity) {
        if(entity == null) return null;
        // Hier wird die ID aus der Entity in den Domain-Objekt überführt (falls gewünscht)
        return new Mittelfeldspieler(
                entity.getId().intValue(),
                entity.getNachname(),
                entity.getVorname(),
                entity.getGeburtsdatum(),
                entity.getGespielteSpiele(),
                entity.isGesperrt(),
                entity.getVereinsbeitritt(),
                entity.getRoteKarten(),
                entity.getGelbeKarten(),
                entity.getAnzahlVorlagen(),
                entity.getTore(),
                entity.getPassquote()
        );
    }
}
