package com.example.demo.controller;

import com.example.demo.services.RoomFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roomfeatures")
@RequiredArgsConstructor
public class RoomFeatureController {

    private final RoomFeatureService roomFeatureService;

    @GetMapping("/status")
    public String status (){
        return "RoomFeature controller running";
    }
}
