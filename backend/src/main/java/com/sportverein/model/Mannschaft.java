package com.sportverein.model;

import java.util.List;

public class Mannschaft {
    private Long id;
    private String name;
    private String trainer;
    private List<Spieler> feldspieler;        // 11 Stammspieler
    private List<Spieler> auswechselspieler;   // Ersatzspieler
    private Formation formation;
    private Liga liga;
    
    /**
     * Standardkonstruktor für ORM und Mapping
     */
    public Mannschaft() {
        // Standardkonstruktor für ORM und Mapping
    }

    public Mannschaft(Long id, String name, String trainer, List<Spieler> feldspieler, List<Spieler> auswechselspieler, Formation formation, Liga liga) {
        this.id = id;
        this.name = name;
        this.trainer = trainer;
        this.feldspieler = feldspieler;
        this.auswechselspieler = auswechselspieler;
        this.formation = formation;
        this.liga = liga;
    }
    
    /**
     * Prüft, ob die Aufstellung der Mannschaft der vorgegebenen Formation entspricht.
     */
    public boolean aufstellungPruefen() {
        if (feldspieler.size() != 11) {
            return false;
        }

        int verteidigerCount = 0;
        int mittelfeldspielerCount = 0;
        int stuermerCount = 0;

        for (Spieler spieler : feldspieler) {
            if (spieler instanceof Verteidiger) {
                verteidigerCount++;
            } else if (spieler instanceof Mittelfeldspieler) {
                mittelfeldspielerCount++;
            } else if (spieler instanceof Stuermer) {
                stuermerCount++;
            }
        }

        return verteidigerCount == formation.getVerteidigerAnzahl() &&
               mittelfeldspielerCount == formation.getMittelfeldspielerAnzahl() &&
               stuermerCount == formation.getStuermerAnzahl();
    }
    
    /**
     * Berechnet die durchschnittliche Bewertung der Feldspieler.
     */
    public double mannschaftsbewertungAusgeben() {
        double bewertung = 0;
        for (Spieler spieler : feldspieler) {
            bewertung += spieler.spielerBewertung();
        }
        return Math.round((bewertung / feldspieler.size()) * 100.0) / 100.0;
    }

    // Getter und Setter (ggf. weitere Methoden)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getTrainer() {
        return trainer;
    }
    
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<Spieler> getFeldspieler() {
        return feldspieler;
    }

    public List<Spieler> getAuswechselspieler() {
        return auswechselspieler;
    }

    public Formation getFormation() {
        return formation;
    }

    public Liga getLiga() {
        return liga;
    }
}
