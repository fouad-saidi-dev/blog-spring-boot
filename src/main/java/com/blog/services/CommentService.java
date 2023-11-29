package com.blog.services;

import com.blog.dto.CommentDto;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto,String email);
}
