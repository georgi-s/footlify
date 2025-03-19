package com.sportverein.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("TorwartEntity")
public class TorwartEntity extends SpielerEntity {

    private int spieleOhneGegentor;
    private int gegentore;
    private double haltequote;
    
    public TorwartEntity() {
    }
    
    public TorwartEntity(String nachname, String vorname, Date geburtsdatum, int gespielteSpiele,
                          boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten,
                          int spieleOhneGegentor, int gegentore, double haltequote) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.spieleOhneGegentor = spieleOhneGegentor;
        this.gegentore = gegentore;
        this.haltequote = haltequote;
    }

    // Getter und Setter

    public int getSpieleOhneGegentor() {
        return spieleOhneGegentor;
    }

    public void setSpieleOhneGegentor(int spieleOhneGegentor) {
        this.spieleOhneGegentor = spieleOhneGegentor;
    }

    public int getGegentore() {
        return gegentore;
    }

    public void setGegentore(int gegentore) {
        this.gegentore = gegentore;
    }

    public double getHaltequote() {
        return haltequote;
    }

    public void setHaltequote(double haltequote) {
        this.haltequote = haltequote;
    }
}
