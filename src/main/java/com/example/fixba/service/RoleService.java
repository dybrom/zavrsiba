package com.example.fixba.service;

import com.example.fixba.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role save(Role role);
    Role findByName(String name);
    List<Role> getRoles();
    Role getRole(Long id);
}
