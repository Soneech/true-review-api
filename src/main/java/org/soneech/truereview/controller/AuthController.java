package org.soneech.truereview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.soneech.truereview.dto.request.AuthenticationRequest;
import org.soneech.truereview.dto.request.RegistrationRequest;
import org.soneech.truereview.dto.response.user.AuthenticatedUserResponse;
import org.soneech.truereview.dto.response.user.RegisteredUserResponse;
import org.soneech.truereview.exception.AuthException;
import org.soneech.truereview.exception.RegistrationException;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.model.User;
import org.soneech.truereview.security.service.AuthenticationService;
import org.soneech.truereview.service.UserService;
import org.soneech.truereview.util.ErrorsUtil;
import org.soneech.truereview.validation.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final DefaultModelMapper mapper;

    private final UserService userService;

    private final UserValidator userValidator;

    private final ErrorsUtil errorsUtil;

    private final AuthenticationService authenticationService;

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
    public ResponseEntity<AuthenticatedUserResponse> login(@RequestBody @Valid AuthenticationRequest request,
                                                           BindingResult bindingResult) throws AuthException {
        if (bindingResult.hasErrors()) {
            throw new AuthException(HttpStatus.BAD_REQUEST, errorsUtil.getFieldsErrors(bindingResult),
                    "Некорректные данные аутентификации");
        }

        AuthenticatedUserResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
