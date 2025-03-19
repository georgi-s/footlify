package com.sportverein.model;

import java.util.Date;

public class Verteidiger extends Spieler {
    private int geblockteAngriffe;
    private int gewonneneZweikaempfe;
    private double passquote;
    
    public Verteidiger(int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten, int geblockteAngriffe, int gewonneneZweikaempfe, double passquote) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geblockteAngriffe = geblockteAngriffe;
        this.gewonneneZweikaempfe = gewonneneZweikaempfe;
        this.passquote = passquote;
    }
    
    @Override
    public String spielerstatistikAusgeben() {
        String statistik = "";
        statistik += "Name: " + getVorname() + " " + getNachname() + "\n";
        statistik += "Position: Verteidiger\n";
        statistik += "Spiele: " + getGespielteSpiele() + "\n";
        statistik += "Gelbe Karten: " + getGelbeKarten() + "\n";
        statistik += "Rote Karten: " + getRoteKarten() + "\n";
        statistik += "Geblockte Angriffe: " + geblockteAngriffe + "\n";
        statistik += "Gewonnene Zweikämpfe: " + gewonneneZweikaempfe + "\n";
        statistik += "Passquote: " + passquote + "\n";
        return statistik;
    }
    
    @Override
    public double spielerBewertung() {
        // Hier kannst du die Bewertung implementieren, falls benötigt.
        return 0.0;
    }
    
    // Neue Getter-Methoden für die Mapper
    public int getGeblockteAngriffe() {
        return geblockteAngriffe;
    }
    
    public int getGewonneneZweikaempfe() {
        return gewonneneZweikaempfe;
    }
    
    public double getPassquote() {
        return passquote;
    }
}
