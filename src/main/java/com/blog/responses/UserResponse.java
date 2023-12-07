package com.blog.responses;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String userId;
    private String email;
    private String phone;
    private List<PostResponse> posts;
    private List<Comment> comments;
}
