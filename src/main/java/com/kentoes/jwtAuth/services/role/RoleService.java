package com.kentoes.jwtAuth.services.role;

import com.kentoes.jwtAuth.models.entities.enums.ERole;
import com.kentoes.jwtAuth.models.entities.role.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findByRoleName(String roleName);

    void saveAll(List<Role> roles);
    Long totData();

    ERole stringToERole(String eRoleString);
}
