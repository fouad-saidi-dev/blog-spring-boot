package com.blog.repositories;

import com.blog.entities.Comment;
import com.blog.entities.LikeComment;
import com.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment,Long> {
    @Query(value = "SELECT COUNT(LikeCommentId) FROM likes_comments WHERE isLike = true and comment.commentId=:commentId")
    Long CountLikeComment(String commentId);

    Boolean existsByUserAndComment(User chechUser, Comment checkComment);
}
