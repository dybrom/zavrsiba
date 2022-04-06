package com.example.fixba.model;

import com.example.fixba.generated.model.UserRoleContract;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public UserRoleContract toDTO() {
        UserRoleContract userRoleContract = new UserRoleContract();
        userRoleContract.setName(name);
        userRoleContract.setId(id);
        return userRoleContract;
    }
}
