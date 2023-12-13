package com.blog.repositories;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.responses.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "SELECT c,c.user.userId FROM comments c WHERE c.post.postId = :postId")
    List<Comment> findAllByPostId(String postId);

    Comment findByCommentId(String commentId);

}
