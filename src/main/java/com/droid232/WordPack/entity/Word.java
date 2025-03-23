package com.droid232.WordPack.entity;

import com.droid232.WordPack.dto.WordResponseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @Column(length = 300)
    private String description;

    @Column(length = 20)
    private String title;

    @ManyToOne
    @JoinColumn(name = "word_pack_id")
    private WordPack wordPack;

    public static WordResponseDto toWordResponseDto(Word word) {
        return WordResponseDto
                .builder()
                .wordId(word.getWordId())
                .title(word.getTitle())
                .description(word.getDescription())
                .build();
    }
}
