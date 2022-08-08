package com.kentoes.jwtAuth.securities;

import com.kentoes.jwtAuth.models.repositories.ErrLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Slf4j
public class MysqlPasswordEncoder implements PasswordEncoder {
    @Autowired
    private ErrLogRepository repository;

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else {
            Optional<String> encodedPassword = repository.mysqlPass(rawPassword.toString());
            if (!encodedPassword.isPresent()) return null;
            return encodedPassword.get();
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            log.info("{}: {}", this.getClass().getSimpleName(), encodedPassword);
            return checkpw(rawPassword.toString(), encodedPassword);
        } else {
            log.warn("Empty encoded password");
            return false;
        }
    }

    private boolean checkpw(String plaintext, String hashed) {
        return equalsNoEarlyReturn(encode(plaintext), hashed);
    }

    private boolean equalsNoEarlyReturn(String a, String b) {
        return MessageDigest.isEqual(a.getBytes(StandardCharsets.UTF_8), b.getBytes(StandardCharsets.UTF_8));
    }
}
