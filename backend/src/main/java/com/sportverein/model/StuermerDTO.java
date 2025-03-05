package com.sportverein.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StuermerDTO extends SpielerDTO {
    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;
    
    public StuermerDTO() {
        setPosition("STUERMER");
    }
}
