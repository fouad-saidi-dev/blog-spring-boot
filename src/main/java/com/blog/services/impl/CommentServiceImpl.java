package com.blog.services.impl;

import com.blog.dto.CommentDto;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.responses.CommentResponse;
import com.blog.services.CommentService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;
    @Autowired
    Util util;

    @Override
    public CommentDto addComment(CommentDto commentDto,String email,String postId) {

        User checkUser = userRepository.findByEmail(email);

        if (checkUser == null) throw new UsernameNotFoundException("user not found !"+email);

        Post checkPost = postRepository.findByPostId(postId);


        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        comment.setCommentId(util.generateStringId(15));
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(checkUser);
        comment.setPost(checkPost);
        Comment newComment = commentRepository.save(comment);
        CommentDto dto = new CommentDto();
        BeanUtils.copyProperties(newComment,dto);

        return dto;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, String commentId, String email) {

        User checkUser = userRepository.findByEmail(email);

        Comment comment = commentRepository.findByCommentId(commentId);

        if (checkUser == null) throw new UsernameNotFoundException(email);

        if (checkUser == comment.getUser()) {
            comment.setComment(commentDto.getComment());
            comment.setUpdatedAt(LocalDateTime.now());
        }
        Comment updatedComment = commentRepository.save(comment);
        CommentDto dto = new CommentDto();
        BeanUtils.copyProperties(updatedComment,dto);
        return dto;

    }

    @Override
    public List<CommentDto> showComments(String postId) {

        List<CommentDto> commentDtos = new ArrayList<>();

        List<Comment> comments = commentRepository.findAllByPostId(postId);

        for (Comment comment:comments) {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment,commentDto);
            commentDtos.add(commentDto);
        }

        return commentDtos;
    }


}
