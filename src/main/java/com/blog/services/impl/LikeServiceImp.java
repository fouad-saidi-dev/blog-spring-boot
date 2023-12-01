package com.blog.services.impl;

import com.blog.dto.LikePostDto;
import com.blog.entities.LikePost;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.LikePostRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.LikePostService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImp implements LikePostService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LikePostRepository likeRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    Util util;
    @Override
    public LikePostDto addLike(LikePostDto likeDto, String email, String postId) {

        User checkUser = userRepository.findByEmail(email);

        if (checkUser == null) throw new UsernameNotFoundException(email);

        Post checkPost = postRepository.findByPostId(postId);

        LikePost like = new LikePost();
        BeanUtils.copyProperties(likeDto,like);
        like.setCreatedAt(LocalDateTime.now());
        like.setUpdatedAt(null);
        like.setUser(checkUser);
        like.setLikeId(util.generateStringId(15));
        like.setPost(checkPost);

        LikePost addLike = likeRepository.save(like);

        LikePostDto dto = new LikePostDto();
        BeanUtils.copyProperties(addLike,dto);

        return dto;
    }

    @Override
    public List<LikePostDto> getLikes() {

        List<LikePostDto> likePostDtos = new ArrayList<>();

        List<LikePost> likePostList =likeRepository.findAll();

        for (LikePost likePost : likePostList) {
            LikePostDto dto = new LikePostDto();
            BeanUtils.copyProperties(likePost,dto);
            likePostDtos.add(dto);
        }

        return likePostDtos;
    }

    @Override
    public Long countLikes(Long id) {

        Long count = likeRepository.countAllPostByIsLike(id);

        return count;
    }

    @Override
    public Long countDislikes(Long id) {
        return likeRepository.countAllPostByIsNotLike(id);
    }


}
