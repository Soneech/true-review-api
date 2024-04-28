package org.soneech.truereview.dto.response.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleResponse {

    @NotBlank
    private String name;
}
