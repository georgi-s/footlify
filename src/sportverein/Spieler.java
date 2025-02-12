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

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getGespielteSpiele() {
        return gespielteSpiele;
    }

    public void setGespielteSpiele(int gespielteSpiele) {
        this.gespielteSpiele = gespielteSpiele;
    }

    public boolean isGesperrt() {
        return gesperrt;
    }

    public void setGesperrt(boolean gesperrt) {
        this.gesperrt = gesperrt;
    }

    public int getRoteKarten() {
        return roteKarten;
    }

    public void setRoteKarten(int roteKarten) {
        this.roteKarten = roteKarten;
    }

    public Date getVereinsbeitritt() {
        return vereinsbeitritt;
    }

    public void setVereinsbeitritt(Date vereinsbeitritt) {
        this.vereinsbeitritt = vereinsbeitritt;
    }

    public int getGelbeKarten() {
        return gelbeKarten;
    }

    public void setGelbeKarten(int gelbeKarten) {
        this.gelbeKarten = gelbeKarten;
    }
}
