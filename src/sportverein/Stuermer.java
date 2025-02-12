package sportverein;


import java.util.Date;


public class Stuermer extends Spieler {
    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;

    public Stuermer(int geschosseneTore, double schussgenauigkeit, double chancenverwertung,int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(playerId,nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geschosseneTore = geschosseneTore;
        this.schussgenauigkeit = schussgenauigkeit;
        this.chancenverwertung = chancenverwertung;
    }
    
    @Override
    
    public String spielerstatistikAusgeben()
    {
        String statistik = "";
        statistik += "Name: " + super.getVorname() + " " + super.getNachname() + "\n";
        statistik += "Position: Stürmer\n";
        statistik += "Spiele: " + super.getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + super.getGelbeKarten() +"\n";
        statistik += "roteKarten: " + super.getRoteKarten() +"\n";
        statistik += "Tore: " + geschosseneTore + "\n";
        statistik += "Schussgenauigkeit: " + schussgenauigkeit + "\n";
        statistik += "Chancenverwertung: " + chancenverwertung + "\n";
        return statistik;
    }
    
    @Override
    
    //spielerqoute ausgeben
    public double spielerBewertung()
    {
        return 0.0;
    }
}
