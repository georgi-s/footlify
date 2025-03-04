package com.sportverein.controller;

import com.sportverein.entity.Liga;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ligen")
@CrossOrigin(origins = "http://localhost:3000")
public class LigaController {

    @GetMapping
    public Liga[] getAllLigen() {
        return Liga.values();
    }

    @GetMapping("/{name}")
    public Liga getLigaByName(@PathVariable String name) {
        return Liga.valueOf(name.toUpperCase());
    }
}
