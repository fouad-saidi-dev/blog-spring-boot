package com.blog.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
