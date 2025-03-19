package com.sportverein.dto;

public class MannschaftDTO {
    private Long id;
    private String name;
    private String trainer;
    private String formation;  // als String, z. B. "f442"
    private String liga;       // als String, z. B. "Bundesliga1"

    public MannschaftDTO() {
    }

    // Alle Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }
}
