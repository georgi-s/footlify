// pfad: STDM_GruppeC_SCPM06/src/sportverein/Verteidiger.java
package Model;


import java.util.Date;


public class Verteidiger extends Spieler {
    private int geblockteAngriffe;
    private int gewonneneZweikaempfe;
    private double passqoute;

    public Verteidiger(int geblockteAngriffe, int gewonneneZweikaempfe, double passqoute,int playerId, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(playerId, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geblockteAngriffe = geblockteAngriffe;
        this.gewonneneZweikaempfe = gewonneneZweikaempfe;
        this.passqoute = passqoute;
    }
    
    @Override
    
    public String spielerstatistikAusgeben()
    {
        String statistik = "";
        statistik += "Name: " + super.getVorname() + " " + super.getNachname() + "\n";
        statistik += "Position: Verteidiger\n";
        statistik += "Spiele: " + super.getGespielteSpiele() + "\n";
        statistik += "gelbeKarten: " + super.getGelbeKarten() +"\n";
        statistik += "roteKarten: " + super.getRoteKarten() +"\n";
        statistik += "Geblockte Angriffe: " + geblockteAngriffe + "\n";
        statistik += "gewonnene Zweikämpfe: " + gewonneneZweikaempfe + "\n";
        statistik += "Passquote: " + passqoute + "\n";
        return statistik;
    }
    
    @Override
    
    //spielerqoute ausgeben
    public double spielerBewertung()
    {
        return 0.0;
    }

    public int getGeblockteAngriffe() {
        return geblockteAngriffe;
    }

    public void setGeblockteAngriffe(int geblockteAngriffe) {
        this.geblockteAngriffe = geblockteAngriffe;
    }

    public int getGewonneneZweikaempfe() {
        return gewonneneZweikaempfe;
    }

    public void setGewonneneZweikaempfe(int gewonneneZweikaempfe) {
        this.gewonneneZweikaempfe = gewonneneZweikaempfe;
    }

    public double getPassqoute() {
        return passqoute;
    }

    public void setPassqoute(double passqoute) {
        this.passqoute = passqoute;
    }
}
