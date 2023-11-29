package com.blog.services.impl;

import com.blog.dto.RoleDto;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepository;
import com.blog.services.RoleService;
import com.blog.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Util util;

    @Override
    public RoleDto addRole(RoleDto roleDto) {

        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        role.setRoleId(util.generateStringId(15));

        return null;
    }

    @Override
    public List<RoleDto> displayRoles() {

        List<Role> roles = roleRepository.findAll();

        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role: roles) {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role,roleDto);
            roleDtoList.add(roleDto);
        }

        return roleDtoList;
    }


}
