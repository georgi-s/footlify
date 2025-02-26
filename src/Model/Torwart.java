// pfad: STDM_GruppeC_SCPM06/src/sportverein/Torwart.java
package Model;


import java.util.Date;
import java.util.UUID;

import static java.lang.Math.round;


public class Torwart extends Spieler {

    private int spieleOhneGegentor;
    private int gegentore;
    private double haltequote;

    public Torwart(int spieleOhneGegentor, int gegentore, double haltequote, UUID playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.spieleOhneGegentor = spieleOhneGegentor;
        this.gegentore = gegentore;
        this.haltequote = haltequote;
    }
    
    @Override
    
    public String spielerstatistikAusgeben()
    {
        String statistik = "";
        statistik += "Name: " + super.getVorname() + " " + super.getNachname() + "\n";
        statistik += "Position: Torwart\n";
        statistik += "Spiele: " + super.getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + super.getGelbeKarten() +"\n";
        statistik += "roteKarten: " + super.getRoteKarten() +"\n";
        statistik += "Spiele ohne Ggt: " + spieleOhneGegentor + "\n";
        statistik += "Gegentore: " + gegentore + "\n";
        statistik += "Haltequote: " + haltequote + "\n";
        return statistik;
    }
    
    @Override
    
    //spielerqoute ausgeben
    public double spielerBewertung()
    {
        double normGgtPS = (double) gegentore /super.getGespielteSpiele();
        double normSOG  = (double) spieleOhneGegentor /super.getGespielteSpiele() ;
        double gewichtungSOG = 4.0;
        double gewichtungHaltequote = 3.0;
        double gewichtungGgtPS = 3.0;
        double bewertung = normGgtPS * gewichtungGgtPS + normSOG * gewichtungSOG+ haltequote * gewichtungHaltequote;

        return round((bewertung/(gewichtungSOG+gewichtungHaltequote+gewichtungGgtPS))*100);
    }

    public int getSpieleOhneGegentor() {
        return spieleOhneGegentor;
    }

    public void setSpieleOhneGegentor(int spieleOhneGegentor) {
        this.spieleOhneGegentor = spieleOhneGegentor;
    }

    public int getGegentore() {
        return gegentore;
    }

    public void setGegentore(int gegentore) {
        this.gegentore = gegentore;
    }

    public double getHaltequote() {
        return haltequote;
    }

    public void setHaltequote(double haltequote) {
        this.haltequote = haltequote;
    }
}
