package com.blog.dto;

import com.blog.entities.Post;
import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long Id;
    private String commentId;
    private String comment;
    private UserDto user;
    private PostDto post;
}
