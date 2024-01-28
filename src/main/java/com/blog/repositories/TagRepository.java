package com.blog.repositories;

import com.blog.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query(value = "SELECT t,t.name FROM tags t JOIN t.posts p WHERE p.postId = :postId")
    List<Tag> findAllByPostId(String postId);

    Tag findFirstByName(String tagName);
}
