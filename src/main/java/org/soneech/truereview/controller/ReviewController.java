package org.soneech.truereview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.request.CreateReviewRequest;
import org.soneech.truereview.dto.request.CreateReviewRequestWithNewItem;
import org.soneech.truereview.dto.response.review.ReviewFullInfoResponse;
import org.soneech.truereview.dto.response.review.ReviewShortResponse;
import org.soneech.truereview.dto.response.success.SuccessOperationResponse;
import org.soneech.truereview.exception.*;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.model.Review;
import org.soneech.truereview.service.ReviewService;
import org.soneech.truereview.util.ErrorsUtil;
import org.soneech.truereview.validation.ReviewValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final DefaultModelMapper mapper;

    private final ReviewValidator reviewValidator;

    private final ErrorsUtil errorsUtil;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getAllReviews() {
        return ResponseEntity.ok(mapper.convertToListWithReviewShortResponse(reviewService.findAll()));
    }

    @GetMapping("/users/{userId}/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getUserReviews(@PathVariable("userId") long userId)
            throws UserNotFoundException {
        return ResponseEntity.ok(mapper.convertToListWithReviewShortResponse(reviewService.findUserReviews(userId)));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewFullInfoResponse> getUserReview(@PathVariable("id") long id)
                                                                    throws ReviewNotFoundException {
        return ResponseEntity.ok(mapper.convertToReviewFullInfoResponse(reviewService.findById(id)));
    }

    @GetMapping("/categories/{id}/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getReviewsForCategory(@PathVariable("id") long categoryId)
            throws CategoryNotFoundException {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewShortResponse(reviewService.findReviewsForCategory(categoryId)));
    }

    @GetMapping("/items/{id}/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getReviewsForItem(@PathVariable("id") long itemId)
            throws ReviewItemNotFoundException {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewShortResponse(reviewService.findReviewsByItem(itemId)));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<SuccessOperationResponse> deleteReview(@PathVariable("id") long id)
            throws ReviewNotFoundException, BadRequestException {

        reviewService.deleteUserReview(id);
        return ResponseEntity.ok(new SuccessOperationResponse(HttpStatus.OK.toString(), "Отзыв удалён"));
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewFullInfoResponse> createReview(@RequestBody @Valid CreateReviewRequest request,
                                                               BindingResult bindingResult) {
        Review review = mapper.convertToReview(request);
        validateReview(review, bindingResult);

        Review savedReview = reviewService.createReview(review, request.categoryId(), request.itemId());
        return ResponseEntity.ok(mapper.convertToReviewFullInfoResponse(savedReview));
    }

    @PostMapping("/reviews-items")
    public ResponseEntity<ReviewFullInfoResponse> createReviewWithItem(@RequestBody @Valid
                                                                           CreateReviewRequestWithNewItem request,
                                                                       BindingResult bindingResult) {
        Review review = mapper.convertToReview(request);
        validateReview(review, bindingResult);

        Review savedReview = reviewService.createReviewAndNewItem(review, request.categoryId(), request.itemName());
        return ResponseEntity.ok(mapper.convertToReviewFullInfoResponse(savedReview));

    }

    public void validateReview(Review review, BindingResult bindingResult) {
        reviewValidator.validate(review, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new CreateReviewException(HttpStatus.BAD_REQUEST,
                    errorsUtil.getFieldsErrors(bindingResult), "Некорректные данные отзыва");
        }
    }
}
