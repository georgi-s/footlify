package com.sportverein.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Repräsentiert eine Liga in der Datenbank
 */
@Entity
@Table(name = "liga")
public class LigaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "saison", nullable = false)
    private String saison;
    
    @OneToMany(mappedBy = "liga")
    private List<MannschaftEntity> mannschaften;
    
    @OneToMany(mappedBy = "liga")
    private List<SpielEntity> spiele;
    
    // Konstruktoren
    public LigaEntity() {
    }
    
    public LigaEntity(String name, String saison) {
        this.name = name;
        this.saison = saison;
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
    
    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public List<MannschaftEntity> getMannschaften() {
        return mannschaften;
    }

    public void setMannschaften(List<MannschaftEntity> mannschaften) {
        this.mannschaften = mannschaften;
    }

    public List<SpielEntity> getSpiele() {
        return spiele;
    }

    public void setSpiele(List<SpielEntity> spiele) {
        this.spiele = spiele;
    }
}