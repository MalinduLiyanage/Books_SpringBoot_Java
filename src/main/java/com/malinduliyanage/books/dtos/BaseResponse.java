package com.malinduliyanage.books.dtos;

import com.malinduliyanage.books.constants.CommonResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

//From lombok pkg, automatically creates Getters and Setters for the class at compile time
@Getter
@Setter
public class BaseResponse<T> {
    private int statusCode;
    private T data;
    private String message;

    public BaseResponse(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public BaseResponse(HttpStatus httpStatus, T data) {
        this.statusCode = httpStatus.value();
        this.data = data;
    }

    public BaseResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public BaseResponse(HttpStatus httpStatus, String message) {
        this.statusCode = httpStatus.value();
        this.message = message;
        this.data = null;
    }


    public BaseResponse(HttpStatus httpStatus, CommonResponse commonResponse) {
        this.statusCode = httpStatus.value();
        this.message = commonResponse.getValue();
        this.data = null;
    }
}
