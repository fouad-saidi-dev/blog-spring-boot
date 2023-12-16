package com.blog.services;

import com.blog.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,String email);
    List<PostDto> allPosts();
    PostDto showPost(String postId);
    PostDto updatePost(PostDto postDto,String postId,String email);
    PostDto deletePost(String id,String email);
    List<PostDto> getPostsByUser(String email,String userId);
}
