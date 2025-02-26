// pfad: STDM_GruppeC_SCPM06/src/sportverein/Turnier.java
package Model;


import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mwiederspahn
 */
public class Turnier {

    private UUID turnierId;
    private String ort;
    private String datum;
    private int insgPreisgeld;
    private ArrayList<UUID> teilnehmer;    //nur zwei Mannschaften
    private String siegerMannschaft;    //nicht im Constructor, da sieger nicht festgelegt werden soll

    public Turnier(String ort, String datum, int insgPreisgeld, ArrayList<UUID> teilnehmer) {
        this.turnierId = UUID.randomUUID();
        this.ort = ort;
        this.datum = datum;
        this.insgPreisgeld = insgPreisgeld;
        this.teilnehmer = teilnehmer;
    }

    
    private void siegerFestlegen()
    {
        //if (teilnehmer[0].mannschaftsbewertungAusgeben() > teilnehmer[1].mannschaftsbewertungAusgeben())
        //{
        //    System.out.println("Die Mannschaft '" + teilnehmer[0].getName() + "' hat gewonnen!");
        //    preisGeldAuszahlen(teilnehmer[0], insgPreisgeld);
        //}
        //else if (teilnehmer[0].mannschaftsbewertungAusgeben() < teilnehmer[1].mannschaftsbewertungAusgeben())
        //{
        //    System.out.println("Die Mannschaft '" + teilnehmer[1].getName() + "' hat gewonnen!");
        //    preisGeldAuszahlen(teilnehmer[1], insgPreisgeld);
        //}
        //else if (teilnehmer[0].mannschaftsbewertungAusgeben().equals(teilnehmer[1].mannschaftsbewertungAusgeben()))
        //{
        //    System.out.println("Unentschieden!");
        //}
    }
    
    private void preisGeldAuszahlen(Mannschaft team, int betrag)
    {
        System.out.println("Das Team" + team.getName() + "erhaelt ein Preisgeld in Hoehe von " + betrag + " Euro!");
    }
    
    private void tunierAusspielen()
    {
        System.out.println("Das Turnier beginnt!");
    }

    public ArrayList<UUID> getTeilnehmer() {
        return teilnehmer;
    }

    public void addTeilnehmer(UUID clubId) {
        this.teilnehmer.add(clubId);
    }

    public void removeTeilnehmer(int clubId){
        this.teilnehmer.remove(Integer.valueOf(clubId));
    }

    public UUID getTurnierId() {
        return turnierId;
    }

    public String getOrt() {
        return ort;
    }

    public String getDate() {
        return datum.toString();
    }

    public int getInsgPreisgeld() {
        return insgPreisgeld;
    }

    public void editTurnier(String ort, String datum, int insgPreisgeld, ArrayList<UUID> teilnehmer) {
        this.ort = ort;
        this.datum = datum;
        this.insgPreisgeld = insgPreisgeld;
        this.teilnehmer = teilnehmer;
    }

    @Override
    public String toString() {
        return ort;
    }
}
