package com.example.fixba.model;

import com.example.fixba.generated.model.UserContract;
import com.example.fixba.generated.model.UserRoleContract;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = User.TABLE_NAME)
@Table
public class User extends BaseModel {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_EMAIL = "email";

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(@NotBlank(message = "Email is Mandatory") String email, @NotBlank(message = "Password is Mandatory") String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Email is Mandatory")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Password is Mandatory")
    private String password;

    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserContract toDTO() {
        UserContract user = new UserContract();
        if (this.getCreatedAt() != null) {
            user.setCreatedAt(OffsetDateTime.of(this.getCreatedAt(), ZoneOffset.UTC));
        }
        if (this.getUpdatedAt() != null) {
            user.setUpdatedAt(OffsetDateTime.of(this.getUpdatedAt(), ZoneOffset.UTC));
        }

        user.setEmail(this.getEmail());
        user.setName(this.getName());
        user.setRoles(this.getRoles().stream().map(role -> {
            UserRoleContract roleContract = new UserRoleContract();
            roleContract.setId(role.getId());
            roleContract.setName(role.getName());
            return roleContract;
        }).collect(Collectors.toList()));
        user.setId(this.getId());
        return user;
    }
}
