package com.example.fixba.controller;

import com.example.fixba.dto.UserDTO;
import com.example.fixba.dto.UserLoginDTO;
import com.example.fixba.service.AuthService;
import com.example.fixba.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authServiceImpl) {
        this.authService = authServiceImpl;
    }

    @PostMapping("/register")
    ResponseEntity<UserDTO> register(@Valid @RequestBody UserLoginDTO user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    ResponseEntity<Boolean> login(@Valid @RequestBody UserLoginDTO user) {
        return ResponseEntity.ok(authService.login(user));
    }

}
