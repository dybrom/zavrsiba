package com.example.fixba.service;

import com.example.fixba.generated.model.RoleAddContract;
import com.example.fixba.generated.model.UserRoleContract;
import com.example.fixba.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);
    Role findByName(String name);
    List<Role> getRoles();
    Role getRole(Long id);
    UserRoleContract addRole(RoleAddContract role);
}
