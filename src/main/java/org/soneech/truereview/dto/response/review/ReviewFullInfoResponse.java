package org.soneech.truereview.dto.response.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.soneech.truereview.dto.response.user.UserShortInfoResponse;
import org.soneech.truereview.model.Image;
import java.util.List;

@Data
public class ReviewFullInfoResponse {

    @NotNull
    private Long id;

    @NotNull
    @JsonProperty("review_item")
    private ReviewItemResponse reviewItem;

    @NotNull
    private Short rating;

    private String advantages;

    private String disadvantages;

    private String note;

    @NotNull
    private CategoryResponse category;

    private List<Image> images;

    @NotNull
    private UserShortInfoResponse author;
}
