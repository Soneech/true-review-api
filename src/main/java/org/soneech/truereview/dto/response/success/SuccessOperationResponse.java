package org.soneech.truereview.dto.response.success;

import jakarta.validation.constraints.NotBlank;

public record SuccessOperationResponse(

    @NotBlank
    String code,

    @NotBlank
    String message
) {
}
