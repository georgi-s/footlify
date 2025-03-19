package com.sportverein.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "turniere")
public class TurnierEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ort;
    
    @Temporal(TemporalType.DATE)
    private Date datum;
    
    private int insgPreisgeld;
    
    // Viele-zu-Viele Beziehung: Ein Turnier kann mehrere Mannschaften haben.
    @ManyToMany
    @JoinTable(name = "turnier_mannschaften",
               joinColumns = @JoinColumn(name = "turnier_id"),
               inverseJoinColumns = @JoinColumn(name = "mannschaft_id"))
    private List<MannschaftEntity> teilnehmer;
    
    @ManyToOne
    @JoinColumn(name = "sieger_mannschaft_id")
    private MannschaftEntity siegerMannschaft;
    
    public TurnierEntity() {
    }
    
    public TurnierEntity(String ort, Date datum, int insgPreisgeld, List<MannschaftEntity> teilnehmer) {
        this.ort = ort;
        this.datum = datum;
        this.insgPreisgeld = insgPreisgeld;
        this.teilnehmer = teilnehmer;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public String getOrt() {
        return ort;
    }

    public Date getDatum() {
        return datum;
    }

    public int getInsgPreisgeld() {
        return insgPreisgeld;
    }

    public List<MannschaftEntity> getTeilnehmer() {
        return teilnehmer;
    }

    public MannschaftEntity getSiegerMannschaft() {
        return siegerMannschaft;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setInsgPreisgeld(int insgPreisgeld) {
        this.insgPreisgeld = insgPreisgeld;
    }

    public void setTeilnehmer(List<MannschaftEntity> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    public void setSiegerMannschaft(MannschaftEntity siegerMannschaft) {
        this.siegerMannschaft = siegerMannschaft;
    }
}
