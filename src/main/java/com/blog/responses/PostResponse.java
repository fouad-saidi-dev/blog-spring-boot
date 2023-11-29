package com.blog.responses;


import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private String title;
    private String body;
    private List<CommentResponse> comments;
}
