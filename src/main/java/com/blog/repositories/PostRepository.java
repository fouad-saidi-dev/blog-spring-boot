package com.blog.repositories;

import com.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByPostId(String postId);

    @Query(value = "select p from posts p where p.user.userId=:userId")
    List<Post> findByUserId(String userId);
    @Query(value = "select p from posts p where p.Id= :Id")
    Post findByIdPost(Long Id);
    @Query(value = "SELECT p FROM posts p JOIN p.tags t WHERE t.name = :tagName")
    List<Post> findAllByTagName(String tagName);

}
