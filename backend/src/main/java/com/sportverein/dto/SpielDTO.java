package com.sportverein.dto;

import java.time.LocalDate;

/**
 * DTO für die Übertragung von Spiel-Daten zwischen Frontend und Backend
 */
public class SpielDTO {
    private Long id;
    private Long heimMannschaftId;
    private String heimMannschaftName;
    private Long gastMannschaftId;
    private String gastMannschaftName;
    private LocalDate datum;
    private String uhrzeit;
    private String ort;
    private String liga;
    private String status;
    private Integer heimTore;
    private Integer gastTore;
    
    // Konstruktoren
    public SpielDTO() {
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHeimMannschaftId() {
        return heimMannschaftId;
    }

    public void setHeimMannschaftId(Long heimMannschaftId) {
        this.heimMannschaftId = heimMannschaftId;
    }

    public String getHeimMannschaftName() {
        return heimMannschaftName;
    }

    public void setHeimMannschaftName(String heimMannschaftName) {
        this.heimMannschaftName = heimMannschaftName;
    }

    public Long getGastMannschaftId() {
        return gastMannschaftId;
    }

    public void setGastMannschaftId(Long gastMannschaftId) {
        this.gastMannschaftId = gastMannschaftId;
    }

    public String getGastMannschaftName() {
        return gastMannschaftName;
    }

    public void setGastMannschaftName(String gastMannschaftName) {
        this.gastMannschaftName = gastMannschaftName;
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
