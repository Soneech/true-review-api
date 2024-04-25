package org.soneech.truereview.validation;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.model.User;
import org.soneech.truereview.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> targetClass) {
        return User.class.equals(targetClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> foundByEmail = userService.findByEmail(user.getEmail());

        if (foundByEmail.isPresent() && !foundByEmail.get().getId().equals(user.getId())) {
            errors.rejectValue("email", HttpStatus.BAD_REQUEST.name(),
                    "Пользователь с такой почтой уже существует");
        }
    }
}
