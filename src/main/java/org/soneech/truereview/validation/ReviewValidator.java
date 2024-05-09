package org.soneech.truereview.validation;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
@RequiredArgsConstructor
public class ReviewValidator implements Validator {

    @Override
    public boolean supports(Class<?> targetClass) {
        return Review.class.equals(targetClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Review review = (Review) target;

        if ((review.getAdvantages() == null || review.getAdvantages().isBlank()) &&
                (review.getDisadvantages() == null || review.getDisadvantages().isBlank()) &&
                (review.getNote() == null || review.getNote().isBlank())) {
            errors.rejectValue("note", HttpStatus.BAD_REQUEST.name(),
                    "Хотя бы одно поле должно быть заполнено");
        }
    }
}
