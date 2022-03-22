package com.example.fixba.util;

import com.example.fixba.dto.UserDTO;
import com.example.fixba.dto.UserTypeDTO;
import com.example.fixba.model.Role;
import com.example.fixba.model.User;

import java.util.List;

public class DTOUtility {

    public static UserDTO getUserDTOFromUser(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRoles());
    }
    public static UserDTO getNewUserDTOFromUser(User user) {
        return new UserDTO(user.getId(), user.getEmail());
    }
}
