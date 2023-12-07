package com.blog.services;

import com.blog.dto.CommentDto;
import com.blog.dto.UserDto;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto,String email);
    CommentDto updateComment(CommentDto commentDto ,String CommentId,String email);
}
