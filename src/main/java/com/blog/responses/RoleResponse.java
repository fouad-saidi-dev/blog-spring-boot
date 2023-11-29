package com.blog.responses;

import com.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleResponse {
    private String name;
    private List<UserResponse> users;
}
