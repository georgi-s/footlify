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
        return false;
    }
    
    private String mannschaftsbewertungAusgeben()
    {
            return "Bewertung";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
