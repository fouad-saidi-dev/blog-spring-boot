package com.blog.enums;


import lombok.Getter;

@Getter
public enum ErrorType {
    REQUIRED_FIELD("This field is required !"),
    ALREADY_EXIST("Already exist !"),
    NOT_FOUND("Not exist !")
    ;

    private String message;

    ErrorType(String errorMessage) {
        this.message = errorMessage;
    }

}
