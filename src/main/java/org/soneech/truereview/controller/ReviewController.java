package org.soneech.truereview.controller;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.response.review.ReviewFullInfoResponse;
import org.soneech.truereview.dto.response.review.ReviewShortResponse;
import org.soneech.truereview.dto.response.success.SuccessOperationResponse;
import org.soneech.truereview.exception.BadRequestException;
import org.soneech.truereview.exception.CategoryNotFoundException;
import org.soneech.truereview.exception.ReviewNotFoundException;
import org.soneech.truereview.exception.UserNotFoundException;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final DefaultModelMapper mapper;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getAllReviews() {
        return ResponseEntity.ok(mapper.convertToListWithReviewShortResponse(reviewService.getAllReviews()));
    }

    @GetMapping("/users/{userId}/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getUserReviews(@PathVariable("userId") long userId)
            throws UserNotFoundException {
        return ResponseEntity.ok(mapper.convertToListWithReviewShortResponse(reviewService.getUserReviews(userId)));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewFullInfoResponse> getUserReview(@PathVariable("id") long id)
                                                                    throws ReviewNotFoundException {
        return ResponseEntity.ok(mapper.convertToReviewFullInfoResponse(reviewService.getReviewById(id)));
    }

    @GetMapping("/categories/{id}/reviews")
    public ResponseEntity<List<ReviewShortResponse>> getReviewsForCategory(@PathVariable("id") long categoryId)
            throws CategoryNotFoundException {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewShortResponse(reviewService.getReviewsForCategory(categoryId)));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<SuccessOperationResponse> deleteReview(@PathVariable("id") long id)
            throws ReviewNotFoundException, BadRequestException {

        reviewService.deleteUserReview(id);
        return ResponseEntity.ok(new SuccessOperationResponse(HttpStatus.OK.toString(), "Отзыв удалён"));
    }
}
