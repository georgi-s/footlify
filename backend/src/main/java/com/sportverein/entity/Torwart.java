package com.sportverein.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import static java.lang.Math.round;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("TORWART")
public class Torwart extends Spieler {
    private int spieleOhneGegentor;
    private int gegentore;
    private double haltequote;

    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Torwart\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + getGelbeKarten() + "\n";
        statistik += "roteKarten: " + getRoteKarten() + "\n";
        statistik += "Spiele ohne Ggt: " + spieleOhneGegentor + "\n";
        statistik += "Gegentore: " + gegentore + "\n";
        statistik += "Haltequote: " + haltequote + "\n";
        return statistik;
    }

    @Override
    public double spielerBewertung() {
        double normGgtPS = (double) gegentore / getGespielteSpiele();
        double normSOG = (double) spieleOhneGegentor / getGespielteSpiele();
        double gewichtungSOG = 4.0;
        double gewichtungHaltequote = 3.0;
        double gewichtungGgtPS = 3.0;
        double bewertung = normGgtPS * gewichtungGgtPS + normSOG * gewichtungSOG + haltequote * gewichtungHaltequote;

        return round((bewertung / (gewichtungSOG + gewichtungHaltequote + gewichtungGgtPS)) * 100);
    }
}
