package com.blog.services;

import com.blog.dto.LikeCommentDto;

public interface LikeCommentService {
    LikeCommentDto addLikeToComment(LikeCommentDto commentDto,String email, String commentId);
}
