package com.sportverein.controller;

import com.sportverein.dto.SpielerDTO;
import com.sportverein.mapper.SpielerDTOMapper;
import com.sportverein.model.Spieler;
import com.sportverein.service.SpielerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spieler")
public class SpielerController {

    private final SpielerService service;

    public SpielerController(SpielerService service) {
        this.service = service;
    }

    // GET /api/spieler – Alle Spieler abrufen
    @GetMapping
    public List<SpielerDTO> getAllSpieler() {
        List<Spieler> spielerListe = service.getAllSpieler();
        return spielerListe.stream()
                .map(SpielerDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /api/spieler/{id} – Spieler anhand der ID abrufen
    @GetMapping("/{id}")
    public SpielerDTO getSpielerById(@PathVariable Long id) {
        Spieler spieler = service.getSpielerById(id);
        return SpielerDTOMapper.toDTO(spieler);
    }

    // POST /api/spieler – Neuen Spieler erstellen
    @PostMapping
    public SpielerDTO createSpieler(@RequestBody SpielerDTO dto) {
        // Konvertiere DTO in Domain-Modell (konkrete Unterklasse wird hier je nach spielerType instanziiert)
        Spieler spieler = SpielerDTOMapper.toModel(dto);
        Spieler created = service.createSpieler(spieler);
        return SpielerDTOMapper.toDTO(created);
    }

    // PUT /api/spieler/{id} – Spieler aktualisieren
    @PutMapping("/{id}")
    public SpielerDTO updateSpieler(@PathVariable Long id, @RequestBody SpielerDTO dto) {
        Spieler spieler = SpielerDTOMapper.toModel(dto);
        Spieler updated = service.updateSpieler(id, spieler);
        return SpielerDTOMapper.toDTO(updated);
    }

    // DELETE /api/spieler/{id} – Spieler löschen
    @DeleteMapping("/{id}")
    public void deleteSpieler(@PathVariable Long id) {
        service.deleteSpieler(id);
    }
}
