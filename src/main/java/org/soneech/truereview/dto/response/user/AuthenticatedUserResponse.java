package org.soneech.truereview.dto.response.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserResponse {
    @NotNull
    private UserShortInfoResponse user;

    @NotBlank
    private String token;
}
