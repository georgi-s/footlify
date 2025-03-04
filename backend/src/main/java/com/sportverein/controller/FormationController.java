package com.sportverein.controller;

import com.sportverein.entity.Formation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formationen")
@CrossOrigin(origins = "http://localhost:3000")
public class FormationController {

    @GetMapping
    public Formation[] getAllFormationen() {
        return Formation.values();
    }

    @GetMapping("/{name}")
    public Formation getFormationByName(@PathVariable String name) {
        return Formation.valueOf(name.toUpperCase());
    }
}
