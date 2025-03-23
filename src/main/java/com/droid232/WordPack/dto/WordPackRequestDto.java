package com.droid232.WordPack.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.droid232.WordPack.entity.WordPack;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordPackRequestDto {

    @NotEmpty(message = "Title is required.")
    @Size(max = 40, message = "Title should not be more than 40 characters long.", min = 1)
    private String title;

    @Size(max = 300, message = "Description should not be more than 300 characters long.", min = 1)
    private String description;

    @NotNull(message = "Is Public is required.")
    private Boolean isPublic = false;

    @Valid
    private List<WordRequestDto> words = new ArrayList<>();

    public static WordPack toWordPack(WordPackRequestDto wordPackRequestDto) {
        return WordPack
                .builder()
                .description(wordPackRequestDto.getDescription())
                .title(wordPackRequestDto.getTitle())
                .isPublic(wordPackRequestDto.getIsPublic())
                .words(
                        wordPackRequestDto.words.stream().map(WordRequestDto::tWord).collect(Collectors.toList()))

                .build();
    }
}
