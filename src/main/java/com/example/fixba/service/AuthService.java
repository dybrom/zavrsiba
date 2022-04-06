package com.example.fixba.service;

import com.example.fixba.dto.UserLoginDTO;

public interface AuthService {
//    UserDTO register(UserLoginDTO userData);
    Boolean login(UserLoginDTO userData);
}
