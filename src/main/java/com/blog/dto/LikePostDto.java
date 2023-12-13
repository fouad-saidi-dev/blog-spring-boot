package com.blog.dto;

import com.blog.entities.Post;
import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikePostDto {
    private Long Id;
    private String likeId;
    private Boolean isLike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto user;
    private PostDto post;
}
