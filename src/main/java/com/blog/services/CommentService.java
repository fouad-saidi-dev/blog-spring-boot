package com.blog.services;

import com.blog.dto.CommentDto;
import com.blog.dto.UserDto;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto,String email,String postId);
    CommentDto updateComment(CommentDto commentDto ,String CommentId,String email);
    List<CommentDto> showComments(String postId);

    CommentDto deleteComment(String commentId,String email);

}
