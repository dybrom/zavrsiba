package com.example.fixba.controller;

import com.example.fixba.dto.UserDTO;
import com.example.fixba.model.User;
import com.example.fixba.service.UserService;
import com.example.fixba.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    ResponseEntity<Boolean> logIn(@Valid @RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return ResponseEntity.ok(bCryptPasswordEncoder.matches(user.getPassword(), "$2a$10$xtL.q2MfuZCm0N6JjkTnmeM5TMAov10Vrzs15VlKJVWefib0mmFWW"));
    }

}
