package com.sportverein.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "spieler_type")
public abstract class Spieler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nachname;
    private String vorname;
    
    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    
    private int gespielteSpiele;
    private boolean gesperrt;
    
    @Temporal(TemporalType.DATE)
    private Date vereinsbeitritt;
    
    private int roteKarten;
    private int gelbeKarten;

    @ManyToOne
    @JoinColumn(name = "mannschaft_id")
    private Mannschaft mannschaft;

    public abstract String spielerstatistikAusgeben();
    public abstract double spielerBewertung();

    @Override
    public String toString() {
        return getVorname() + " " + getNachname();
    }
}
