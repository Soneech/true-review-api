package org.soneech.truereview.security.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.request.AuthenticationRequest;
import org.soneech.truereview.dto.response.user.AuthenticatedUserResponse;
import org.soneech.truereview.exception.AuthException;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.model.User;
import org.soneech.truereview.security.util.JwtUtil;
import org.soneech.truereview.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final DefaultModelMapper mapper;

    public AuthenticatedUserResponse authenticate(AuthenticationRequest request) {
        Optional<User> user = userService.findByEmail(request.email());
        if (user.isEmpty()) {
            throw new AuthException(HttpStatus.BAD_REQUEST, Collections.emptyMap(), "Неверный логин или пароль");
        }

        String token = jwtUtil.generateToken(user.get().getEmail());
        return mapper.convertToAuthenticatedUserResponse(user.get(), token);
    }
}
