package com.blog.responses;

import com.blog.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String comment;
    private String commentId;
    private UserResponse user;
    private PostResponse post;
}
