package com.sportverein.entity;

import com.sportverein.model.SpielStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Repräsentiert ein Spiel zwischen zwei Mannschaften in der Datenbank
 */
@Entity
@Table(name = "spiele")
public class SpielEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "heim_mannschaft_id", nullable = false)
    private MannschaftEntity heimMannschaft;
    
    @ManyToOne
    @JoinColumn(name = "gast_mannschaft_id", nullable = false)
    private MannschaftEntity gastMannschaft;
    
    @Column(nullable = false)
    private LocalDate datum;
    
    @Column(nullable = false)
    private String uhrzeit;
    
    @Column(nullable = false)
    private String ort;
    
    @Enumerated(EnumType.STRING)
    private SpielStatus status;
    
    private Integer heimTore;
    private Integer gastTore;
    
    @ManyToOne
    @JoinColumn(name = "liga_id")
    private LigaEntity liga;
    
    @ManyToOne
    @JoinColumn(name = "turnier_id")
    private TurnierEntity turnier;
    
    // Konstruktoren
    public SpielEntity() {
    }
    
    public SpielEntity(MannschaftEntity heimMannschaft, MannschaftEntity gastMannschaft, LocalDate datum, 
                       String uhrzeit, String ort, SpielStatus status) {
        this.heimMannschaft = heimMannschaft;
        this.gastMannschaft = gastMannschaft;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.ort = ort;
        this.status = status;
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MannschaftEntity getHeimMannschaft() {
        return heimMannschaft;
    }

    public void setHeimMannschaft(MannschaftEntity heimMannschaft) {
        this.heimMannschaft = heimMannschaft;
    }

    public MannschaftEntity getGastMannschaft() {
        return gastMannschaft;
    }

    public void setGastMannschaft(MannschaftEntity gastMannschaft) {
        this.gastMannschaft = gastMannschaft;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public SpielStatus getStatus() {
        return status;
    }

    public void setStatus(SpielStatus status) {
        this.status = status;
    }

    public Integer getHeimTore() {
        return heimTore;
    }

    public void setHeimTore(Integer heimTore) {
        this.heimTore = heimTore;
    }

    public Integer getGastTore() {
        return gastTore;
    }

    public void setGastTore(Integer gastTore) {
        this.gastTore = gastTore;
    }

    public LigaEntity getLiga() {
        return liga;
    }

    public void setLiga(LigaEntity liga) {
        this.liga = liga;
    }

    public TurnierEntity getTurnier() {
        return turnier;
    }

    public void setTurnier(TurnierEntity turnier) {
        this.turnier = turnier;
    }
}
