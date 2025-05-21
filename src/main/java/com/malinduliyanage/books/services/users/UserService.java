package com.malinduliyanage.books.services.users;

import com.malinduliyanage.books.dtos.requests.users.AddUserRequest;
import com.malinduliyanage.books.dtos.requests.users.LoginRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.users.LoginUserResponse;

public interface UserService {

    BaseResponse<String> signupUser(AddUserRequest request);
    BaseResponse<LoginUserResponse> loginUser(LoginRequest request);
}
