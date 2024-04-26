package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.model.User;
import org.soneech.truereview.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    
    public int countUserReviews(User user) {
        return reviewRepository.countByAuthor(user);
    }
}
