// pfad: STDM_GruppeC_SCPM06/src/sportverein/Turnier.java
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
public class Turnier {
    private String ort;
    private Date datum;
    private int insgPreisgeld;
    private Mannschaft[] teilnehmer;    //nur zwei Mannschaften
    private String siegerMannschaft;    //nicht im Constructor, da sieger nicht festgelegt werden soll

    public Turnier(String ort, Date datum, int insgPreisgeld, Mannschaft[] teilnehmer) {
        this.ort = ort;
        this.datum = datum;
        this.insgPreisgeld = insgPreisgeld;
        this.teilnehmer = teilnehmer;
    }
    
    private void siegerFestlegen()
    {
        
    }
    
    private void preisGeldAuszahlen(Mannschaft team, int betrag)
    {
        
    }
    
    private void tunierAusspielen()
    {
        
    }

    public Mannschaft[] getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(Mannschaft[] teilnehmer) {
        this.teilnehmer = teilnehmer;
    }
}
