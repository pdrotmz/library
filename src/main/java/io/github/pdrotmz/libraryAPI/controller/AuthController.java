package io.github.pdrotmz.libraryAPI.controller;

import io.github.pdrotmz.libraryAPI.dto.auth.AuthRequest;
import io.github.pdrotmz.libraryAPI.dto.auth.AuthResponse;
import io.github.pdrotmz.libraryAPI.dto.auth.RegisterRequest;
import io.github.pdrotmz.libraryAPI.model.User;
import io.github.pdrotmz.libraryAPI.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
