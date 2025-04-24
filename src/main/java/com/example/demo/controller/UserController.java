package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "users")
public class UserController {

    @GetMapping
    public String StatusUser(){
        return "User controller running";
    }

}
