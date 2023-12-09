package com.blog.repositories;

import com.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByPostId(String postId);

}
