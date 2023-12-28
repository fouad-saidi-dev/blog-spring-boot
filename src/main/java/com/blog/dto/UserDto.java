package com.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {
    private long Id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //private String encryptedPassword;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean admin = false;
    private List<PostDto> posts;
    private List<CommentDto> comments;
    private List<RoleDto> roles;
}
