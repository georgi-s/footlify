package com.sportverein.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;



import java.util.Date;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "spielerType"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Mittelfeldspieler.class, name = "Mittelfeldspieler"),
    @JsonSubTypes.Type(value = Stuermer.class, name = "Stürmer"),
    @JsonSubTypes.Type(value = Torwart.class, name = "Torwart"),
    @JsonSubTypes.Type(value = Verteidiger.class, name = "Verteidiger")
})

public abstract class Spieler {
    private int playerId;
    private String nachname;
    private String vorname;
    private Date geburtsdatum;
    private int gespielteSpiele;
    private boolean gesperrt;
    private Date vereinsbeitritt;
    private int roteKarten;
    private int gelbeKarten;

    public Spieler(int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        this.playerId = playerId;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.gespielteSpiele = gespielteSpiele;
        this.gesperrt = gesperrt;
        this.vereinsbeitritt = vereinsbeitritt;
        this.roteKarten = roteKarten;
        this.gelbeKarten = gelbeKarten;
    }
    
    /**
     * Gibt die Spielerstatistik als String aus.
     */
    public abstract String spielerstatistikAusgeben();

    /**
     * Berechnet und gibt die Spielerbewertung als double zurück.
     */
    public abstract double spielerBewertung();

    // Getter (und Setter, falls erforderlich)
    public int getPlayerId() {
        return playerId;
    }
    
    public String getNachname() {
        return nachname;
    }
    
    public String getVorname() {
        return vorname;
    }
    
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }
    
    public int getGespielteSpiele() {
        return gespielteSpiele;
    }
    
    public boolean isGesperrt() {
        return gesperrt;
    }
    
    public Date getVereinsbeitritt() {
        return vereinsbeitritt;
    }
    
    public int getRoteKarten() {
        return roteKarten;
    }
    
    public int getGelbeKarten() {
        return gelbeKarten;
    }
}
