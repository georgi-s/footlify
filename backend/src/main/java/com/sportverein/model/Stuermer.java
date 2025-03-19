package com.sportverein.model;

import java.util.Date;

public class Stuermer extends Spieler {
    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;
    
    public Stuermer(int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten, int geschosseneTore, double schussgenauigkeit, double chancenverwertung) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geschosseneTore = geschosseneTore;
        this.schussgenauigkeit = schussgenauigkeit;
        this.chancenverwertung = chancenverwertung;
    }
    
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
        double normToreProSpiel = spiele > 0 ? ((double) geschosseneTore / spiele) / 5.0 : 0.0;
        double gewichtungTore = 5.0;
        double gewichtungSchussgenauigkeit = 3.0;
        double gewichtungChancenverwertung = 4.0;
        double bewertung = normToreProSpiel * gewichtungTore + schussgenauigkeit * gewichtungSchussgenauigkeit + chancenverwertung * gewichtungChancenverwertung;
        double gesamtGewichtung = gewichtungTore + gewichtungSchussgenauigkeit + gewichtungChancenverwertung;
        return (bewertung / gesamtGewichtung) * 100;
    }
    
    // Falls noch nicht vorhanden, füge die Getter hinzu:
    public int getGeschosseneTore() {
        return geschosseneTore;
    }
    
    public double getSchussgenauigkeit() {
        return schussgenauigkeit;
    }
    
    public double getChancenverwertung() {
        return chancenverwertung;
    }
}
