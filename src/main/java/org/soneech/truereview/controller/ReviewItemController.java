package org.soneech.truereview.controller;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.response.review.ReviewItemResponse;
import org.soneech.truereview.dto.response.review.ReviewItemShortResponse;
import org.soneech.truereview.exception.CategoryNotFoundException;
import org.soneech.truereview.exception.ReviewItemNotFoundException;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.service.ReviewItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewItemController {

    private final ReviewItemService reviewItemService;

    private final DefaultModelMapper mapper;

    @GetMapping("/items")
    public ResponseEntity<List<ReviewItemResponse>> getAllReviewItems() {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewItemResponse(reviewItemService.findAll()));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ReviewItemResponse> getReviewItem(@PathVariable("id") long id) throws ReviewItemNotFoundException {
        return ResponseEntity
                .ok(mapper.convertToReviewItemResponse(reviewItemService.findByIdWithMainData(id)));
    }

    @GetMapping("/items/search")
    public ResponseEntity<List<ReviewItemShortResponse>> searchReviewItemsByName(@RequestParam("name") String name) {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewItemShortResponse(
                        reviewItemService.searchReviewsByNameSubstr(name)));
    }

    @GetMapping("/categories/{id}/items")
    public ResponseEntity<List<ReviewItemResponse>> getReviewItemsForCategory(@PathVariable("id") long categoryId)
            throws CategoryNotFoundException {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewItemResponse(reviewItemService.findAllForCategory(categoryId)));
    }
}
