package org.soneech.truereview.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.soneech.truereview.dto.request.CreateReviewRequest;
import org.soneech.truereview.dto.request.CreateReviewRequestWithNewItem;
import org.soneech.truereview.dto.request.RegistrationRequest;
import org.soneech.truereview.dto.request.UpdateCategoryRequest;
import org.soneech.truereview.dto.response.review.*;
import org.soneech.truereview.dto.response.role.RoleResponse;
import org.soneech.truereview.dto.response.user.*;
import org.soneech.truereview.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultModelMapper {

    private final ModelMapper modelMapper;

    public User convertToUser(RegistrationRequest request) {
        return modelMapper.map(request, User.class);
    }

    public RegisteredUserResponse convertToRegisteredUserResponse(User user) {
        return modelMapper.map(user, RegisteredUserResponse.class);
    }

    public List<UserShortInfoResponse> convertToListWithUserShortInfoResponses(List<User> users) {
        return users.stream().map(this::convertToUserShortInfoResponse).toList();
    }

    public UserFullInfoResponse convertToUserFullInfoResponse(User user, int reviewsCount) {
        UserFullInfoResponse userResponse = modelMapper.map(user, UserFullInfoResponse.class);

        userResponse.setReviewsCount(reviewsCount);
        userResponse.setRoles(convertToListWithRoleResponse(user.getRoles()));

        return userResponse;
    }

    public List<RoleResponse> convertToListWithRoleResponse(List<Role> roles) {
        return roles.stream().map(this::convertToRoleResponse).toList();
    }

    public RoleResponse convertToRoleResponse(Role role) {
        return modelMapper.map(role, RoleResponse.class);
    }

    public UserPublicInfoResponse convertToUserPublicInfoResponse(User user, int reviewsCount) {
        UserPublicInfoResponse userResponse = modelMapper.map(user, UserPublicInfoResponse.class);

        userResponse.setReviewsCount(reviewsCount);
        userResponse.setRoles(convertToListWithRoleResponse(user.getRoles()));

        return userResponse;
    }

    public UserShortInfoResponse convertToUserShortInfoResponse(User user) {
        UserShortInfoResponse userResponse = modelMapper.map(user, UserShortInfoResponse.class);
        userResponse.setRoles(convertToListWithRoleResponse(user.getRoles()));

        return userResponse;
    }

    public AuthenticatedUserResponse convertToAuthenticatedUserResponse(User user, String token) {
        UserShortInfoResponse shortInfoResponse = convertToUserShortInfoResponse(user);
        return new AuthenticatedUserResponse(shortInfoResponse, token);
    }

    public List<ReviewShortResponse> convertToListWithReviewShortResponse(List<Review> reviews) {
        return reviews.stream().map(this::convertToReviewShortResponse).toList();
    }

    public ReviewShortResponse convertToReviewShortResponse(Review review) {
        ReviewShortResponse reviewResponse = modelMapper.map(review, ReviewShortResponse.class);
        reviewResponse.setAuthor(convertToUserShortInfoResponse(review.getAuthor()));
        reviewResponse.setReviewItem(convertToReviewItemResponse(review.getReviewItem()));

        String description;
        if (review.getAdvantages() != null && !review.getAdvantages().isBlank()) {
            description = review.getAdvantages();
        } else if (review.getDisadvantages() != null && !review.getDisadvantages().isBlank()) {
            description = review.getDisadvantages();
        } else {
            description = review.getNote();
        }

        reviewResponse.setDescription(description);
        return reviewResponse;
    }

    public ReviewItemResponse convertToReviewItemResponse(ReviewItem reviewItem) {
        ReviewItemResponse reviewItemResponse = modelMapper.map(reviewItem, ReviewItemResponse.class);
        reviewItemResponse.setCategory(convertToCategoryResponse(reviewItem.getCategory()));

        return reviewItemResponse;
    }

    public ReviewItemShortResponse convertToReviewItemShortResponse(ReviewItem reviewItem) {
        ReviewItemShortResponse reviewItemResponse = modelMapper.map(reviewItem, ReviewItemShortResponse.class);
        reviewItemResponse.setCategory(convertToCategoryResponse(reviewItem.getCategory()));

        return reviewItemResponse;
    }

    public List<ReviewItemResponse> convertToListWithReviewItemResponse(List<ReviewItem> reviewItems) {
        return reviewItems.stream().map(this::convertToReviewItemResponse).toList();
    }

    public List<ReviewItemShortResponse> convertToListWithReviewItemShortResponse(List<ReviewItem> reviewItems) {
        return reviewItems.stream().map(this::convertToReviewItemShortResponse).toList();
    }

    public CategoryResponse convertToCategoryResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }

    public List<CategoryResponse> convertToListWithCategoryResponse(List<Category> categories) {
        return categories.stream().map(category -> modelMapper.map(category, CategoryResponse.class)).toList();
    }

    public ReviewFullInfoResponse convertToReviewFullInfoResponse(Review review) {
        ReviewFullInfoResponse reviewResponse = modelMapper.map(review, ReviewFullInfoResponse.class);

        reviewResponse.setAuthor(convertToUserShortInfoResponse(review.getAuthor()));
        reviewResponse.setReviewItem(convertToReviewItemResponse(review.getReviewItem()));

        return reviewResponse;
    }

    public Review convertToReview(CreateReviewRequest request) {
        return modelMapper.map(request, Review.class);
    }

    public Review convertToReview(CreateReviewRequestWithNewItem request) {
        return modelMapper.map(request, Review.class);
    }

    public Category convertToCategory(UpdateCategoryRequest request, long id) {
        Category category = modelMapper.map(request, Category.class);
        category.setId(id);
        return category;
    }
}
