package com.sportverein.controller;

import com.sportverein.model.Mannschaft;
import com.sportverein.model.Formation;
import com.sportverein.model.Liga;
import com.sportverein.service.MannschaftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mannschaften")
@CrossOrigin(origins = "http://localhost:3000")
public class MannschaftController {

    @Autowired
    private MannschaftService mannschaftService;

    @GetMapping
    public List<Mannschaft> getAllMannschaften() {
        return mannschaftService.getAllMannschaften();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mannschaft> getMannschaftById(@PathVariable Long id) {
        return mannschaftService.getMannschaftById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/liga/{liga}")
    public List<Mannschaft> getMannschaftenByLiga(@PathVariable String liga) {
        try {
            Liga ligaEnum = Liga.valueOf(liga.toUpperCase());
            return mannschaftService.getMannschaftenByLiga(ligaEnum);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Mannschaft> getMannschaftByName(@PathVariable String name) {
        Mannschaft mannschaft = mannschaftService.getMannschaftByName(name);
        return mannschaft != null ? ResponseEntity.ok(mannschaft) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Mannschaft> createMannschaft(@RequestBody Mannschaft mannschaft) {
        try {
            return ResponseEntity.ok(mannschaftService.saveMannschaft(mannschaft));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mannschaft> updateMannschaft(@PathVariable Long id, @RequestBody Mannschaft mannschaftDetails) {
        try {
            return mannschaftService.getMannschaftById(id)
                    .map(mannschaft -> {
                        mannschaft.setName(mannschaftDetails.getName());
                        mannschaft.setSpieler(mannschaftDetails.getSpieler());
                        mannschaft.setLiga(mannschaftDetails.getLiga());
                        mannschaft.setFormation(mannschaftDetails.getFormation());
                        return ResponseEntity.ok(mannschaftService.saveMannschaft(mannschaft));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMannschaft(@PathVariable Long id) {
        return mannschaftService.getMannschaftById(id)
                .map(mannschaft -> {
                    mannschaftService.deleteMannschaft(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
