package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Mannschaft {
    public UUID ClubId = UUID.randomUUID();
    private String name;
    String trainer;
    private ArrayList<UUID> feldspieler;
    private ArrayList<UUID> auswechselspieler;
    private Formation formation;
    private Liga liga;

    public Mannschaft(String name, String trainer, ArrayList<UUID> feldspieler, ArrayList<UUID> auswechselspieler, Formation formation, Liga liga) {
        this.name = name;
        this.trainer = trainer;
        this.feldspieler = feldspieler;
        this.auswechselspieler = auswechselspieler;
        this.formation = formation;
        this.liga = liga;
    }

    public void editClub(String name, String trainer, Formation formation, Liga liga) {
        this.name = name;
        this.trainer = trainer;
        this.formation = formation;
        this.liga = liga;
    }
    

    public UUID getClubId() {
        return ClubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UUID> getAuswechselspieler(){
        return auswechselspieler;
    }

    public void setFeldspieler(ArrayList<UUID> feldspieler) {
        this.feldspieler = feldspieler;
    }

    public void setAuswechselspieler(ArrayList<UUID> auswechselspieler) {
        this.auswechselspieler = auswechselspieler;
    }

    public ArrayList<UUID> getFeldspieler(){
        return feldspieler;
    }

    public void addAuswechselspieler(UUID playerId){
        this.auswechselspieler.add(playerId);
    }
    public void addFeldspieler(UUID playerId){
        this.feldspieler.add(playerId);
    }

    public void transferPlayer(UUID playerId){
        if(feldspieler.contains(playerId)){
            feldspieler.remove(playerId);
            auswechselspieler.add(playerId);
        }
        else{
            auswechselspieler.remove(playerId);
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
