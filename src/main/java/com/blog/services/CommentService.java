package com.blog.services;

import com.blog.dto.CommentDto;
import com.blog.dto.UserDto;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto,String email);
    CommentDto updateComment(CommentDto commentDto ,String CommentId,String email);
    List<CommentDto> showComments(String postId);
}
