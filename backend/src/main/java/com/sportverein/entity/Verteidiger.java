package com.sportverein.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import static java.lang.Math.round;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("VERTEIDIGER")
public class Verteidiger extends Spieler {
    private int geblockteAngriffe;
    private int gewonneneZweikaempfe;
    private double passqoute;

    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Verteidiger\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + getGelbeKarten() + "\n";
        statistik += "roteKarten: " + getRoteKarten() + "\n";
        statistik += "Geblockte Angriffe: " + geblockteAngriffe + "\n";
        statistik += "gewonnene Zweikämpfe: " + gewonneneZweikaempfe + "\n";
        statistik += "Passquote: " + passqoute + "\n";
        return statistik;
    }

    @Override
    public double spielerBewertung() {
        double gewGeb = 2.0;
        double gewZK = 5.0;
        double gewPQ = 3.0;

        double normGeb = (double) geblockteAngriffe / getGespielteSpiele();
        double normZK = (double) gewonneneZweikaempfe / getGespielteSpiele();
        double bewertung = normGeb * gewGeb + normZK * gewZK + passqoute * gewPQ;
        double gewichtung = gewGeb + gewZK + gewPQ;
        bewertung = (bewertung / gewichtung) * 100;
        return round(bewertung / 3);
    }
}
