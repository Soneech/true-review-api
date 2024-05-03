package org.soneech.truereview.controller;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.response.user.UserInfoResponse;
import org.soneech.truereview.dto.response.user.UserShortInfoResponse;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.model.User;
import org.soneech.truereview.service.ReviewService;
import org.soneech.truereview.service.RoleService;
import org.soneech.truereview.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final ReviewService reviewService;

    private final DefaultModelMapper mapper;

    @Value("${app.roles.admin}")
    private String adminRole;

    @GetMapping
    public ResponseEntity<List<UserShortInfoResponse>> getAllUsers() {
        return ResponseEntity.ok(mapper.convertToListWithUserShortInfoResponses(userService.findAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable("id") long id) {
        User foundUser = userService.findById(id);
        int userReviewsCount = reviewService.countUserReviews(foundUser);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (foundUser.getEmail().equals(authentication.getName()) ||
                authentication.getAuthorities().contains(roleService.findRoleByName(adminRole))) {
            return ResponseEntity.ok(mapper.convertToUserFullInfoResponse(foundUser, userReviewsCount));
        }
        return ResponseEntity.ok(mapper.convertToUserPublicInfoResponse(foundUser, userReviewsCount));
    }
}
