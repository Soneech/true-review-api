package org.soneech.truereview.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtConfig(

    @NotBlank
    String secret,

    @NotBlank
    String subject,

    @NotBlank
    String issuer,

    @NotNull
    Long ttl
) {
}
