// pfad: STDM_GruppeC_SCPM06/src/sportverein/Torwart.java
package sportverein;


import java.util.Date;

import static java.lang.Math.round;


public class Torwart extends Spieler {

    private int spieleOhneGegentor;
    private int gegentore;
    private double haltequote;

    public Torwart(int spieleOhneGegentor, int gegentore, double haltequote,int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
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
}
