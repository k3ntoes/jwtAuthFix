package com.kentoes.jwtAuth.models.repositories;

import com.kentoes.jwtAuth.models.entities.enums.ERole;
import com.kentoes.jwtAuth.models.entities.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole eRole);
}