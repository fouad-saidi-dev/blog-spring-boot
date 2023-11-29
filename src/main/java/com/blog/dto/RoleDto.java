package com.blog.dto;

import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDto {
    private Long Id;
    private String roleId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<User> users;
}
