package com.blog.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Date timestamp;

    public ErrorResponse(Date timestamp, String message) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
