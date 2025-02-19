// pfad: STDM_GruppeC_SCPM06/src/sportverein/Manschaft.java
package sportverein;


import java.util.ArrayList;

public class Mannschaft {
    public int ClubId;
    private String name;
    String trainer;
    private ArrayList<Integer> feldspieler;
    private ArrayList<Integer> auswechselspieler;
    private Formation formation;
    private Liga liga;

    public Mannschaft(int clubId,String name, String trainer, ArrayList<Integer> feldspieler, ArrayList<Integer> auswechselspieler, Formation formation, Liga liga) {
        this.ClubId = clubId;
        this.name = name;
        this.trainer = trainer;
        this.feldspieler = feldspieler;
        this.auswechselspieler = auswechselspieler;
        this.formation = formation;
        this.liga = liga;
    }

    
    public Double mannschaftsbewertungAusgeben()
    {
        double bewertung = 0;
        //for (Spieler feldspieler : feldspieler) {
        //    bewertung += feldspieler.spielerBewertung();
        //}

        return Math.round((bewertung / 11) * 100.0) / 100.0;
    }
    public int getClubId() {
        return ClubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getAuswechselspieler(){
        return auswechselspieler;
    }

    public ArrayList<Integer> getFeldspieler(){
        return feldspieler;
    }

    public void addAuswechselspieler(int playerId){
        this.auswechselspieler.add(playerId);
    }
    public void addFeldspieler(int playerId){
        this.feldspieler.add(playerId);
    }

    public void transferPlayer(int playerId){
        if(feldspieler.contains(playerId)){
            feldspieler.remove(Integer.valueOf(playerId));
            auswechselspieler.add(playerId);
        }
        else{
            auswechselspieler.remove(Integer.valueOf(playerId));
            feldspieler.add(playerId);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}
