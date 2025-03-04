package com.sportverein.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Torwart extends Spieler {
    private int reaktion;
    private int fangsicherheit;
    
    public Torwart(String name, int alter, double gehalt, int trikotnummer, 
                   int reaktion, int fangsicherheit) {
        super(null, name, alter, gehalt, trikotnummer, "Torwart", null);
        this.reaktion = reaktion;
        this.fangsicherheit = fangsicherheit;
    }
}
