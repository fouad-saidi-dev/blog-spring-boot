package com.blog.enums;


import lombok.Getter;

@Getter
public enum ErrorType {
    REQUIRED_FIELD("This field is required"),
    ALREADY_EXIST("Already exist !"),
    ;

    private String message;

    ErrorType(String errorMessage) {
        this.message = errorMessage;
    }

}
