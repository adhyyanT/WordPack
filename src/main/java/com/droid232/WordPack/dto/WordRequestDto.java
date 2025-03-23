package com.droid232.WordPack.dto;

import com.droid232.WordPack.entity.Word;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordRequestDto {
    @NotEmpty(message = "Description is required.")
    @Size(max = 300, message = "Description should not be more than 300 characters long.", min = 1)
    private String description;

    @NotEmpty(message = "Word Title is required.")
    @Size(max = 20, message = "Word Title should not be more than 20 characters long.", min = 1)
    private String title;

    public static Word tWord(WordRequestDto wordRequestDto) {
        return Word
                .builder()
                .description(wordRequestDto.getDescription())
                .title(wordRequestDto.getTitle())
                .build();
    }
}
