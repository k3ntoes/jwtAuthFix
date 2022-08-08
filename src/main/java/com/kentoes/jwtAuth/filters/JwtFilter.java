package com.kentoes.jwtAuth.filters;

import com.kentoes.jwtAuth.services.user.UserDetailServiceImpl;
import com.kentoes.jwtAuth.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken auth = getAuthentication(request);
        if (auth == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String TOKEN_PREFIX = "Bearer ";
        if (token == null || !token.startsWith(TOKEN_PREFIX)) return null;

        String username = jwtUtil.getUserNameFromJwtToken(token.replace(TOKEN_PREFIX, ""));
        if (username == null) return null;

        UserDetails userDetails = service.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
