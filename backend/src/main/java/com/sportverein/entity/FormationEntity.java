package com.sportverein.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Repräsentiert eine Formation in der Datenbank
 */
@Entity
@Table(name = "formation")
public class FormationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "bezeichnung", nullable = false)
    private String bezeichnung;
    
    @Column(name = "verteidiger_anzahl", nullable = false)
    private int verteidigerAnzahl;
    
    @Column(name = "mittelfeldspieler_anzahl", nullable = false)
    private int mittelfeldspielerAnzahl;
    
    @Column(name = "stuermer_anzahl", nullable = false)
    private int stuermerAnzahl;
    
    @OneToMany(mappedBy = "formation")
    private List<MannschaftEntity> mannschaften;
    
    // Konstruktoren
    public FormationEntity() {
    }
    
    public FormationEntity(String bezeichnung, int verteidigerAnzahl, int mittelfeldspielerAnzahl, int stuermerAnzahl) {
        this.bezeichnung = bezeichnung;
        this.verteidigerAnzahl = verteidigerAnzahl;
        this.mittelfeldspielerAnzahl = mittelfeldspielerAnzahl;
        this.stuermerAnzahl = stuermerAnzahl;
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getVerteidigerAnzahl() {
        return verteidigerAnzahl;
    }

    public void setVerteidigerAnzahl(int verteidigerAnzahl) {
        this.verteidigerAnzahl = verteidigerAnzahl;
    }

    public int getMittelfeldspielerAnzahl() {
        return mittelfeldspielerAnzahl;
    }

    public void setMittelfeldspielerAnzahl(int mittelfeldspielerAnzahl) {
        this.mittelfeldspielerAnzahl = mittelfeldspielerAnzahl;
    }

    public int getStuermerAnzahl() {
        return stuermerAnzahl;
    }

    public void setStuermerAnzahl(int stuermerAnzahl) {
        this.stuermerAnzahl = stuermerAnzahl;
    }

    public List<MannschaftEntity> getMannschaften() {
        return mannschaften;
    }

    public void setMannschaften(List<MannschaftEntity> mannschaften) {
        this.mannschaften = mannschaften;
    }
}