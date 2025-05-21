package com.malinduliyanage.books.controllers;

import com.malinduliyanage.books.dtos.requests.users.AddUserRequest;
import com.malinduliyanage.books.dtos.requests.users.LoginRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.users.LoginUserResponse;
import com.malinduliyanage.books.services.users.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public BaseResponse<String> signup(@Valid @RequestBody AddUserRequest request){
        return userService.signupUser(request);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserResponse> login(@Valid @RequestBody LoginRequest request){
        return userService.loginUser(request);
    }
}
