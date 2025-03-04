package com.sportverein.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stuermer extends Spieler {
    private int schusskraft;
    private int technik;
    
    public Stuermer(String name, int alter, double gehalt, int trikotnummer, 
                    int schusskraft, int technik) {
        super(null, name, alter, gehalt, trikotnummer, "Stuermer", null);
        this.schusskraft = schusskraft;
        this.technik = technik;
    }
}
