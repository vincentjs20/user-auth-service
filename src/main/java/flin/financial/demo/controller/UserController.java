package flin.financial.demo.controller;

import flin.financial.demo.model.MUser;
import flin.financial.demo.model.dto.request.UserProfileRequest;
import flin.financial.demo.model.dto.response.UserProfileResponse;
import flin.financial.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequestMapping("/profile")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MUser> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MUser currentUser = (MUser) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @PostMapping()
    public ResponseEntity<?> updateProfile(@RequestBody UserProfileRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MUser currentUser = (MUser) authentication.getPrincipal();

        UUID userId = currentUser.getId();

        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(
            @PathVariable("userId") UUID userId
    )  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MUser currentUser = (MUser) authentication.getPrincipal();

        if (!currentUser.getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        UserProfileResponse response = userService.getProfileById(userId);

        return ResponseEntity.ok(response);
    }

}
