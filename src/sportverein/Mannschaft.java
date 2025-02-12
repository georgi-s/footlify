// pfad: STDM_GruppeC_SCPM06/src/sportverein/Manschaft.java
package sportverein;


import java.util.ArrayList;

public class Mannschaft {
    public int ClubId;
    private String name;
    String trainer;
    private Spieler[] feldspieler;
    private ArrayList<Spieler> auswechselspieler;
    private Formation formation;
    private Liga liga;

    public Mannschaft(String name, String trainer, Spieler[] feldspieler, ArrayList<Spieler> auswechselspieler, Formation formation, Liga liga) {
        this.name = name;
        this.trainer = trainer;
        this.feldspieler = feldspieler;
        this.auswechselspieler = auswechselspieler;
        this.formation = formation;
        this.liga = liga;
    }
    
    private boolean aufstellungPruefen()
    {
        if (feldspieler.length != 11) {
            return false;
        }

        int verteidigerCount = 0;
        int mittelfeldspielerCount = 0;
        int stuermerCount = 0;

        for (Spieler spieler : feldspieler) {
            if (spieler instanceof Verteidiger) {
                verteidigerCount++;
            } else if (spieler instanceof Mittelfeldspieler) {
                mittelfeldspielerCount++;
            } else if (spieler instanceof Stuermer) {
                stuermerCount++;
            }
        }

        return verteidigerCount == formation.getVerteidigerAnzahl() &&
                mittelfeldspielerCount == formation.getMittelfeldspielerAnzahl() &&
                stuermerCount == formation.getStuermerAnzahl();
    }
    
    public Double mannschaftsbewertungAusgeben()
    {
        double bewertung = 0;
        for (Spieler feldspieler : feldspieler) {
            bewertung += feldspieler.spielerBewertung();
        }

        return Math.round((bewertung / 11) * 100.0) / 100.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
