package com.blog.dto;

import com.blog.entities.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {
    private String userId;
    private long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private List<Post> posts;
}
