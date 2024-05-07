package org.soneech.truereview.dto.response.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewItemResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String name;
}