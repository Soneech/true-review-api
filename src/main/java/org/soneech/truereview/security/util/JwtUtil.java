package org.soneech.truereview.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soneech.truereview.configuration.JwtConfig;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtConfig config;

    private static final String EMAIL_CLAIM = "email";

    public String generateToken(String email) {
        OffsetDateTime currentDateTime = OffsetDateTime.now();
        OffsetDateTime expirationDate = currentDateTime.plusSeconds(config.ttl());
        return JWT.create()
                .withSubject(config.subject())
                .withClaim(EMAIL_CLAIM, email)
                .withIssuedAt(currentDateTime.toInstant())
                .withIssuer(config.issuer())
                .withExpiresAt(expirationDate.toInstant())
                .sign(Algorithm.HMAC256(config.secret()));
    }

    public String validateTokenAndRetrieveClaim(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(config.secret()))
                .withSubject(config.subject())
                .withIssuer(config.issuer())
                .build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(EMAIL_CLAIM).asString();
        } catch (JWTVerificationException exception) {
            log.error("Invalid jwt: %s".formatted(token));
            log.error(exception.getMessage());
            throw exception;
        }
    }

}
