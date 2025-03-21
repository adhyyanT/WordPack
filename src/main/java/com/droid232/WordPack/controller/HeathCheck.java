package com.droid232.WordPack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("health")
public class HeathCheck {
    @GetMapping
    public String getHealth() {
        return "Good";
    }

}
