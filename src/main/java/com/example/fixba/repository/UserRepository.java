package com.example.fixba.repository;

import com.example.fixba.model.Role;
import com.example.fixba.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM users u where u.email =?1")
    User findByEmail(String email);

    @Query(value = "SELECT u.* FROM users u " +
            "JOIN users_roles ur " + "ON u.id = ur.users_id " +
            "JOIN role r ON ur.roles_id = r.id WHERE r.name  LIKE :searchTerm OR u.name LIKE :searchTerm OR u.email LIKE :searchTerm", nativeQuery = true)
    List<User> findUsersByRoleOrName(String searchTerm);

}
