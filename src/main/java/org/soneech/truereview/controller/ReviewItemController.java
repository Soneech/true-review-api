package org.soneech.truereview.controller;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.response.review.ReviewItemResponse;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.service.ReviewItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ReviewItemController {

    private final ReviewItemService reviewItemService;

    private final DefaultModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReviewItemResponse>> getAllReviewItems() {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewItemResponse(reviewItemService.findAll()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReviewItemResponse>> searchReviewItemsByName(@RequestParam("name") String name) {
        return ResponseEntity
                .ok(mapper.convertToListWithReviewItemResponse(
                        reviewItemService.searchReviewsByNameSubstr(name)));
    }
}
