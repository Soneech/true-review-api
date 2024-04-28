package org.soneech.truereview.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPublicInfoResponse implements UserInfoResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @JsonProperty("reviews_count")
    private int reviewsCount;
}
