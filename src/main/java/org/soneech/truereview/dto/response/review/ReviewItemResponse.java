package org.soneech.truereview.dto.response.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewItemResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private CategoryResponse category;

    @JsonProperty("reviews_count")
    private Integer reviewsCount;

    @JsonProperty("middle_rating")
    private Float middleRating;
}
