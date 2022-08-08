package com.kentoes.jwtAuth.models.entities.user;

import com.kentoes.jwtAuth.models.entities.cabang.CabangJoin;
import com.kentoes.jwtAuth.models.entities.role.RoleJoin;

import java.time.LocalDateTime;
import java.util.Set;

public interface UserInfo<U> {
    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    U getCreatedBy();

    U getModifiedBy();

    String getUsername();

    String getFullName();

    String getEmail();

    String getAddress();

    String getPhone();

    boolean isEnabled();

    CabangJoin getCabang();

    Set<RoleJoin> getRoles();
}
