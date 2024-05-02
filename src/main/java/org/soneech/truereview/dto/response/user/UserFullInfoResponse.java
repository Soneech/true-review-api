package org.soneech.truereview.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.soneech.truereview.dto.response.role.RoleResponse;
import java.util.List;

@Data
public class UserFullInfoResponse implements UserInfoResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @JsonProperty("reviews_count")
    private int reviewsCount;

    @NotEmpty
    private List<RoleResponse> roles;
}
