package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.exception.BadRequestException;
import org.soneech.truereview.exception.CategoryNotFoundException;
import org.soneech.truereview.exception.ReviewNotFoundException;
import org.soneech.truereview.exception.UserNotFoundException;
import org.soneech.truereview.model.Review;
import org.soneech.truereview.model.User;
import org.soneech.truereview.repository.ReviewRepository;
import org.soneech.truereview.security.UserCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;

    private final UserService userService;

    private final CategoryService categoryService;

    private final RoleService roleService;

    @Value("${app.roles.admin}")
    private String adminRole;
    
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

    public List<Review> getReviewsForCategory(long categoryId) {
        if (!categoryService.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        return reviewRepository.findReviewsForCategory(categoryId);
    }

    public void deleteUserReview(long reviewId) throws ReviewNotFoundException {
        Review foundReview = getReviewById(reviewId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) authentication.getPrincipal();

        if ((userCredentials.getUser().getId().equals(foundReview.getAuthor().getId())) ||
                (userCredentials.getAuthorities().contains(roleService.findRoleByName(adminRole)))) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new BadRequestException("Обычный пользователь не может удалять чужие отзывы");
        }
    }
}
