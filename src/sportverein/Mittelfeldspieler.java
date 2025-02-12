package sportverein;


import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mwiederspahn
 */
public class Mittelfeldspieler extends Spieler {
    private int anzahlVorlagen;
    private int tore;
    private double passquote;

    public Mittelfeldspieler(int anzahlVorlagen, int tore, double passquote, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.anzahlVorlagen = anzahlVorlagen;
        this.tore = tore;
        this.passquote = passquote;
    }
    
    @Override
    
    public String spielerstatistikAusgeben()
    {
        return "Spielerdaten";
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
        return (bewertung/(gewGPS+gewAS+gewPQ))*100;
    }
}
