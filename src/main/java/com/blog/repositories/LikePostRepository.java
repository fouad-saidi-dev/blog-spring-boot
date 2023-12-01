package com.blog.repositories;

import com.blog.entities.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost,Long> {
    @Query(value = "SELECT COUNT(likeId) FROM like_posts WHERE isLike = true and post.Id=:postId")
    Long countAllPostByIsLike(Long postId);
    @Query(value = "SELECT COUNT(likeId) FROM like_posts WHERE isLike = false and post.Id=:postId")
    Long countAllPostByIsNotLike(Long postId);
}
