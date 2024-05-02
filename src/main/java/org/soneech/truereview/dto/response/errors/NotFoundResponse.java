package org.soneech.truereview.dto.response.errors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotFoundResponse(
    @NotBlank
    String code,

    @NotBlank
    String message,

    @NotNull
    Long id
) {
}
