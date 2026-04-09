package flin.financial.demo.controller;

import flin.financial.demo.model.dto.request.UserLoginRequest;
import flin.financial.demo.model.dto.request.UserRegisterRequest;
import flin.financial.demo.model.dto.response.LoginResponse;
import flin.financial.demo.model.dto.response.UserRegisterResponse;
import flin.financial.demo.service.AuthService;
import flin.financial.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest registerUserDto) {
        UserRegisterResponse registeredUser = authenticationService.register(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginRequest loginUserDto) {
        LoginResponse authenticatedUser = authenticationService.authenticate(loginUserDto);

        return ResponseEntity.ok(authenticatedUser);
    }
}