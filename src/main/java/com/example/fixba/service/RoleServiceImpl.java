package com.example.fixba.service;

import com.example.fixba.generated.model.RoleAddContract;
import com.example.fixba.generated.model.UserRoleContract;
import com.example.fixba.model.Role;
import com.example.fixba.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleServiceImpl() {
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String email) {
        return roleRepository.findByName(email);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public UserRoleContract addRole(RoleAddContract role) {
        Role roleToAdd = new Role();
        roleToAdd.setName(role.getName());
       return roleRepository.save(roleToAdd).toDTO();
    }
}
