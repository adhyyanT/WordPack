package com.droid232.WordPack.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.droid232.WordPack.dto.WordPackDetailsResponse;
import com.droid232.WordPack.dto.WordPackResponse;
import com.droid232.WordPack.dto.WordResponseDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordPack {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long wordPackId;

        @Column(length = 300)
        private String description;

        @Column(length = 40)
        private String title;

        @Builder.Default
        @Column(nullable = false)
        private Boolean isPublic = false;

        @Builder.Default
        @OneToMany(mappedBy = "wordPack", cascade = { CascadeType.MERGE, CascadeType.REFRESH,
                        CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
        private List<Word> words = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        public static WordPackResponse toWordPackResponse(WordPack wordPack) {
                return WordPackResponse
                                .builder()
                                .description(wordPack.getDescription())
                                .title(wordPack.getTitle())
                                .isPublic(wordPack.isPublic)
                                .wordPackId(wordPack.getWordPackId())
                                .words(
                                                wordPack.words.stream().map(WordResponseDto::toWordResponseDto)
                                                                .collect(Collectors.toList()))
                                .build();
        }

        public static WordPackDetailsResponse toWordPackDetailsResponse(WordPack wordPack) {
                return WordPackDetailsResponse
                                .builder()
                                .description(wordPack.getDescription())
                                .title(wordPack.getTitle())
                                .isPublic(wordPack.isPublic)
                                .wordPackId(wordPack.getWordPackId())
                                .build();
        }

}
