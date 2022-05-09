package com.example.fixba.service;

import com.example.fixba.dto.UserLoginDTO;
import com.example.fixba.model.User;
import com.example.fixba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthServiceImpl  implements  AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public Boolean login(UserLoginDTO userData) {
        User user = userRepository.findByEmail(userData.getEmail());
        if(user == null) {
            return false;
        }
        return passwordEncoder.matches(userData.getPassword(), user.getPassword());
    }
}
