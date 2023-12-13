package com.blog.dto;

import com.blog.entities.Comment;
import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeCommentDto {
    private Long Id;
    private String LikeCommentId;
    private Boolean isLike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto user;
    private CommentDto comment;
}
