package com.sportverein.dto;

import java.util.Date;

public class SpielerDTO {
    private Long id;
    private String nachname;
    private String vorname;
    private Date geburtsdatum;
    private int gespielteSpiele;
    private boolean gesperrt;
    private Date vereinsbeitritt;
    private int roteKarten;
    private int gelbeKarten;
    private String spielerType; // z.B. "Mittelfeldspieler", "Stürmer", "Torwart", "Verteidiger"
    
    // Spezifische Felder, z.B. für Mittelfeldspieler
    private Integer anzahlVorlagen;
    private Integer tore;
    private Double passquote;

    public SpielerDTO() {
    }

    // Getter und Setter für die gemeinsamen Felder
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getGespielteSpiele() {
        return gespielteSpiele;
    }

    public void setGespielteSpiele(int gespielteSpiele) {
        this.gespielteSpiele = gespielteSpiele;
    }

    public boolean isGesperrt() {
        return gesperrt;
    }

    public void setGesperrt(boolean gesperrt) {
        this.gesperrt = gesperrt;
    }

    public Date getVereinsbeitritt() {
        return vereinsbeitritt;
    }

    public void setVereinsbeitritt(Date vereinsbeitritt) {
        this.vereinsbeitritt = vereinsbeitritt;
    }

    public int getRoteKarten() {
        return roteKarten;
    }

    public void setRoteKarten(int roteKarten) {
        this.roteKarten = roteKarten;
    }

    public int getGelbeKarten() {
        return gelbeKarten;
    }

    public void setGelbeKarten(int gelbeKarten) {
        this.gelbeKarten = gelbeKarten;
    }

    public String getSpielerType() {
        return spielerType;
    }

    public void setSpielerType(String spielerType) {
        this.spielerType = spielerType;
    }

    // Getter und Setter für die spezifischen Felder

    public Integer getAnzahlVorlagen() {
        return anzahlVorlagen;
    }

    public void setAnzahlVorlagen(Integer anzahlVorlagen) {
        this.anzahlVorlagen = anzahlVorlagen;
    }

    public Integer getTore() {
        return tore;
    }

    public void setTore(Integer tore) {
        this.tore = tore;
    }

    public Double getPassquote() {
        return passquote;
    }

    public void setPassquote(Double passquote) {
        this.passquote = passquote;
    }
}
