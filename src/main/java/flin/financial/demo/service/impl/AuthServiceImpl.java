package flin.financial.demo.service.impl;

import flin.financial.demo.model.MUser;
import flin.financial.demo.model.dto.request.UserLoginRequest;
import flin.financial.demo.model.dto.request.UserRegisterRequest;
import flin.financial.demo.model.dto.response.LoginResponse;
import flin.financial.demo.model.dto.response.UserRegisterResponse;
import flin.financial.demo.repository.UserRepository;
import flin.financial.demo.service.AuthService;
import flin.financial.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        MUser user = MUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .build();

        userRepository.save(user);

        return UserRegisterResponse.builder()
                .username(user.getUsername())
                .build();
    }

    @Override
    public LoginResponse authenticate(UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        MUser authenticatedUser = userRepository.findByUsername(request.getUsername()).orElse(null);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .username(request.getUsername())
                .jwt(jwtToken)
                .build();
    }
}
