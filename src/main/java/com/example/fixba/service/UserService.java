package com.example.fixba.service;

import com.example.fixba.dto.UserDTO;
import com.example.fixba.model.Role;
import com.example.fixba.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getAll();
    User save(User user);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
}
