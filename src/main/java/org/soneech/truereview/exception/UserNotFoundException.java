package org.soneech.truereview.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends NotFoundException {


    public UserNotFoundException(Long userId) {
        super("Такой пользователь не найден", HttpStatus.NOT_FOUND, userId);
    }
}
