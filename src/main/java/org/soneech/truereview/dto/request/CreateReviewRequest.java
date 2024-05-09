package org.soneech.truereview.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CreateReviewRequest(

    @NotNull(message = "Нужно найти и выбрать предмет отзыва")
    @JsonProperty("item_id")
    Long itemId,

    @NotNull(message = "Не может быть пустым")
    @Min(value = 1, message = "Значение должно находиться в диапазоне от 1 до 5")
    @Max(value = 5, message = "Значение должно находиться в диапазоне от 1 до 5")
    Short rating,

    @Size(max = 1000, message = "Должно содержать не более 1000 символов")
    String advantages,

    @Size(max = 1000, message = "Должно содержать не более 1000 символов")
    String disadvantages,

    @Size(max = 1000, message = "Должно содержать не более 1000 символов")
    String note,

    @NotNull(message = "Не может быть пустым")
    @JsonProperty("category_id")
    Long categoryId
) {
}
