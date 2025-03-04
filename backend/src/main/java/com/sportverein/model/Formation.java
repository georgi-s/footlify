package com.sportverein.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int verteidiger;
    private int mittelfeldspieler;
    private int stuermer;
    
    @OneToOne(mappedBy = "formation")
    private Mannschaft mannschaft;
}
