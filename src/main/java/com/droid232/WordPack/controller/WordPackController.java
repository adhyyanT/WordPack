package com.droid232.WordPack.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wordPack")
public class WordPackController {

    @PostMapping("create")
    public String createWordPack() {
        return "";
    }

}
