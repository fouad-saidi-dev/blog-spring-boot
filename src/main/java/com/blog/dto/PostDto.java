package com.blog.dto;

import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostDto {
    private Long Id;
    private String postId;
    private String title;
    private String body;
    private User user;
}
