package org.soneech.truereview.exception;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends NotFoundException {

    public ReviewNotFoundException(long reviewId) {
        super("Такой отзыв не найден", HttpStatus.NOT_FOUND, reviewId);
    }
}
