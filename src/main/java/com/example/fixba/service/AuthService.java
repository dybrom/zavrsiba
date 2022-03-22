package com.example.fixba.service;

import com.example.fixba.dto.UserDTO;
import com.example.fixba.dto.UserLoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDTO register(UserLoginDTO userData);
    Boolean login(UserLoginDTO userData);
}
