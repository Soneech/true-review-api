package org.soneech.truereview.dto.response.errors;

import jakarta.validation.constraints.NotBlank;

public record BadRequestResponse(
    @NotBlank
    String code,

    @NotBlank
    String message
) {
}
