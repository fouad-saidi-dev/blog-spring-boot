package com.blog.services;

import com.blog.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto addRole(RoleDto roleDto);
    List<RoleDto> displayRoles();
}
