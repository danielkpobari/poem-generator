package com.spring_ai.Spring_AI;

import jakarta.validation.constraints.NotBlank;

public record PoemGenerationRequest(

        @NotBlank(message = "Genre is required")
        String genre,

        @NotBlank(message = "Theme is required")
        String theme
) {}
