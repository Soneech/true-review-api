package org.soneech.truereview.dto.response.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.soneech.truereview.dto.response.user.UserShortInfoResponse;

@Data
public class ReviewShortResponse {

    @NotNull
    private Long id;

    @NotNull
    private UserShortInfoResponse author;

    @NotNull
    @JsonProperty("review_item")
    private ReviewItemResponse reviewItem;

    @NotNull
    private Short rating;

    @NotBlank
    private String description;
}
