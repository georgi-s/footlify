package sportverein;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mwiederspahn
 */
public class Mannschaft {
    private String name;
    String trainer;
    private Spieler[] feldspieler;
    private ArrayList<Spieler> auswechselspieler;
    private Formation formation;
    private Liga liga;

    public Mannschaft(String name, String trainer, Spieler[] feldspieler, ArrayList<Spieler> auswechselspieler, Formation formation, Liga liga) {
        this.name = name;
        this.trainer = trainer;
        this.feldspieler = feldspieler;
        this.auswechselspieler = auswechselspieler;
        this.formation = formation;
        this.liga = liga;
    }
    
    private boolean aufstellungPruefen()
    {
        if (feldspieler.length != 11) {
            return false;
        }

        int verteidigerCount = 0;
        int mittelfeldspielerCount = 0;
        int stuermerCount = 0;

        for (Spieler spieler : feldspieler) {
            if (spieler instanceof Verteidiger) {
                verteidigerCount++;
            } else if (spieler instanceof Mittelfeldspieler) {
                mittelfeldspielerCount++;
            } else if (spieler instanceof Stuermer) {
                stuermerCount++;
            }
        }

        return verteidigerCount == formation.getVerteidigerAnzahl() &&
                mittelfeldspielerCount == formation.getMittelfeldspielerAnzahl() &&
                stuermerCount == formation.getStuermerAnzahl();
    }
    
    private Double mannschaftsbewertungAusgeben()
    {
        double bewertung = 0;
        for (Spieler feldspieler : feldspieler) {
            bewertung += feldspieler.spielerBewertung();
        }

        return Math.round((bewertung / 11) * 100.0) / 100.0;
    }
}
