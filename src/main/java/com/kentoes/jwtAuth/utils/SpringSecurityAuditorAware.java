package com.kentoes.jwtAuth.utils;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("System").filter(s -> !s.isEmpty());
    }
}
