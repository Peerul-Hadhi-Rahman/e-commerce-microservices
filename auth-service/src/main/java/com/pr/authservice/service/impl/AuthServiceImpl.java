package com.pr.authservice.service.impl;


import com.pr.authservice.dto.AuthResponse;
import com.pr.authservice.dto.LoginRequest;
import com.pr.authservice.dto.RegisterRequest;
import com.pr.authservice.entity.User;
import com.pr.authservice.enums.Role;
import com.pr.authservice.repository.UserRepository;
import com.pr.authservice.security.JwtService;
import com.pr.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) throw new RuntimeException("Email already exists");
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Email or password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
