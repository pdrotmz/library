package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.auth.AuthRequest;
import io.github.pdrotmz.libraryAPI.dto.auth.AuthResponse;
import io.github.pdrotmz.libraryAPI.dto.auth.RegisterRequest;
import io.github.pdrotmz.libraryAPI.model.User;
import io.github.pdrotmz.libraryAPI.repository.UserRepository;
import io.github.pdrotmz.libraryAPI.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email Já cadastrado!");
        }

        var user = User.builder()
                .username(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }
}
