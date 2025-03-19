package com.sportverein.model;

import java.util.Date;

public class Mittelfeldspieler extends Spieler {
    private int anzahlVorlagen;
    private int tore;
    private double passquote;
    
    // Parameterloser Konstruktor für Jackson
    public Mittelfeldspieler() {
        // Du kannst hier Standardwerte setzen, falls gewünscht.
        // Alternativ einfach den Default-Konstruktor leer lassen.
        super(0, "", "", new Date(), 0, false, new Date(), 0, 0);
    }
    
    public Mittelfeldspieler(int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten, int anzahlVorlagen, int tore, double passquote) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.anzahlVorlagen = anzahlVorlagen;
        this.tore = tore;
        this.passquote = passquote;
    }
    
    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Mittelfeldspieler\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "Gelbe Karten: " + getGelbeKarten() + "\n";
        statistik += "Rote Karten: " + getRoteKarten() + "\n";
        statistik += "Tore: " + tore + "\n";
        statistik += "Vorlagen: " + anzahlVorlagen + "\n";
        statistik += "Passquote: " + passquote + "\n";
        return statistik;
    }
    
    @Override
    public double spielerBewertung() {
        double normGPS = tore / 2.0;
        double normAS = anzahlVorlagen / 2.0;
        double gewGPS = 3.0;
        double gewAS = 4.0;
        double gewPQ = 3.0;
        double bewertung = normGPS * gewGPS + normAS * gewAS + passquote * gewPQ;
        return (bewertung / (gewGPS + gewAS + gewPQ)) * 100;
    }
    
    // Getter-Methoden
    public int getAnzahlVorlagen() {
        return anzahlVorlagen;
    }
    
    public int getTore() {
        return tore;
    }
    
    public double getPassquote() {
        return passquote;
    }
}
