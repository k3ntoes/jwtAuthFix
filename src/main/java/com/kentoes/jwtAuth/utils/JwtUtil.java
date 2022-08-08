package com.kentoes.jwtAuth.utils;

import com.kentoes.jwtAuth.models.entities.user.UserDetailsImpl;
import com.kentoes.jwtAuth.securities.KeyGenerator;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KeyGenerator keyGenerator;
    @Value("${kentoes.security.jwt.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        String TOKEN_PREFIX = "Bearer ";
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(TOKEN_PREFIX))
            return headerAuth.replace(TOKEN_PREFIX, "");
        return null;
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .signWith(keyGenerator.jwtSigningKey(), SignatureAlgorithm.RS512)
                .setIssuer("CATER API")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .setSubject(userPrincipal.getUsername())
                .setId(UUID.randomUUID().toString())
                .compact();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(keyGenerator.jwtValidationKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(keyGenerator.jwtValidationKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token : {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Invalid JWT token signature: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return getAllClaims(token).getSubject();
    }

}
