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
import org.springframework.stereotype.Service;

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

        BeanUtils.copyProperties(postDto,post);
        post.setPostId(util.generateStringId(15));

        Post newPost = postRepository.save(post);

        PostDto dto = new PostDto();
        BeanUtils.copyProperties(newPost,dto);

        return dto;
    }
}
