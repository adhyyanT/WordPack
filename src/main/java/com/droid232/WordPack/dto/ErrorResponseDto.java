package com.droid232.WordPack.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private List<String> errors;

    public ErrorResponseDto(List<String> errors) {
        this.errors = errors.stream().map(String::valueOf).collect(Collectors.toList());
    }
}
