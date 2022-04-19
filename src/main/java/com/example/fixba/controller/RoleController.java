package com.example.fixba.controller;

import com.example.fixba.exception.NotFoundException;
import com.example.fixba.generated.model.RoleAddContract;
import com.example.fixba.generated.model.UserRoleContract;
import com.example.fixba.model.Role;
import com.example.fixba.service.RoleService;
import org.openapitools.api.RolesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController implements RolesApi {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<List<UserRoleContract>> getRoles() {
            throw new NotFoundException("some text");
//        return ResponseEntity.ok(roleService.getRoles().stream().map(Role::toDTO).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<UserRoleContract> addRole(@Valid RoleAddContract roleAddContract) {
        return ResponseEntity.ok().body(roleService.addRole(roleAddContract));
    }
}
