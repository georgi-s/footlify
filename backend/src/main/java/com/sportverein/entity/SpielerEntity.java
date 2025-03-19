package com.sportverein.entity;

import jakarta.persistence.*;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.util.Date;

@Entity
@Table(name = "spieler")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("SpielerEntity")
public class SpielerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nachname;
    private String vorname;
    
    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    
    @Column(name = "gespielte_spiele")
    private int gespielteSpiele;
    
    private boolean gesperrt;
    
    @Temporal(TemporalType.DATE)
    private Date vereinsbeitritt;
    
    @Column(name = "rote_karten")
    private int roteKarten;
    
    @Column(name = "gelbe_karten")
    private int gelbeKarten;
    
    // Neues Feld für "position" hinzufügen
    // Falls du möchtest, dass dieses Feld optional ist, setze nullable = true.
    @Column(nullable = true)
    private String position;

    @ManyToOne
    @JoinColumn(name = "mannschaft_id")
    private MannschaftEntity mannschaft;

    public SpielerEntity() {
    }
    
    public SpielerEntity(String nachname, String vorname, Date geburtsdatum, int gespielteSpiele, boolean gesperrt,
                           Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.gespielteSpiele = gespielteSpiele;
        this.gesperrt = gesperrt;
        this.vereinsbeitritt = vereinsbeitritt;
        this.roteKarten = roteKarten;
        this.gelbeKarten = gelbeKarten;
    }
    
    // Getter und Setter für bestehende Felder

    public Long getId() {
        return id;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public int getGespielteSpiele() {
        return gespielteSpiele;
    }

    public boolean isGesperrt() {
        return gesperrt;
    }

    public Date getVereinsbeitritt() {
        return vereinsbeitritt;
    }

    public int getRoteKarten() {
        return roteKarten;
    }

    public int getGelbeKarten() {
        return gelbeKarten;
    }

    public MannschaftEntity getMannschaft() {
        return mannschaft;
    }

    public void setMannschaft(MannschaftEntity mannschaft) {
        this.mannschaft = mannschaft;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setGespielteSpiele(int gespielteSpiele) {
        this.gespielteSpiele = gespielteSpiele;
    }

    public void setGesperrt(boolean gesperrt) {
        this.gesperrt = gesperrt;
    }

    public void setVereinsbeitritt(Date vereinsbeitritt) {
        this.vereinsbeitritt = vereinsbeitritt;
    }

    public void setRoteKarten(int roteKarten) {
        this.roteKarten = roteKarten;
    }

    public void setGelbeKarten(int gelbeKarten) {
        this.gelbeKarten = gelbeKarten;
    }

    // Getter und Setter für das neue Feld "position"
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
