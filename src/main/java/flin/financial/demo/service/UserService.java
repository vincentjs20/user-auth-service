package flin.financial.demo.service;

import flin.financial.demo.model.dto.request.UserProfileRequest;
import flin.financial.demo.model.dto.response.UserProfileResponse;

import java.util.UUID;

public interface UserService {

    UserProfileResponse updateProfile (UUID userId, UserProfileRequest request);

    UserProfileResponse getProfileById(UUID userId);

}
