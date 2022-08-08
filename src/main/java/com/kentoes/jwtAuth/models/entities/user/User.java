package com.kentoes.jwtAuth.models.entities.user;

import com.kentoes.jwtAuth.models.entities.BaseEntity;
import com.kentoes.jwtAuth.models.entities.cabang.Cabang;
import com.kentoes.jwtAuth.models.entities.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseEntity<String> implements Serializable {
    @NaturalId
    @Size(max = 30)
    private String username;

    @Size(max = 100)
    private String fullName;
    @Email
    @Size(max = 50)
    @Column(columnDefinition = "varchar(50) NOT NULL")
    private String email;
    private String address;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "satker")
    private Cabang cabang;
    private String password;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    private boolean enabled = false;

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}
