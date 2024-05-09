package org.soneech.truereview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
    @NotBlank(message = "Не может быть пустым")
    @Size(min = 2, max = 200, message = "Должно содержать не менее 2 и не более 200 символов")
    String name
) {
}
