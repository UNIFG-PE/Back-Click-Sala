package com.example.demo.controller;

import com.example.demo.entities.Campus;
import com.example.demo.services.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/campus")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService campusService;

    @GetMapping("/status")
    public String status() {
        return "Campus controller running";
    }

    @GetMapping
    public List<Campus> findAll() {
        return campusService.findAll();
    }

    @PostMapping
    public Campus createCampus(@RequestBody Campus campus) {
        return campusService.createCampus(campus);
    }

    @PutMapping("/{id}")
    public Campus updateCampus(@PathVariable Long id, @RequestBody Campus campus) {
        return campusService.updateCampus(id, campus);
    }

    @DeleteMapping("/{id}")
    public void deleteCampus(@PathVariable Long id) {
        campusService.delete(id);
    }
}
