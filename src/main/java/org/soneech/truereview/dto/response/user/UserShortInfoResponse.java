package org.soneech.truereview.dto.response.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.soneech.truereview.dto.response.role.RoleResponse;

import java.util.List;

@Data
public class UserShortInfoResponse implements UserInfoResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotEmpty
    private List<RoleResponse> roles;
}
