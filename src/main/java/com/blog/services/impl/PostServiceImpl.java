package com.blog.services.impl;

import com.blog.dto.PostDto;
import com.blog.dto.UserDto;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Util util;

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        //User currentUser = userRepository.findByEmail(email);

        Post post = new Post();

        BeanUtils.copyProperties(postDto, post);
        post.setPostId(util.generateStringId(15));

        Post newPost = postRepository.save(post);

        PostDto dto = new PostDto();
        BeanUtils.copyProperties(newPost, dto);

        return dto;
    }

    @Override
    public List<PostDto> allPosts() {

        List<PostDto> postDtos = new ArrayList<>();

        List<Post> posts = postRepository.findAll();

        for (Post post : posts
        ) {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(post,postDto);
            postDtos.add(postDto);
        }

        return postDtos;
    }

    @Override
    public PostDto showPost(String postId) {

        Post post = postRepository.findByPostId(postId);

        if (post == null) throw new RuntimeException(postId);

        PostDto dto = new PostDto();
        BeanUtils.copyProperties(post,dto);

        return dto;
    }
}
