package com.example.fixba.service;

import com.example.fixba.generated.model.UserContract;
import com.example.fixba.generated.model.UserRegisterContract;
import com.example.fixba.model.User;

import java.util.List;

public interface UserService {
    List<UserContract> getAll();
    User save(User user);
    void addRoleToUser(String email, String roleName);
    User getUserByEmail(String email);
    User createUser(UserRegisterContract user);
    void deleteUser(Integer id);
    UserContract updateUser(Integer id, UserContract user);
    UserContract getUserById(Integer id);
    List<UserContract> searchUser(String searchTerm);
}
