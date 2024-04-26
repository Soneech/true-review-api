package org.soneech.truereview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soneech.truereview.dto.request.AuthenticationRequest;
import org.soneech.truereview.dto.request.RegistrationRequest;
import org.soneech.truereview.dto.response.user.RegisteredUserResponse;
import org.soneech.truereview.exception.AuthException;
import org.soneech.truereview.exception.RegistrationException;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.model.User;
import org.soneech.truereview.security.util.JwtUtil;
import org.soneech.truereview.service.UserService;
import org.soneech.truereview.util.ErrorsUtil;
import org.soneech.truereview.validation.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final DefaultModelMapper mapper;

    private final UserService userService;

    private final UserValidator userValidator;

    private final ErrorsUtil errorsUtil;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/registration")
    public ResponseEntity<RegisteredUserResponse> register(@RequestBody @Valid RegistrationRequest request,
                                                           BindingResult bindingResult) {
        User user = mapper.convertToUser(request);
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new RegistrationException(HttpStatus.BAD_REQUEST, errorsUtil.getFieldsErrors(bindingResult),
                    "Некорректные данные регистрации");
        }

        User savedUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertToRegisteredUserResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthenticationRequest request,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AuthException(HttpStatus.BAD_REQUEST, errorsUtil.getFieldsErrors(bindingResult),
                    "Некорректные данные аутентификации");
        }

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        try {
            authenticationManager.authenticate(authToken);
        } catch (AuthenticationException exception) {
            log.error("Invalid authentication, user credentials: %s".formatted(request));
            throw new AuthException(HttpStatus.BAD_REQUEST, Collections.emptyMap(), "Неверный логин или пароль");
        }

        String jwt = jwtUtil.generateToken(request.email());
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}
