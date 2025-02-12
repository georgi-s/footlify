package sportverein;


import java.util.Date;

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
        double normGgtPS = (double) gegentore /super.getGespielteSpiele();
        double normSOG  = (double) spieleOhneGegentor /super.getGespielteSpiele() ;
        double gewichtungSOG = 4.0;
        double gewichtungHaltequote = 3.0;
        double gewichtungGgtPS = 3.0;
        double bewertung = normGgtPS * gewichtungGgtPS + normSOG * gewichtungSOG+ haltequote * gewichtungHaltequote;

        return (bewertung/(gewichtungSOG+gewichtungHaltequote+gewichtungGgtPS))*100;
    }
}
