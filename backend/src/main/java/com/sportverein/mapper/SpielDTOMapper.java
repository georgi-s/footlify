package com.sportverein.mapper;

import com.sportverein.dto.SpielDTO;
import com.sportverein.model.Mannschaft;
import com.sportverein.model.Spiel;
import org.springframework.stereotype.Component;

/**
 * Mapper zum Konvertieren zwischen Spiel-DTOs und Domänenmodellen
 */
@Component
public class SpielDTOMapper {
    
    /**
     * Konvertiert ein Spiel-Domänenmodell in ein SpielDTO
     * @param model Das zu konvertierende Domänenmodell
     * @return Das konvertierte DTO
     */
    public static SpielDTO toDTO(Spiel model) {
        if (model == null) {
            return null;
        }
        
        SpielDTO dto = new SpielDTO();
        dto.setId(model.getId());
        
        // Heimmannschaft-Informationen
        if (model.getHeimMannschaft() != null) {
            dto.setHeimMannschaftId(model.getHeimMannschaft().getId());
            dto.setHeimMannschaftName(model.getHeimMannschaft().getName());
        }
        
        // Gastmannschaft-Informationen
        if (model.getGastMannschaft() != null) {
            dto.setGastMannschaftId(model.getGastMannschaft().getId());
            dto.setGastMannschaftName(model.getGastMannschaft().getName());
        }
        
        dto.setDatum(model.getDatum());
        dto.setUhrzeit(model.getUhrzeit());
        dto.setOrt(model.getOrt());
        dto.setLiga(model.getLiga());
        dto.setStatus(model.getStatus());
        dto.setHeimTore(model.getHeimTore());
        dto.setGastTore(model.getGastTore());
        
        return dto;
    }
    
    /**
     * Konvertiert ein SpielDTO in ein Spiel-Domänenmodell
     * @param dto Das zu konvertierende DTO
     * @return Das konvertierte Domänenmodell
     */
    public static Spiel toModel(SpielDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Spiel model = new Spiel();
        model.setId(dto.getId());
        
        // Heimmannschaft-Stub (ID wird verwendet, um die vollständige Mannschaft später im Service zu laden)
        if (dto.getHeimMannschaftId() != null) {
            Mannschaft heimMannschaft = new Mannschaft();
            heimMannschaft.setId(dto.getHeimMannschaftId());
            heimMannschaft.setName(dto.getHeimMannschaftName());
            model.setHeimMannschaft(heimMannschaft);
        }
        
        // Gastmannschaft-Stub (ID wird verwendet, um die vollständige Mannschaft später im Service zu laden)
        if (dto.getGastMannschaftId() != null) {
            Mannschaft gastMannschaft = new Mannschaft();
            gastMannschaft.setId(dto.getGastMannschaftId());
            gastMannschaft.setName(dto.getGastMannschaftName());
            model.setGastMannschaft(gastMannschaft);
        }
        
        model.setDatum(dto.getDatum());
        model.setUhrzeit(dto.getUhrzeit());
        model.setOrt(dto.getOrt());
        model.setLiga(dto.getLiga());
        model.setStatus(dto.getStatus());
        model.setHeimTore(dto.getHeimTore());
        model.setGastTore(dto.getGastTore());
        
        return model;
    }
}
