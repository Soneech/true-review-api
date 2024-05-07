package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
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

    public List<ReviewItem> findAll() {
        return reviewItemRepository.findAll();
    }

    public ReviewItem findById(long id) {
        return reviewItemRepository.findById(id).orElseThrow(() -> new ReviewItemNotFoundException(id));
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
