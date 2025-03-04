package com.sportverein.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mittelfeldspieler extends Spieler {
    private int passgenauigkeit;
    private int ausdauer;
    
    public Mittelfeldspieler(String name, int alter, double gehalt, int trikotnummer, 
                            int passgenauigkeit, int ausdauer) {
        super(null, name, alter, gehalt, trikotnummer, "Mittelfeldspieler", null);
        this.passgenauigkeit = passgenauigkeit;
        this.ausdauer = ausdauer;
    }
}
