package com.sportverein.controller;

import com.sportverein.entity.Turnier;
import com.sportverein.service.TurnierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turnier")
@CrossOrigin(origins = "http://localhost:3000")
public class TurnierController {

    @Autowired
    private TurnierService turnierService;

    @GetMapping
    public List<Turnier> getAllTurniere() {
        return turnierService.getAllTurniere();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turnier> getTurnierById(@PathVariable Long id) {
        Optional<Turnier> turnier = turnierService.getTurnierById(id);
        return turnier.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ort/{ort}")
    public List<Turnier> getTurniereByOrt(@PathVariable String ort) {
        return turnierService.getTurniereByOrt(ort);
    }

    @GetMapping("/datum/{datum}")
    public List<Turnier> getTurniereByDatum(@PathVariable String datum) {
        return turnierService.getTurniereByDatum(datum);
    }

    @GetMapping("/teilnehmer/{mannschaftId}")
    public List<Turnier> getTurniereByTeilnehmer(@PathVariable Long mannschaftId) {
        return turnierService.getTurniereByTeilnehmer(mannschaftId);
    }

    @PostMapping
    public ResponseEntity<Turnier> createTurnier(@RequestBody Turnier turnier) {
        try {
            Turnier newTurnier = turnierService.saveTurnier(turnier);
            return ResponseEntity.ok(newTurnier);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turnier> updateTurnier(@PathVariable Long id, @RequestBody Turnier turnierDetails) {
        try {
            return turnierService.getTurnierById(id)
                    .map(turnier -> {
                        turnier.setOrt(turnierDetails.getOrt());
                        turnier.setDatum(turnierDetails.getDatum());
                        turnier.setInsgPreisgeld(turnierDetails.getInsgPreisgeld());
                        turnier.setTeilnehmer(turnierDetails.getTeilnehmer());
                        turnier.setSiegerMannschaft(turnierDetails.getSiegerMannschaft());
                        return ResponseEntity.ok(turnierService.saveTurnier(turnier));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurnier(@PathVariable Long id) {
        return turnierService.getTurnierById(id)
                .map(turnier -> {
                    turnierService.deleteTurnier(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

