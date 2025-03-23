package com.droid232.WordPack.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordPackResponse {

    private Long wordPackId;

    private String title;

    private String description;

    @Builder.Default
    private Boolean isPublic = false;

    @Builder.Default
    private List<WordResponseDto> words = new ArrayList<>();
}
