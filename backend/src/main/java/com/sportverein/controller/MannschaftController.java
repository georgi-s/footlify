package com.sportverein.controller;

import com.sportverein.dto.MannschaftDTO;
import com.sportverein.mapper.MannschaftDTOMapper;
import com.sportverein.mapper.MannschaftMapper;
import com.sportverein.model.Mannschaft;
import com.sportverein.service.MannschaftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mannschaften")
public class MannschaftController {

    private final MannschaftService service;

    public MannschaftController(MannschaftService service) {
        this.service = service;
    }

    // GET /api/mannschaften - Alle Mannschaften abrufen
    @GetMapping
    public List<MannschaftDTO> getAllMannschaften() {
        List<Mannschaft> mannschaften = service.getAllMannschaften();
        return mannschaften.stream()
                .map(MannschaftDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /api/mannschaften/{id} - Mannschaft anhand der ID abrufen
    @GetMapping("/{id}")
    public MannschaftDTO getMannschaftById(@PathVariable Long id) {
        Mannschaft mannschaft = service.getMannschaftById(id);
        return MannschaftDTOMapper.toDTO(mannschaft);
    }

    // POST /api/mannschaften - Neue Mannschaft erstellen
    @PostMapping
    public MannschaftDTO createMannschaft(@RequestBody MannschaftDTO dto) {
        Mannschaft mannschaft = MannschaftDTOMapper.toModel(dto);
        Mannschaft saved = service.createMannschaft(mannschaft);
        // return MannschaftDTOMapper.toDTO(saved);
        return MannschaftDTOMapper.fromEntity(MannschaftMapper.toEntity(saved));
    }

    // PUT /api/mannschaften/{id} - Mannschaft aktualisieren
    @PutMapping("/{id}")
    public MannschaftDTO updateMannschaft(@PathVariable Long id, @RequestBody MannschaftDTO dto) {
        Mannschaft mannschaft = MannschaftDTOMapper.toModel(dto);
        Mannschaft updated = service.updateMannschaft(id, mannschaft);
        return MannschaftDTOMapper.toDTO(updated);
    }

    // DELETE /api/mannschaften/{id} - Mannschaft löschen
    @DeleteMapping("/{id}")
    public void deleteMannschaft(@PathVariable Long id) {
        service.deleteMannschaft(id);
    }
}
