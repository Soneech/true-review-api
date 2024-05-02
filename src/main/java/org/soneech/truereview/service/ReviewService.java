package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.exception.ReviewNotFoundException;
import org.soneech.truereview.exception.UserNotFoundException;
import org.soneech.truereview.model.Review;
import org.soneech.truereview.model.User;
import org.soneech.truereview.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;

    private final UserService userService;
    
    public int countUserReviews(User user) {
        return reviewRepository.countByAuthor(user);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getUserReviews(long userId) throws UserNotFoundException {
        userService.verifyThatUserExists(userId);
        return reviewRepository.findUserReviews(userId);
    }

    public Review getReviewById(long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }
}
