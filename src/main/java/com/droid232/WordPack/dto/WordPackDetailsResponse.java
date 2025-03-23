package com.droid232.WordPack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class WordPackDetailsResponse {

    private Long wordPackId;

    private String title;

    private String description;

    @Builder.Default
    private Boolean isPublic = false;

    public WordPackDetailsResponse(Long wordPackId, String title, String description, Boolean isPublic) {
        this.wordPackId = wordPackId;
        this.title = title;
        this.description = description;
        this.isPublic = isPublic;
    }

}
