package com.pr.authservice.service;

import com.pr.authservice.dto.AuthResponse;
import com.pr.authservice.dto.LoginRequest;
import com.pr.authservice.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
