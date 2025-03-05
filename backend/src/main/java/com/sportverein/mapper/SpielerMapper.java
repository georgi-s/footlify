package com.sportverein.mapper;

import com.sportverein.entity.Spieler;
import com.sportverein.entity.Stuermer;
import com.sportverein.model.SpielerDTO;
import com.sportverein.model.StuermerDTO;
import org.springframework.stereotype.Component;


@Component
public class SpielerMapper {
    
    public SpielerDTO toDTO(Spieler entity) {
        if (entity == null) return null;
        
        SpielerDTO dto;
        
        if (entity instanceof Stuermer) {
            dto = toStuermerDTO((Stuermer) entity);
        } else {
            dto = new SpielerDTO();
        }
        
        // Gemeinsame Felder mappen
        dto.setId(entity.getId());
        dto.setNachname(entity.getNachname());
        dto.setVorname(entity.getVorname());
        dto.setGeburtsdatum(entity.getGeburtsdatum());
        dto.setGespielteSpiele(entity.getGespielteSpiele());
        dto.setGesperrt(entity.isGesperrt());
        dto.setVereinsbeitritt(entity.getVereinsbeitritt());
        dto.setRoteKarten(entity.getRoteKarten());
        dto.setGelbeKarten(entity.getGelbeKarten());
        dto.setBewertung(entity.spielerBewertung());
        
        return dto;
    }
    
    public StuermerDTO toStuermerDTO(Stuermer entity) {
        if (entity == null) return null;
        
        StuermerDTO dto = new StuermerDTO();
        dto.setGeschosseneTore(entity.getGeschosseneTore());
        dto.setSchussgenauigkeit(entity.getSchussgenauigkeit());
        dto.setChancenverwertung(entity.getChancenverwertung());
        
        return dto;
    }
    
    public Spieler toEntity(SpielerDTO dto) {
        if (dto == null) return null;
        
        Spieler entity;
        
        if (dto instanceof StuermerDTO) {
            entity = toStuermerEntity((StuermerDTO) dto);
        } else {
            // Hier könnten weitere Spielertypen hinzugefügt werden
            throw new IllegalArgumentException("Unbekannter Spielertyp");
        }
        
        // Gemeinsame Felder mappen
        entity.setId(dto.getId());
        entity.setNachname(dto.getNachname());
        entity.setVorname(dto.getVorname());
        entity.setGeburtsdatum(dto.getGeburtsdatum());
        entity.setGespielteSpiele(dto.getGespielteSpiele());
        entity.setGesperrt(dto.isGesperrt());
        entity.setVereinsbeitritt(dto.getVereinsbeitritt());
        entity.setRoteKarten(dto.getRoteKarten());
        entity.setGelbeKarten(dto.getGelbeKarten());
        
        return entity;
    }
    
    public Stuermer toStuermerEntity(StuermerDTO dto) {
        if (dto == null) return null;
        
        Stuermer entity = new Stuermer();
        entity.setGeschosseneTore(dto.getGeschosseneTore());
        entity.setSchussgenauigkeit(dto.getSchussgenauigkeit());
        entity.setChancenverwertung(dto.getChancenverwertung());
        
        return entity;
    }
}
