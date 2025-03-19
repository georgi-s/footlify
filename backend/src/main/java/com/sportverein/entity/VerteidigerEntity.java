package com.sportverein.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("VerteidigerEntity")
public class VerteidigerEntity extends SpielerEntity {

    private int geblockteAngriffe;
    private int gewonneneZweikaempfe;
    private double passquote;
    
    public VerteidigerEntity() {
    }
    
    public VerteidigerEntity(String nachname, String vorname, Date geburtsdatum, int gespielteSpiele,
                              boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten,
                              int geblockteAngriffe, int gewonneneZweikaempfe, double passquote) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geblockteAngriffe = geblockteAngriffe;
        this.gewonneneZweikaempfe = gewonneneZweikaempfe;
        this.passquote = passquote;
    }

    // Getter und Setter

    public int getGeblockteAngriffe() {
        return geblockteAngriffe;
    }

    public void setGeblockteAngriffe(int geblockteAngriffe) {
        this.geblockteAngriffe = geblockteAngriffe;
    }

    public int getGewonneneZweikaempfe() {
        return gewonneneZweikaempfe;
    }

    public void setGewonneneZweikaempfe(int gewonneneZweikaempfe) {
        this.gewonneneZweikaempfe = gewonneneZweikaempfe;
    }

    public double getPassquote() {
        return passquote;
    }

    public void setPassquote(double passquote) {
        this.passquote = passquote;
    }
}
