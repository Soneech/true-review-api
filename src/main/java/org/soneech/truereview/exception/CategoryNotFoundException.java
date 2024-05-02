package org.soneech.truereview.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(Long categoryId) {
        super("Такая категория не найдена", HttpStatus.NOT_FOUND, categoryId);
    }
}
