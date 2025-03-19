package com.sportverein.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("StuermerEntity")
public class StuermerEntity extends SpielerEntity {

    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;
    
    public StuermerEntity() {
    }
    
    public StuermerEntity(String nachname, String vorname, Date geburtsdatum, int gespielteSpiele,
                           boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten,
                           int geschosseneTore, double schussgenauigkeit, double chancenverwertung) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geschosseneTore = geschosseneTore;
        this.schussgenauigkeit = schussgenauigkeit;
        this.chancenverwertung = chancenverwertung;
    }

    // Getter und Setter

    public int getGeschosseneTore() {
        return geschosseneTore;
    }

    public void setGeschosseneTore(int geschosseneTore) {
        this.geschosseneTore = geschosseneTore;
    }

    public double getSchussgenauigkeit() {
        return schussgenauigkeit;
    }

    public void setSchussgenauigkeit(double schussgenauigkeit) {
        this.schussgenauigkeit = schussgenauigkeit;
    }

    public double getChancenverwertung() {
        return chancenverwertung;
    }

    public void setChancenverwertung(double chancenverwertung) {
        this.chancenverwertung = chancenverwertung;
    }
}
