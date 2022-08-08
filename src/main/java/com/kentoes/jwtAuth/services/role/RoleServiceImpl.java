package com.kentoes.jwtAuth.services.role;

import com.kentoes.jwtAuth.models.entities.enums.ERole;
import com.kentoes.jwtAuth.models.entities.role.Role;
import com.kentoes.jwtAuth.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findByRoleName(String roleName) {
        ERole eRole = stringToERole(roleName);
        Optional<Role> role = repository.findByRoleName(eRole);
        if (!role.isPresent()) return null;
        return role.get();
    }

    @Override
    public void saveAll(List<Role> roles) {
        repository.saveAll(roles);
    }

    @Override
    public Long totData() {
        return repository.count();
    }

    @Override
    public ERole stringToERole(String eRoleString) {
        ERole eRole = null;
        switch (eRoleString) {
            case "ROLE_ADMIN":
                eRole = ERole.ROLE_ADMIN;
                break;
            case "ROLE_CHECKER_TI":
                eRole = ERole.ROLE_CHECKER_TI;
                break;
            case "ROLE_CHECKER_CABANG":
                eRole = ERole.ROLE_CHECKER_CABANG;
                break;
            case "ROLE_CHECKER_KOPERASI":
                eRole = ERole.ROLE_CHECKER_KOPERASI;
                break;
            case "ROLE_CATER":
                eRole = ERole.ROLE_CATER;
                break;
            default:
                eRole = ERole.ROLE_IDLE;
        }
        return eRole;
    }

}
