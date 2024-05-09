package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.exception.*;
import org.soneech.truereview.model.Category;
import org.soneech.truereview.model.Review;
import org.soneech.truereview.model.ReviewItem;
import org.soneech.truereview.model.User;
import org.soneech.truereview.repository.ReviewRepository;
import org.soneech.truereview.security.UserCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;

    private final ReviewItemService reviewItemService;

    private final UserService userService;

    private final CategoryService categoryService;

    private final RoleService roleService;

    @Value("${app.roles.admin}")
    private String adminRole;
    
    public int countUserReviews(User user) {
        return reviewRepository.countByAuthor(user);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public List<Review> findUserReviews(long userId) throws UserNotFoundException {
        userService.verifyThatUserExists(userId);
        return reviewRepository.findUserReviews(userId);
    }

    public Review findById(long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public List<Review> findReviewsByItem(long itemId) {
        if (!reviewItemService.existsById(itemId)) {
            throw new ReviewItemNotFoundException(itemId);
        }
        return reviewRepository.findReviewsByItem(itemId);
    }

    @Transactional
    public Review createReview(Review review, long itemId) throws NotFoundException {
        User user = userService.getAuthenticatedUserIfExists();
        ReviewItem reviewItem = reviewItemService.findById(itemId);

        review.setAuthor(user);
        review.setReviewItem(reviewItem);
        return reviewRepository.save(review);
    }

    @Transactional
    public Review createReviewAndNewItem(Review review, long categoryId, String itemName) throws NotFoundException {
        User user = userService.getAuthenticatedUserIfExists();

        Category foundCategory = categoryService.findById(categoryId);
        Optional<ReviewItem> foundReviewItem = reviewItemService.findByName(itemName.trim());

        if (foundReviewItem.isEmpty()) {
            ReviewItem reviewItem = new ReviewItem(itemName.trim(), foundCategory);
            ReviewItem savedItem = reviewItemService.saveItem(reviewItem);
            review.setReviewItem(savedItem);
        } else {
            review.setReviewItem(foundReviewItem.get());
        }

        review.setAuthor(user);

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteUserReview(long reviewId) throws ReviewNotFoundException {
        Review foundReview = findById(reviewId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) authentication.getPrincipal();

        if ((userCredentials.getUser().getId().equals(foundReview.getAuthor().getId())) ||
                (userCredentials.getAuthorities().contains(roleService.findRoleByName(adminRole)))) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new BadRequestException("Обычный пользователь не может удалять чужие отзывы");
        }

        if (!reviewItemService.checkThanItemHasReviews(foundReview.getReviewItem().getId())) {
            reviewItemService.deleteReviewItem(foundReview.getReviewItem().getId());
        }
    }
}
