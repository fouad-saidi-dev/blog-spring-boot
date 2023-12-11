package com.blog.responses;

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
    private Long postId;
    private User user;
}
