package org.soneech.truereview.dto.response.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;

public record BadParamsResponse(
    @NotBlank
    String code,

    @NotBlank
    String message,

    @NotEmpty
    @JsonProperty("fields_errors")
    Map<String, Set<String>> fieldsErrors
) {
}
