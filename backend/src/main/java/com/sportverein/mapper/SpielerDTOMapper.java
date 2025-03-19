package com.sportverein.mapper;

import com.sportverein.dto.SpielerDTO;
import com.sportverein.model.Spieler;
import com.sportverein.model.Mittelfeldspieler;
import com.sportverein.model.Stuermer;
import com.sportverein.model.Torwart;
import com.sportverein.model.Verteidiger;

public class SpielerDTOMapper {

    public static SpielerDTO toDTO(Spieler spieler) {
        if (spieler == null) return null;
        SpielerDTO dto = new SpielerDTO();
        // Gemeinsame Felder
        dto.setId((long) spieler.getPlayerId()); // Falls playerId als int vorhanden ist, casten oder anpassen
        dto.setNachname(spieler.getNachname());
        dto.setVorname(spieler.getVorname());
        dto.setGeburtsdatum(spieler.getGeburtsdatum());
        dto.setGespielteSpiele(spieler.getGespielteSpiele());
        dto.setGesperrt(spieler.isGesperrt());
        dto.setVereinsbeitritt(spieler.getVereinsbeitritt());
        dto.setRoteKarten(spieler.getRoteKarten());
        dto.setGelbeKarten(spieler.getGelbeKarten());
        
        // Bestimme den Typ anhand der Instanz
        if (spieler instanceof Mittelfeldspieler) {
            dto.setSpielerType("Mittelfeldspieler");
            // Falls vorhanden, könntest du hier auch spezifische Felder setzen:
            // dto.setAnzahlVorlagen(...), dto.setTore(...), dto.setPassquote(...);
        } else if (spieler instanceof Stuermer) {
            dto.setSpielerType("Stürmer");
        } else if (spieler instanceof Torwart) {
            dto.setSpielerType("Torwart");
        } else if (spieler instanceof Verteidiger) {
            dto.setSpielerType("Verteidiger");
        } else {
            dto.setSpielerType("Unbekannt");
        }
        return dto;
    }

    // Methode, um vom DTO in ein konkretes Domain-Modell zu mappen.
    public static Spieler toModel(SpielerDTO dto) {
        if (dto == null) return null;
        String type = dto.getSpielerType();
        // Für die Erstellung übergeben wir einen Standardwert (z. B. 0) für playerId, da diese typischerweise vom System generiert wird.
        switch (type) {
            case "Mittelfeldspieler":
                return new Mittelfeldspieler(
                    0, // playerId, wird später generiert
                    dto.getNachname(),
                    dto.getVorname(),
                    dto.getGeburtsdatum(),
                    dto.getGespielteSpiele(),
                    dto.isGesperrt(),
                    dto.getVereinsbeitritt(),
                    dto.getRoteKarten(),
                    dto.getGelbeKarten(),
                    // Hier nehmen wir an, dass die spezifischen Felder in dto vorhanden sind:
                    dto.getAnzahlVorlagen() != null ? dto.getAnzahlVorlagen() : 0,
                    dto.getTore() != null ? dto.getTore() : 0,
                    dto.getPassquote() != null ? dto.getPassquote() : 0.0
                );
            case "Stürmer":
                // Beispiel: Falls du für Stürmer andere spezifische Felder hast, musst du diese hier anpassen.
                return new Stuermer(
                    0,
                    dto.getNachname(),
                    dto.getVorname(),
                    dto.getGeburtsdatum(),
                    dto.getGespielteSpiele(),
                    dto.isGesperrt(),
                    dto.getVereinsbeitritt(),
                    dto.getRoteKarten(),
                    dto.getGelbeKarten(),
                    dto.getTore() != null ? dto.getTore() : 0,
                    0.0,  // Beispiel: schussgenauigkeit
                    0.0   // Beispiel: chancenverwertung
                );
            case "Torwart":
                return new Torwart(
                    0,
                    dto.getNachname(),
                    dto.getVorname(),
                    dto.getGeburtsdatum(),
                    dto.getGespielteSpiele(),
                    dto.isGesperrt(),
                    dto.getVereinsbeitritt(),
                    dto.getRoteKarten(),
                    dto.getGelbeKarten(),
                    0,    // spieleOhneGegentor
                    0,    // gegentore
                    0.0   // haltequote
                );
            case "Verteidiger":
                return new Verteidiger(
                    0,
                    dto.getNachname(),
                    dto.getVorname(),
                    dto.getGeburtsdatum(),
                    dto.getGespielteSpiele(),
                    dto.isGesperrt(),
                    dto.getVereinsbeitritt(),
                    dto.getRoteKarten(),
                    dto.getGelbeKarten(),
                    0,    // geblockteAngriffe
                    0,    // gewonneneZweikaempfe
                    0.0   // passquote, sofern spezifisch anders
                );
            default:
                throw new IllegalArgumentException("Unbekannter Spielertyp: " + type);
        }
    }
}
