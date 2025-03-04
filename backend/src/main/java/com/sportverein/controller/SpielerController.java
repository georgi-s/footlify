package com.sportverein.controller;

import com.sportverein.entity.*;
import com.sportverein.service.SpielerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spieler")
@CrossOrigin(origins = "http://localhost:3000")
public class SpielerController {

    @Autowired
    private SpielerService spielerService;

    @GetMapping
    public List<Spieler> getAllSpieler() {
        return spielerService.getAllSpieler();
    }

    @GetMapping("/torwart")
    public List<Torwart> getAllTorwart() {
        return spielerService.getAllTorwart();
    }

    @GetMapping("/verteidiger")
    public List<Verteidiger> getAllVerteidiger() {
        return spielerService.getAllVerteidiger();
    }

    @GetMapping("/mittelfeldspieler")
    public List<Mittelfeldspieler> getAllMittelfeldspieler() {
        return spielerService.getAllMittelfeldspieler();
    }

    @GetMapping("/stuermer")
    public List<Stuermer> getAllStuermer() {
        return spielerService.getAllStuermer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spieler> getSpielerById(@PathVariable Long id) {
        return spielerService.getSpielerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/torwart/{id}")
    public ResponseEntity<Torwart> getTorwartById(@PathVariable Long id) {
        return spielerService.getTorwartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/verteidiger/{id}")
    public ResponseEntity<Verteidiger> getVerteidigerById(@PathVariable Long id) {
        return spielerService.getVerteidigerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mittelfeldspieler/{id}")
    public ResponseEntity<Mittelfeldspieler> getMittelfeldspielerById(@PathVariable Long id) {
        return spielerService.getMittelfeldspielerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stuermer/{id}")
    public ResponseEntity<Stuermer> getSturmerById(@PathVariable Long id) {
        return spielerService.getStuermerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mannschaft/{mannschaftId}")
    public List<Spieler> getSpielerByMannschaft(@PathVariable Long mannschaftId) {
        return spielerService.getSpielerByMannschaft(mannschaftId);
    }

    @PostMapping("/torwart")
    public Torwart createTorwart(@RequestBody Torwart torwart) {
        return spielerService.saveTorwart(torwart);
    }

    @PostMapping("/verteidiger")
    public Verteidiger createVerteidiger(@RequestBody Verteidiger verteidiger) {
        return spielerService.saveVerteidiger(verteidiger);
    }

    @PostMapping("/mittelfeldspieler")
    public Mittelfeldspieler createMittelfeldspieler(@RequestBody Mittelfeldspieler mittelfeldspieler) {
        return spielerService.saveMittelfeldspieler(mittelfeldspieler);
    }

    @PostMapping("/stuermer")
    public Stuermer createStuermer(@RequestBody Stuermer stuermer) {
        return spielerService.saveStuermer(stuermer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spieler> updateSpieler(@PathVariable Long id, @RequestBody Spieler spielerDetails) {
        Optional<Spieler> existingSpielertOpt = spielerService.getSpielerById(id);
        
        if (existingSpielertOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Spieler existingPlayer = existingSpielertOpt.get();
        existingPlayer.setVorname(spielerDetails.getVorname());
        existingPlayer.setNachname(spielerDetails.getNachname());
        existingPlayer.setGeburtsdatum(spielerDetails.getGeburtsdatum());
        existingPlayer.setGespielteSpiele(spielerDetails.getGespielteSpiele());
        existingPlayer.setGesperrt(spielerDetails.isGesperrt());
        existingPlayer.setVereinsbeitritt(spielerDetails.getVereinsbeitritt());
        existingPlayer.setRoteKarten(spielerDetails.getRoteKarten());
        existingPlayer.setGelbeKarten(spielerDetails.getGelbeKarten());
        existingPlayer.setMannschaft(spielerDetails.getMannschaft());
        
        if (existingPlayer instanceof Torwart) {
            return ResponseEntity.ok(spielerService.saveTorwart((Torwart) existingPlayer));
        } else if (existingPlayer instanceof Verteidiger) {
            return ResponseEntity.ok(spielerService.saveVerteidiger((Verteidiger) existingPlayer));
        } else if (existingPlayer instanceof Mittelfeldspieler) {
            return ResponseEntity.ok(spielerService.saveMittelfeldspieler((Mittelfeldspieler) existingPlayer));
        } else if (existingPlayer instanceof Stuermer) {
            return ResponseEntity.ok(spielerService.saveStuermer((Stuermer) existingPlayer));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpieler(@PathVariable Long id) {
        return spielerService.getSpielerById(id)
                .map(spieler -> {
                    spielerService.deleteSpieler(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
