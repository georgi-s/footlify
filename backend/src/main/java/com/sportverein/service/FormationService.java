package com.sportverein.service;

import com.sportverein.entity.Formation;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class FormationService {
    
    public List<Formation> getAllFormationen() {
        return Arrays.asList(Formation.values());
    }
    
    public Formation getFormationByName(String name) {
        return Formation.valueOf(name.toUpperCase());
    }
}
