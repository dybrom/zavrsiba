package com.example.fixba.service;

import com.example.fixba.dto.UserLoginDTO;

public interface AuthService {
    Boolean login(UserLoginDTO userData);
}
