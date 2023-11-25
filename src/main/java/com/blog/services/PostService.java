package com.blog.services;

import com.blog.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> allPosts();

    PostDto showPost(String postId);
}
