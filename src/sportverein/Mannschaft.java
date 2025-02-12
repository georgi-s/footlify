package sportverein;


import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mwiederspahn
 */
public class Mannschaft {
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
}
