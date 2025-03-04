package com.sportverein.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Spieler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int alter;
    private double gehalt;
    private int trikotnummer;
    private String position;
    
    @ManyToOne
    @JoinColumn(name = "mannschaft_id")
    private Mannschaft mannschaft;
}
