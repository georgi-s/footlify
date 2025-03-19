package com.sportverein.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mannschaft")
public class MannschaftEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String trainer;
    
    @ManyToOne
    @JoinColumn(name = "formation_id")
    private FormationEntity formation;
    
    @ManyToOne
    @JoinColumn(name = "liga_id")
    private LigaEntity liga;
    
    // Eine Mannschaft hat mehrere Spieler
    @OneToMany(mappedBy = "mannschaft", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpielerEntity> spieler;

    public MannschaftEntity() {
    }

    public MannschaftEntity(String name, String trainer, FormationEntity formation, LigaEntity liga) {
        this.name = name;
        this.trainer = trainer;
        this.formation = formation;
        this.liga = liga;
    }

    // Getter und Setter

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

    public FormationEntity getFormation() {
        return formation;
    }

    public void setFormation(FormationEntity formation) {
        this.formation = formation;
    }

    public LigaEntity getLiga() {
        return liga;
    }

    public void setLiga(LigaEntity liga) {
        this.liga = liga;
    }

    public List<SpielerEntity> getSpieler() {
        return spieler;
    }

    public void setSpieler(List<SpielerEntity> spieler) {
        this.spieler = spieler;
    }
}
