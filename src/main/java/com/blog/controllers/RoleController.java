package com.blog.controllers;


import com.blog.dto.RoleDto;
import com.blog.responses.RoleResponse;
import com.blog.services.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public List<RoleResponse> getRoles() {

        List<RoleResponse> rolesRoleResponse = new ArrayList<>();

        List<RoleDto> roleDtoList = roleService.displayRoles();

        for (RoleDto roleDto:roleDtoList) {
            RoleResponse roleResponse = new RoleResponse();
            BeanUtils.copyProperties(roleDto,roleResponse);
            rolesRoleResponse.add(roleResponse);
        }

        return rolesRoleResponse;
    }

    @GetMapping("/test")
    public String test() {
        return "done";
    }
}
