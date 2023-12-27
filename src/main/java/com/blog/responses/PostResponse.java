package com.blog.responses;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String title;
    private String body;
    private String postId;
    private String description;
    private LocalDateTime createdAt;
    //private UserResponse user;
    @Getter
    private List<CommentResponse> comments;

}
