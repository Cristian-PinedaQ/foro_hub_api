package com.example.foro_hub_api.controller.dto;


import jakarta.validation.constraints.NotBlank;

public record TopicsDto(
        @NotBlank
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
