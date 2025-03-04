package com.sportverein.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import static java.lang.Math.round;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("STUERMER")
public class Stuermer extends Spieler {
    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;

    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Stürmer\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "Gelbe Karten: " + getGelbeKarten() + "\n";
        statistik += "Rote Karten: " + getRoteKarten() + "\n";
        statistik += "Tore: " + geschosseneTore + "\n";
        statistik += "Schussgenauigkeit: " + schussgenauigkeit + "\n";
        statistik += "Chancenverwertung: " + chancenverwertung + "\n";
        return statistik;
    }

    @Override
    public double spielerBewertung() {
        int spiele = getGespielteSpiele();
        double normToreProSpiel = 0.0;
        if (spiele > 0) {
            normToreProSpiel = ((double) geschosseneTore / spiele) / 5.0;
        }

        double gewichtungTore = 5.0;
        double gewichtungSchussgenauigkeit = 3.0;
        double gewichtungChancenverwertung = 4.0;

        double bewertung = normToreProSpiel * gewichtungTore
                + schussgenauigkeit * gewichtungSchussgenauigkeit
                + chancenverwertung * gewichtungChancenverwertung;

        double gesamtGewichtung = gewichtungTore + gewichtungSchussgenauigkeit + gewichtungChancenverwertung;
        bewertung = (bewertung / gesamtGewichtung) * 100.0;

        return round(bewertung);
    }
}
