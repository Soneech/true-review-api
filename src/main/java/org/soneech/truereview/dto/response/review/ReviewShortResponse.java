package org.soneech.truereview.dto.response.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewShortResponse {

    @NotNull
    private Long id;

    @NotBlank
    @JsonProperty("object_name")
    private String objectName;

    @NotNull
    private Short rating;
}
