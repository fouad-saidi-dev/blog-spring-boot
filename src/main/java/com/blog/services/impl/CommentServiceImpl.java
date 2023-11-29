package com.blog.services.impl;

import com.blog.dto.CommentDto;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.UserRepository;
import com.blog.responses.CommentResponse;
import com.blog.services.CommentService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    Util util;

    @Override
    public CommentDto addComment(CommentDto commentDto,String email) {

        User checkUser = userRepository.findByEmail(email);

        if (checkUser == null) throw new UsernameNotFoundException("user not found !"+email);

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        comment.setCommentId(util.generateStringId(15));
        comment.setUser(checkUser);
        Comment newComment = commentRepository.save(comment);
        CommentDto dto = new CommentDto();
        BeanUtils.copyProperties(newComment,dto);

        return dto;
    }
}
