package com.blog.services.impl;

import com.blog.dto.LikeCommentDto;
import com.blog.entities.Comment;
import com.blog.entities.LikeComment;
import com.blog.entities.User;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.LikeCommentRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.LikeCommentService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class LikeCommentServiceImpl implements LikeCommentService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeCommentRepository likeCommentRepository;
    @Autowired
    Util util;

    @Override
    public LikeCommentDto addLikeToComment(LikeCommentDto commentDto, String email, String commentId) {

        User chechUser = userRepository.findByEmail(email);

        if (chechUser == null) throw new UsernameNotFoundException(email);

        Comment checkComment = commentRepository.findByCommentId(commentId);

        Boolean userAlreadyLiked = likeCommentRepository.existsByUserAndComment(chechUser, checkComment);

        LikeComment likeComment = new LikeComment();
        BeanUtils.copyProperties(commentDto,likeComment);

        if (!userAlreadyLiked) {
            likeComment.setComment(checkComment);
            likeComment.setUser(chechUser);
            likeComment.setCreatedAt(LocalDateTime.now());
            likeComment.setLikeCommentId(util.generateStringId(15));
        }
        LikeComment newLike = likeCommentRepository.save(likeComment);
        LikeCommentDto dto = new LikeCommentDto();
        BeanUtils.copyProperties(newLike,dto);
        return dto;
    }

    @Override
    public Long countLikes(String id) {
        return likeCommentRepository.CountLikeComment(id);
    }

    @Override
    public Long countDislikes(Long id) {
        return null;
    }
}
