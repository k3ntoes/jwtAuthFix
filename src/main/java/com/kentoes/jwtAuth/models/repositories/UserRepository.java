package com.kentoes.jwtAuth.models.repositories;

import com.kentoes.jwtAuth.models.entities.user.User;
import com.kentoes.jwtAuth.models.entities.user.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"cabang", "roles"})
    @Query("SELECT u FROM User u " +
            "WHERE u.username=?1 " +
            "OR u.email=?1 " +
            "OR u.fullName LIKE CONCAT('%',?1,'%') " +
            "OR u.address LIKE CONCAT('%',?1,'%')")
    Page<UserInfo> findAllPage(String search, Pageable build);

//    @EntityGraph(attributePaths = {"cabang", "roles"})
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}