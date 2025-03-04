package com.sportverein.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Turnier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ort;
    private String datum;
    private int insgPreisgeld;
    
    @ManyToMany
    @JoinTable(
        name = "turnier_teilnehmer",
        joinColumns = @JoinColumn(name = "turnier_id"),
        inverseJoinColumns = @JoinColumn(name = "mannschaft_id")
    )
    private List<Mannschaft> teilnehmer = new ArrayList<>();
    
    private String siegerMannschaft;

    public void siegerFestlegen(Mannschaft sieger) {
        this.siegerMannschaft = sieger.getName();
        preisGeldAuszahlen(sieger, insgPreisgeld);
    }
    
    private void preisGeldAuszahlen(Mannschaft team, int betrag) {
        // In einer realen Anwendung würde hier die Preisgeldzahlung abgewickelt
        System.out.println("Das Team " + team.getName() + " erhält ein Preisgeld in Höhe von " + betrag + " Euro!");
    }
    
    public void turnierAusspielen() {
        System.out.println("Das Turnier beginnt!");
        // Hier könnte die Turnier-Logik implementiert werden
    }

    @Override
    public String toString() {
        return ort;
    }
}
