package org.soneech.truereview.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInfoResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}
