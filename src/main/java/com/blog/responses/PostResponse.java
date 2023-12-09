package com.blog.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<CommentResponse> comments;
}
