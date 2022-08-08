package com.kentoes.jwtAuth.services.user;

import com.kentoes.jwtAuth.helper.PageableBuilder;
import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import com.kentoes.jwtAuth.models.entities.user.User;
import com.kentoes.jwtAuth.models.entities.user.UserInfo;
import com.kentoes.jwtAuth.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Page<UserInfo> findAll(FindAllReq findAllReq) {
        return repository.findAllPage(findAllReq.getSearch(), PageableBuilder.build(findAllReq));
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
