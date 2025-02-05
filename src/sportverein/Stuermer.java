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
public class Stuermer extends Spieler {
    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;

    public Stuermer(int geschosseneTore, double schussgenauigkeit, double chancenverwertung, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geschosseneTore = geschosseneTore;
        this.schussgenauigkeit = schussgenauigkeit;
        this.chancenverwertung = chancenverwertung;
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
