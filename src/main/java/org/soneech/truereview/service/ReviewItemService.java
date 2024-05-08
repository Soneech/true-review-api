package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.exception.CategoryNotFoundException;
import org.soneech.truereview.exception.ReviewItemNotFoundException;
import org.soneech.truereview.model.ReviewItem;
import org.soneech.truereview.repository.ReviewItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewItemService {

    private final ReviewItemRepository reviewItemRepository;

    private final CategoryService categoryService;

    public List<ReviewItem> findAll() {
        List<ReviewItem> reviewItems = reviewItemRepository.findAll();
        return reviewItems.stream().map(this::fillWithData).toList();
    }

    public List<ReviewItem> findAllForCategory(long categoryId) {
        if (!categoryService.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }

        List<ReviewItem> reviewItems = reviewItemRepository.findAllForCategory(categoryId);
        return reviewItems.stream().map(this::fillWithData).toList();
    }

    public ReviewItem findById(long id) {
        return reviewItemRepository.findById(id).orElseThrow(() -> new ReviewItemNotFoundException(id));
    }

    public ReviewItem findByIdWithMainData(long id) throws ReviewItemNotFoundException{
        ReviewItem review = findById(id);
        fillWithData(review);
        return review;
    }

    public ReviewItem fillWithData(ReviewItem reviewItem) {
        reviewItem.setReviewsCount(reviewItemRepository.getReviewsCount(reviewItem.getId()));
        reviewItem.setMiddleRating(reviewItemRepository.getMiddleRating(reviewItem.getId()));
        return reviewItem;
    }

    public boolean existsById(long id) {
        return reviewItemRepository.existsById(id);
    }

    public Optional<ReviewItem> findByName(String name) {
        return reviewItemRepository.findByName(name);
    }

    public ReviewItem saveItem(ReviewItem reviewItem) {
        return reviewItemRepository.save(reviewItem);
    }

    public List<ReviewItem> searchReviewsByNameSubstr(String nameSubstr) {
        return reviewItemRepository.searchReviewItemByNameSubstr(nameSubstr.replaceAll("\\s+", "").toLowerCase());
    }
}
