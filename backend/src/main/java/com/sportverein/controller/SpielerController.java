package com.sportverein.controller;

import com.sportverein.entity.Spieler;
import com.sportverein.entity.Stuermer;
import com.sportverein.entity.Torwart;
import com.sportverein.entity.Verteidiger;
import com.sportverein.entity.Mittelfeldspieler;
import com.sportverein.model.SpielerDTO;
import com.sportverein.model.StuermerDTO;
import com.sportverein.mapper.SpielerMapper;
import com.sportverein.service.SpielerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spieler")
@CrossOrigin(origins = "http://localhost:3000")
public class SpielerController {

    @Autowired
    private SpielerService spielerService;
    
    @Autowired
    private SpielerMapper spielerMapper;

    @GetMapping
    public List<SpielerDTO> getAllSpieler() {
        return spielerService.getAllSpieler().stream()
                .map(spielerMapper::toDTO)
                .collect(Collectors.toList());
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
    public List<StuermerDTO> getAllStuermer() {
        return spielerService.getAllStuermer().stream()
                .map(spielerMapper::toStuermerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpielerDTO> getSpielerById(@PathVariable Long id) {
        return spielerService.getSpielerById(id)
                .map(spielerMapper::toDTO)
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
    public ResponseEntity<StuermerDTO> getStuermerById(@PathVariable Long id) {
        return spielerService.getStuermerById(id)
                .map(spielerMapper::toStuermerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mannschaft/{mannschaftId}")
    public List<SpielerDTO> getSpielerByMannschaft(@PathVariable Long mannschaftId) {
        return spielerService.getSpielerByMannschaft(mannschaftId).stream()
                .map(spielerMapper::toDTO)
                .collect(Collectors.toList());
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
    public ResponseEntity<StuermerDTO> createStuermer(@RequestBody StuermerDTO stuermerDTO) {
        try {
            Stuermer stuermer = spielerMapper.toStuermerEntity(stuermerDTO);
            Stuermer savedStuermer = spielerService.saveStuermer(stuermer);
            return ResponseEntity.ok(spielerMapper.toStuermerDTO(savedStuermer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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
