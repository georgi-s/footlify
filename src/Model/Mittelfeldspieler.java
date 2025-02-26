// pfad: STDM_GruppeC_SCPM06/src/sportverein/Mittelfeld.java
package Model;


import java.util.Date;
import java.util.UUID;

import static java.lang.Math.round;


public class Mittelfeldspieler extends Spieler {
    private int anzahlVorlagen;
    private int tore;
    private double passquote;

    public Mittelfeldspieler(int anzahlVorlagen, int tore, double passquote, UUID playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.anzahlVorlagen = anzahlVorlagen;
        this.tore = tore;
        this.passquote = passquote;
    }
    
    @Override
    
    public String spielerstatistikAusgeben()
    {
        String statistik = "";
        statistik += "Name: " + super.getVorname() + " " + super.getNachname() + "\n";
        statistik += "Position: Mittelfeldspieler\n";
        statistik += "Spiele: " + super.getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + super.getGelbeKarten() +"\n";
        statistik += "roteKarten: " + super.getRoteKarten() +"\n";
        statistik += "Tore: " + tore + "\n";
        statistik += "Vorlagen: " + anzahlVorlagen + "\n";
        statistik += "Passquote: " + passquote + "\n";
        return statistik;
    }
    
    @Override
    
    //spielerqoute ausgeben
    public double spielerBewertung()
    {
        double normGPS = tore/2.0;
        double normAS = anzahlVorlagen/2.0;
        double gewGPS = 3.0;
        double gewAS = 4.0;
        double gewPQ = 3.0;
        double bewertung = normGPS * gewGPS + normAS * gewAS + passquote * gewPQ;
        return round((bewertung/(gewGPS+gewAS+gewPQ))*100);
    }

    public double getPassquote() {
        return passquote;
    }

    public void setPassquote(double passquote) {
        this.passquote = passquote;
    }

    public int getTore() {
        return tore;
    }

    public void setTore(int tore) {
        this.tore = tore;
    }

    public int getAnzahlVorlagen() {
        return anzahlVorlagen;
    }

    public void setAnzahlVorlagen(int anzahlVorlagen) {
        this.anzahlVorlagen = anzahlVorlagen;
    }
}
