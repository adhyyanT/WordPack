package com.droid232.WordPack.dto;

import com.droid232.WordPack.entity.Word;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordResponseDto {

    private Long wordId;
    private String description;
    private String title;

    public static WordResponseDto toWordResponseDto(Word word) {
        return WordResponseDto
                .builder()
                .description(word.getDescription())
                .title(word.getTitle())
                .wordId(word.getWordId())
                .build();
    }
}
