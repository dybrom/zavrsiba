package com.example.fixba.controller;

import com.example.fixba.exception.NotFoundException;
import com.example.fixba.generated.model.UserContract;
import com.example.fixba.generated.model.UserRegisterContract;
import com.example.fixba.model.User;
import com.example.fixba.service.UserService;
import org.openapitools.api.UsersApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
public class UserController implements UsersApi {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ResponseEntity<List<UserContract>> getUsers(@Valid Integer page, @Valid Integer size, @Valid String searchTerm, @Pattern(regexp = "^(((dueDate|title|createdAt){1}:)((asc|desc){1}))(,{1}(((dueDate|title|createdAt)+:)((asc|desc){1})))*$") @Valid String order) {
		if (searchTerm != null) {
			return ResponseEntity.ok(userService.searchUser(searchTerm));
		}
		return ResponseEntity.ok(userService.getAll());
	}

	@Override
	public ResponseEntity<UserContract> addUser(@Valid @RequestBody UserRegisterContract user) {
		throw new NotFoundException("test");
//		User newUser = userService.createUser(user);
//		return ResponseEntity.ok(newUser.toDTO());
	}

	@Override
	public ResponseEntity<Void> deleteUser(Integer id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<UserContract> updateUser(Integer id, @Valid UserContract userContract) {
		return ResponseEntity.ok(userService.updateUser(id, userContract));
	}

	@Override
	public ResponseEntity<UserContract> getUser(Integer id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
}
