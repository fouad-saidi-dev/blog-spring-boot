package com.blog.services;

import com.blog.dto.LikePostDto;

import java.util.List;

public interface LikePostService {
    LikePostDto addLike(LikePostDto likeDto, String email, String postId);
    List<LikePostDto> getLikes();
    Long countLikes(String id);
    Long countDislikes(Long id);
}
