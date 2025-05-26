package io.github.pdrotmz.libraryAPI.dto.auth;

import io.github.pdrotmz.libraryAPI.model.UserRole;

public record RegisterRequest(String name, String email, String password, UserRole role) {
}
