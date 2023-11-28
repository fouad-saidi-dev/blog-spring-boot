package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    @Column(nullable = true)
    private String encryptedPassword;
    private String phone;
    @Column(nullable = true)
    private Boolean admin = false;
    @CreatedDate
    @Column(nullable = true)
    private LocalDateTime createdAt;
    @Column(nullable = true)
    @LastModifiedBy
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

}
