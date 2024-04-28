package org.soneech.truereview.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.soneech.truereview.dto.request.RegistrationRequest;
import org.soneech.truereview.dto.response.role.RoleResponse;
import org.soneech.truereview.dto.response.user.*;
import org.soneech.truereview.model.Role;
import org.soneech.truereview.model.User;
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
}
