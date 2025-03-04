package com.sportverein.service;

import com.sportverein.entity.Liga;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class LigaService {
    
    public List<Liga> getAllLigen() {
        return Arrays.asList(Liga.values());
    }
    
    public Liga getLigaByName(String name) {
        return Liga.valueOf(name.toUpperCase());
    }
}
