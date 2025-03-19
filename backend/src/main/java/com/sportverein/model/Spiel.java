package com.sportverein.model;

import java.time.LocalDate;

/**
 * Domänenmodell für ein Spiel zwischen zwei Mannschaften
 */
public class Spiel {
    private Long id;
    private Mannschaft heimMannschaft;
    private Mannschaft gastMannschaft;
    private LocalDate datum;
    private String uhrzeit;
    private String ort;
    private String liga;
    private String status;
    private Integer heimTore;
    private Integer gastTore;
    
    // Konstruktoren
    public Spiel() {
    }
    
    public Spiel(Mannschaft heimMannschaft, Mannschaft gastMannschaft, LocalDate datum, 
                 String uhrzeit, String ort, String status) {
        this.heimMannschaft = heimMannschaft;
        this.gastMannschaft = gastMannschaft;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.ort = ort;
        this.status = status;
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mannschaft getHeimMannschaft() {
        return heimMannschaft;
    }

    public void setHeimMannschaft(Mannschaft heimMannschaft) {
        this.heimMannschaft = heimMannschaft;
    }

    public Mannschaft getGastMannschaft() {
        return gastMannschaft;
    }

    public void setGastMannschaft(Mannschaft gastMannschaft) {
        this.gastMannschaft = gastMannschaft;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getHeimTore() {
        return heimTore;
    }

    public void setHeimTore(Integer heimTore) {
        this.heimTore = heimTore;
    }

    public Integer getGastTore() {
        return gastTore;
    }

    public void setGastTore(Integer gastTore) {
        this.gastTore = gastTore;
    }
}
