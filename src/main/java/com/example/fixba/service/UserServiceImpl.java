package com.example.fixba.service;

import com.example.fixba.generated.model.UserContract;
import com.example.fixba.generated.model.UserRegisterContract;
import com.example.fixba.model.Role;
import com.example.fixba.model.User;
import com.example.fixba.repository.RoleRepository;
import com.example.fixba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
    }

    public List<UserContract> getAll() {
        return userRepository.findAll().stream().map(User::toDTO).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        User newUser = new User(user.getEmail(),
                passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserContract getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow().toDTO();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(UserRegisterContract user) {
        List<Role> roles = new ArrayList<>();
        for (long id : user.getRoles()) {
            Role role = roleService.getRole(id);
            roles.add(role);
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserContract updateUser(Integer id, UserContract userContract) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        if (userContract.getEmail() != null) {
            user.setEmail(userContract.getEmail());
        }
        if (userContract.getName() != null) {
            user.setName(userContract.getName());
        }
        if (userContract.getRoles() != null) {
            user.setRoles(userContract.getRoles().stream().map(role -> roleService.getRole(role.getId())).collect(Collectors.toList()));
        }
       return userRepository.save(user).toDTO();
    }

    @Override
    public List<UserContract> searchUser(String searchTerm) {
        return userRepository.findUsersByRoleOrName(searchTerm).stream().map(User::toDTO).collect(Collectors.toList());
    }
}
