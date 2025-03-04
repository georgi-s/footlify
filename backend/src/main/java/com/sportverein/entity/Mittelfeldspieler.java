package com.sportverein.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import static java.lang.Math.round;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MITTELFELDSPIELER")
public class Mittelfeldspieler extends Spieler {
    private int anzahlVorlagen;
    private int tore;
    private double passquote;

    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Mittelfeldspieler\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + getGelbeKarten() + "\n";
        statistik += "roteKarten: " + getRoteKarten() + "\n";
        statistik += "Tore: " + tore + "\n";
        statistik += "Vorlagen: " + anzahlVorlagen + "\n";
        statistik += "Passquote: " + passquote + "\n";
        return statistik;
    }

    @Override
    public double spielerBewertung() {
        double normGPS = (double) tore / getGespielteSpiele();
        double normAS = (double) anzahlVorlagen / getGespielteSpiele();
        double gewGPS = 3.0;
        double gewAS = 4.0;
        double gewPQ = 3.0;
        double bewertung = normGPS * gewGPS + normAS * gewAS + passquote * gewPQ;
        double gewichtung = gewGPS + gewAS + gewPQ;
        bewertung = (bewertung / gewichtung) * 100;

        return round(bewertung);
    }
}
