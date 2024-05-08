package org.soneech.truereview.exception;

import org.springframework.http.HttpStatus;

public class ReviewItemNotFoundException extends NotFoundException {
    public ReviewItemNotFoundException(Long itemId) {
        super("Такой предмет отзыва не найден", HttpStatus.NOT_FOUND, itemId);
    }
}
