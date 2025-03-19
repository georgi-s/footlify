package com.sportverein.mapper;

import com.sportverein.entity.SpielerEntity;
import com.sportverein.model.Spieler;
import com.sportverein.model.Mittelfeldspieler;
import com.sportverein.model.Stuermer;
import com.sportverein.model.Torwart;
import com.sportverein.model.Verteidiger;

import java.util.Date;

public class SpielerMapper {

    public static SpielerEntity toEntity(Spieler model) {
        if(model == null) return null;
        
        SpielerEntity entity = new SpielerEntity();
        // ID konvertieren von int zu Long
        entity.setId(model.getPlayerId() > 0 ? Long.valueOf(model.getPlayerId()) : null);
        entity.setNachname(model.getNachname());
        entity.setVorname(model.getVorname());
        entity.setGeburtsdatum(model.getGeburtsdatum());
        entity.setGespielteSpiele(model.getGespielteSpiele());
        entity.setGesperrt(model.isGesperrt());
        entity.setVereinsbeitritt(model.getVereinsbeitritt());
        entity.setRoteKarten(model.getRoteKarten());
        entity.setGelbeKarten(model.getGelbeKarten());
        
        // Position basierend auf dem Spielertyp setzen
        if(model instanceof Mittelfeldspieler) {
            entity.setPosition("Mittelfeld");
        } else if(model instanceof Stuermer) {
            entity.setPosition("Sturm");
        } else if(model instanceof Torwart) {
            entity.setPosition("Tor");
        } else if(model instanceof Verteidiger) {
            entity.setPosition("Verteidigung");
        }
        
        return entity;
    }

    public static Spieler toModel(SpielerEntity entity) {
        if(entity == null) return null;
        
        String position = entity.getPosition();
        
        // ID aus der Entity ermitteln (Long zu int konvertieren)
        int playerId = entity.getId() != null ? entity.getId().intValue() : 0;
        String nachname = entity.getNachname();
        String vorname = entity.getVorname();
        Date geburtsdatum = entity.getGeburtsdatum();
        int gespielteSpiele = entity.getGespielteSpiele();
        boolean gesperrt = entity.isGesperrt();
        Date vereinsbeitritt = entity.getVereinsbeitritt();
        int roteKarten = entity.getRoteKarten();
        int gelbeKarten = entity.getGelbeKarten();
        
        // Basierend auf der Position den richtigen Spielertyp erstellen
        if (position != null) {
            switch (position) {
                case "Mittelfeld":
                    return new Mittelfeldspieler(playerId, nachname, vorname, geburtsdatum, 
                            gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten,
                            0, 0, 0.0); // Default-Werte für Mittelfeldspieler-spezifische Attribute
                
                case "Sturm":
                    return new Stuermer(playerId, nachname, vorname, geburtsdatum, 
                            gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten,
                            0, 0.0, 0.0); // Default-Werte für Stürmer-spezifische Attribute
                
                case "Tor":
                    return new Torwart(playerId, nachname, vorname, geburtsdatum, 
                            gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten,
                            0, 0, 0.0); // Default-Werte für Torwart-spezifische Attribute
                
                case "Verteidigung":
                    return new Verteidiger(playerId, nachname, vorname, geburtsdatum, 
                            gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten,
                            0, 0, 0.0); // Default-Werte für Verteidiger-spezifische Attribute
                
                default:
                    // Wir können hier keinen abstrakten Spieler erstellen, daher verwenden wir Mittelfeldspieler als Standard
                    return new Mittelfeldspieler(playerId, nachname, vorname, geburtsdatum, 
                            gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten,
                            0, 0, 0.0);
            }
        } else {
            // Wenn keine Position gesetzt ist, verwenden wir Mittelfeldspieler als Standard
            return new Mittelfeldspieler(playerId, nachname, vorname, geburtsdatum, 
                    gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten,
                    0, 0, 0.0);
        }
    }
}
