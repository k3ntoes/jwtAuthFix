package com.kentoes.jwtAuth.models.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@SpringBootTest
class ErrLogRepositoryTest {
    @Autowired
    private ErrLogRepository repository;

    @Test
    void mysqlPass() {
        Pattern pattern = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
        Matcher matcher = pattern.matcher("$2a$10$EFjZbNU0Iko0serbCgqqwOkXDVKNs1Wph/.54AyJmcxjCIo/o40oW");
        log.info("{}", matcher.matches());
        log.info("{}", matcher.group(2));
        log.info("native: {}", repository.mysqlPass("password"));
    }

    @Test
    void token() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                "cater", "password"
        );
        log.info("result: {}", token);
    }
}