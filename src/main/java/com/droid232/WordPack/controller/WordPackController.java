package com.droid232.WordPack.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.droid232.WordPack.dto.WordPackDetailsResponse;
import com.droid232.WordPack.dto.WordPackRequestDto;
import com.droid232.WordPack.dto.WordPackResponse;
import com.droid232.WordPack.entity.User;
import com.droid232.WordPack.service.WordPackService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("wordPack")
@RequiredArgsConstructor
public class WordPackController {

    private final WordPackService wordPackService;

    @PostMapping("create")
    public ResponseEntity<WordPackResponse> createWordPack(
            @Valid @RequestBody WordPackRequestDto wordPackRequestDto) {

        return new ResponseEntity<>(wordPackService.createWordPack(wordPackRequestDto), HttpStatus.OK);
    }

    @GetMapping("{wordPackId}")
    public ResponseEntity<WordPackResponse> getWordPack(@PathVariable Long wordPackId) {
        return new ResponseEntity<>(wordPackService.getWordPackDetails(wordPackId), HttpStatus.OK);
    }

    @GetMapping("my")
    public ResponseEntity<List<WordPackDetailsResponse>> getMyWordPacks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(wordPackService.getMyWordPacks(user.getId()), HttpStatus.OK);
    }
}
