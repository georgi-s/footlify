// pfad: STDM_GruppeC_SCPM06/src/sportverein/Torwart.java
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
public class Torwart extends Spieler {

    private int spieleOhneGegentor;
    private int gegentore;
    private double haltequote;

    public Torwart(int spieleOhneGegentor, int gegentore, double haltequote, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.spieleOhneGegentor = spieleOhneGegentor;
        this.gegentore = gegentore;
        this.haltequote = haltequote;
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
        return 0.0;
    }
}
