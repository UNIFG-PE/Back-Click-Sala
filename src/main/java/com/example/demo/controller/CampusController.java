package com.example.demo.controller;

import com.example.demo.services.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/campus")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService campusService;

    @GetMapping("/status")
    public String status(){
        return "Campus controller running";
    }
}
