package com.sportverein.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SpielerDTO {
    private Long id;
    private String nachname;
    private String vorname;
    private LocalDate geburtsdatum;
    private int gespielteSpiele;
    private boolean gesperrt;
    private LocalDate vereinsbeitritt;
    private int roteKarten;
    private int gelbeKarten;
    private String position;  // "TORWART", "VERTEIDIGER", "MITTELFELD", "STUERMER"
    private double bewertung; // Berechneter Wert
}
