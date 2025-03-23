package com.droid232.WordPack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.droid232.WordPack.dto.WordRequestDto;
import com.droid232.WordPack.dto.WordResponseDto;
import com.droid232.WordPack.service.WordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("word")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @PostMapping("create/{wordPackId}")
    public ResponseEntity<WordResponseDto> addWord(@Valid @RequestBody WordRequestDto wordRequestDto,
            @PathVariable Long wordPackId) {
        return new ResponseEntity<>(wordService.createWord(wordRequestDto, wordPackId), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{wordPackId}/{wordId}")
    public ResponseEntity<?> addWord(@PathVariable Long wordId,
            @PathVariable Long wordPackId) {
        wordService.deleteWord(wordId, wordPackId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/{wordPackId}/{wordId}")
    public ResponseEntity<WordResponseDto> updateWord(@Valid @RequestBody WordRequestDto wordRequestDto,
            @PathVariable Long wordPackId, @PathVariable Long wordId) {
        return new ResponseEntity<>(wordService.updateWord(wordRequestDto, wordPackId, wordId), HttpStatus.OK);
    }
}
