package org.soneech.truereview.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserFullInfoResponse implements UserInfoResponse {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @JsonProperty("reviews_count")
    private int reviewsCount;
}
