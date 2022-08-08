package com.kentoes.jwtAuth.services.user;

import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import com.kentoes.jwtAuth.models.entities.user.User;
import com.kentoes.jwtAuth.models.entities.user.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<UserInfo> findAll(FindAllReq findAllReq);

    User findById(Long id);

    User findByUsername(String username);

    User save(User user);

    void saveAll(List<User> users);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
