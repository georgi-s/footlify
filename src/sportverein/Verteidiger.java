// pfad: STDM_GruppeC_SCPM06/src/sportverein/Verteidiger.java
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
public class Verteidiger extends Spieler {
    private int geblockteAngriffe;
    private int gewonneneZweikaempfe;
    private double passqoute;

    public Verteidiger(int geblockteAngriffe, int gewonneneZweikaempfe, double passqoute, String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geblockteAngriffe = geblockteAngriffe;
        this.gewonneneZweikaempfe = gewonneneZweikaempfe;
        this.passqoute = passqoute;
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
