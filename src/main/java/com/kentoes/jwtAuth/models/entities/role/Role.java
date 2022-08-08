package com.kentoes.jwtAuth.models.entities.role;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.jwtAuth.models.entities.BaseEntity;
import com.kentoes.jwtAuth.models.entities.enums.ERole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = "roleName")})
@JsonPropertyOrder({"id", "roleName", "createdAt", "updatedAt", "createdBy", "updatedBy"})
public class Role extends BaseEntity<String> implements Serializable {
    @Enumerated(EnumType.STRING)
    private ERole roleName;
}
