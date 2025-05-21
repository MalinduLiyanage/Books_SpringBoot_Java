package com.malinduliyanage.books.services.users;

import com.malinduliyanage.books.dtos.requests.users.AddUserRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;

public interface UserService {

    BaseResponse<String> signupUser(AddUserRequest request);
}
