package com.example.fixba.dto;

import java.util.ArrayList;
import java.util.List;

public class UserLoginDTO {
    public UserLoginDTO() {

    }

    public UserLoginDTO(String email, String password, List<Long> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    private String email;
    private String password;
    private List<Long> roles = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
