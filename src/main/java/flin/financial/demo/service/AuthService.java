package flin.financial.demo.service;

import flin.financial.demo.model.dto.request.UserLoginRequest;
import flin.financial.demo.model.dto.request.UserRegisterRequest;
import flin.financial.demo.model.dto.response.LoginResponse;
import flin.financial.demo.model.dto.response.UserRegisterResponse;

public interface AuthService {

    UserRegisterResponse register (UserRegisterRequest request);

    LoginResponse authenticate(UserLoginRequest request);

}
