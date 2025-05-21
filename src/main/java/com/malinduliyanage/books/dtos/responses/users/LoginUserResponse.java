package com.malinduliyanage.books.dtos.responses.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserResponse {

    private String email;
    private String jwt;

    public LoginUserResponse(String email, String jwt) {
        this.email = email;
        this.jwt = jwt;
    }
}
