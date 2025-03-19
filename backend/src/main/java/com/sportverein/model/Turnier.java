package com.sportverein.model;

import java.util.Date;
import java.util.List;

public class Turnier {
    private String ort;
    private Date datum;
    private int insgPreisgeld;
    private List<Mannschaft> teilnehmer; // z. B. zwei Mannschaften
    private Mannschaft siegerMannschaft;  // Wird zur Laufzeit bestimmt

    public Turnier(String ort, Date datum, int insgPreisgeld, List<Mannschaft> teilnehmer) {
        this.ort = ort;
        this.datum = datum;
        this.insgPreisgeld = insgPreisgeld;
        this.teilnehmer = teilnehmer;
    }
    
    public void siegerFestlegen() {
        // Implementiere hier die Logik zur Bestimmung des Siegers
    }
    
    public void preisGeldAuszahlen(Mannschaft team, int betrag) {
        // Implementiere hier die Logik zur Verteilung des Preisgeldes
    }
    
    public void turnierAusspielen() {
        // Implementiere den Turnierablauf
    }
    
    // Getter und Setter
    public String getOrt() {
        return ort;
    }
    
    public Date getDatum() {
        return datum;
    }
    
    public int getInsgPreisgeld() {
        return insgPreisgeld;
    }
    
    public List<Mannschaft> getTeilnehmer() {
        return teilnehmer;
    }
    
    public Mannschaft getSiegerMannschaft() {
        return siegerMannschaft;
    }
    
    public void setSiegerMannschaft(Mannschaft siegerMannschaft) {
        this.siegerMannschaft = siegerMannschaft;
    }
}
