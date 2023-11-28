package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDto {
    private Long Id;
    private String roleId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
