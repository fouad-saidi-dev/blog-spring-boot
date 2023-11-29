package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String postId;
    private String title;
    private String body;
    @CreatedDate
    @Column(nullable = true)
    private LocalDateTime createdAt;
    @LastModifiedBy
    @Column(nullable = true)
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comment;
}
