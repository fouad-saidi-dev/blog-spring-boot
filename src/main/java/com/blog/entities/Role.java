package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roleId;

    @Column(nullable = false)
    private String name;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate createdAt;

    @LastModifiedBy
    @Column(nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<User> users;
}
