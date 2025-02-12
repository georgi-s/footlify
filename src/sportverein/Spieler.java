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
public abstract class Spieler {
    private String nachname;
    private String vorname;
    private Date geburtsdatum;
    private int gespielteSpiele;
    private boolean gesperrt;
    private Date vereinsbeitritt;
    private int roteKarten;
    private int gelbeKarten;

    public Spieler(String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.gespielteSpiele = gespielteSpiele;
        this.gesperrt = gesperrt;
        this.vereinsbeitritt = vereinsbeitritt;
        this.roteKarten = roteKarten;
        this.gelbeKarten = gelbeKarten;
    }
    
    //spielerdaten ausgeben
    public String spielerstatistikAusgeben()
    {
        return "";
    }
    
    //spielerquote ausgeben
    public double spielerBewertung()
    {
        return 0.0;
    }

    public int getGespielteSpiele() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGespielteSpiele'");
    }
}
