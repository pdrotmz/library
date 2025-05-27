package io.github.pdrotmz.libraryAPI.controller;

import io.github.pdrotmz.libraryAPI.dto.auth.AuthRequest;
import io.github.pdrotmz.libraryAPI.dto.auth.AuthResponse;
import io.github.pdrotmz.libraryAPI.dto.auth.RegisterRequest;
import io.github.pdrotmz.libraryAPI.model.User;
import io.github.pdrotmz.libraryAPI.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Registro e Login de usuário")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Registra um novo usuário no sistema")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Loga o usuário no sistema")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @Operation(summary = "Acessa as infos de user")
    @GetMapping("/me")
    public ResponseEntity<User> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
