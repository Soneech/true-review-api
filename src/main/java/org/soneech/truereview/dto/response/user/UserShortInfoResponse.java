package org.soneech.truereview.dto.response.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserShortInfoResponse implements UserInfoResponse {

    @NotBlank
    private String name;
}
