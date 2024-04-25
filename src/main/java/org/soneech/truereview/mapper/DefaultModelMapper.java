package org.soneech.truereview.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.soneech.truereview.dto.request.RegistrationRequest;
import org.soneech.truereview.dto.response.UserInfoResponse;
import org.soneech.truereview.model.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultModelMapper {

    private final ModelMapper modelMapper;

    public User convertToUser(RegistrationRequest request) {
        return modelMapper.map(request, User.class);
    }

    public UserInfoResponse convertToUserInfoResponse(User user) {
        return modelMapper.map(user, UserInfoResponse.class);
    }
}
