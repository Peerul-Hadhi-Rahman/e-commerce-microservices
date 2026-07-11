package com.pr.authservice.controller;

import com.pr.authservice.dto.UserResponse;
import com.pr.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public UserResponse me() {
        return userService.getCurrentUser();
    }


}
