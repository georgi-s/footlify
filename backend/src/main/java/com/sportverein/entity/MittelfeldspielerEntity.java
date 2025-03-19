package com.sportverein.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("MittelfeldspielerEntity")
public class MittelfeldspielerEntity extends SpielerEntity {
    
    private int anzahlVorlagen;
    private int tore;
    private double passquote;
    
    @Column(name = "position", nullable = false)
    private String position = "Mittelfeld";
    
    public MittelfeldspielerEntity() {
    }
    
    public MittelfeldspielerEntity(String nachname, String vorname, Date geburtsdatum, int gespielteSpiele,
                                   boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten,
                                   int anzahlVorlagen, int tore, double passquote) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.anzahlVorlagen = anzahlVorlagen;
        this.tore = tore;
        this.passquote = passquote;
        // Setze explizit den Wert für position
        this.position = "Mittelfeld";
    }

    // Getter und Setter für die spezifischen Felder

    public int getAnzahlVorlagen() {
        return anzahlVorlagen;
    }

    public void setAnzahlVorlagen(int anzahlVorlagen) {
        this.anzahlVorlagen = anzahlVorlagen;
    }

    public int getTore() {
        return tore;
    }

    public void setTore(int tore) {
        this.tore = tore;
    }

    public double getPassquote() {
        return passquote;
    }

    public void setPassquote(double passquote) {
        this.passquote = passquote;
    }

    // Getter und Setter für das neue Feld "position"
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
