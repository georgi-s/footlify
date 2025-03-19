package com.sportverein.model;

import java.util.Date;

public class Torwart extends Spieler {
    private int spieleOhneGegentor;
    private int gegentore;
    private double haltequote;
    
    public Torwart(int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten, int spieleOhneGegentor, int gegentore, double haltequote) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.spieleOhneGegentor = spieleOhneGegentor;
        this.gegentore = gegentore;
        this.haltequote = haltequote;
    }
    
    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Torwart\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "Gelbe Karten: " + getGelbeKarten() + "\n";
        statistik += "Rote Karten: " + getRoteKarten() + "\n";
        statistik += "Spiele ohne Gegentor: " + spieleOhneGegentor + "\n";
        statistik += "Gegentore: " + gegentore + "\n";
        statistik += "Haltequote: " + haltequote + "\n";
        return statistik;
    }
    
    @Override
    public double spielerBewertung() {
        double normGgtPS = getGespielteSpiele() > 0 ? (double) gegentore / getGespielteSpiele() : 0.0;
        double normSOG = getGespielteSpiele() > 0 ? (double) spieleOhneGegentor / getGespielteSpiele() : 0.0;
        double gewichtungSOG = 4.0;
        double gewichtungHaltequote = 3.0;
        double gewichtungGgtPS = 3.0;
        double bewertung = normGgtPS * gewichtungGgtPS + normSOG * gewichtungSOG + haltequote * gewichtungHaltequote;
        return (bewertung / (gewichtungSOG + gewichtungHaltequote + gewichtungGgtPS)) * 100;
    }
    
    // Neue Getter-Methoden für die fehlenden Felder:
    public int getSpieleOhneGegentor() {
        return spieleOhneGegentor;
    }
    
    public int getGegentore() {
        return gegentore;
    }
    
    public double getHaltequote() {
        return haltequote;
    }
}
