package com.example.fixba.service;

import com.example.fixba.dto.UserDTO;
import com.example.fixba.dto.UserLoginDTO;
import com.example.fixba.model.Role;
import com.example.fixba.model.User;
import com.example.fixba.repository.UserRepository;
import com.example.fixba.util.DTOUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthServiceImpl  implements  AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public UserDTO register(UserLoginDTO userData) {
        List<Role> roles = new ArrayList<>();
        for(Long id:userData.getRoles()) {
            Role role = roleService.getRole(id);
            roles.add(role);
        }
        User user = new User(userData.getEmail(), passwordEncoder.encode(userData.getPassword()), roles);
        userRepository.save(user);
        return DTOUtility.getUserDTOFromUser(user);
    }

    public Boolean login(UserLoginDTO userData) {
        User user = userRepository.findByEmail(userData.getEmail());
//        System.out.println("user: " + user.getPassword());
//        return true;
        if(user == null) {
            return false;
        }
        return passwordEncoder.matches(userData.getPassword(), user.getPassword());
    }
}
