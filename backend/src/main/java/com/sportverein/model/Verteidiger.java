package com.sportverein.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Verteidiger extends Spieler {
    private int zweikampfstaerke;
    private int kopfballstaerke;
    
    public Verteidiger(String name, int alter, double gehalt, int trikotnummer, 
                      int zweikampfstaerke, int kopfballstaerke) {
        super(null, name, alter, gehalt, trikotnummer, "Verteidiger", null);
        this.zweikampfstaerke = zweikampfstaerke;
        this.kopfballstaerke = kopfballstaerke;
    }
}
