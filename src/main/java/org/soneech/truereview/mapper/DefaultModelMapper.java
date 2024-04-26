package org.soneech.truereview.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.soneech.truereview.dto.request.RegistrationRequest;
import org.soneech.truereview.dto.response.user.*;
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
        return users.stream().map(user -> modelMapper.map(user, UserShortInfoResponse.class)).toList();
    }

    public UserFullInfoResponse convertToUserFullInfoResponse(User user, int reviewsCount) {
        UserFullInfoResponse userResponse = modelMapper.map(user, UserFullInfoResponse.class);
        userResponse.setReviewsCount(reviewsCount);

        return userResponse;
    }

    public UserPublicInfoResponse convertToUserPublicInfoResponse(User user, int reviewsCount) {
        UserPublicInfoResponse userResponse = modelMapper.map(user, UserPublicInfoResponse.class);
        userResponse.setReviewsCount(reviewsCount);

        return userResponse;
    }
}
