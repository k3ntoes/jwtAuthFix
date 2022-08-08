package com.kentoes.jwtAuth.controllers.auth;

import com.kentoes.jwtAuth.models.dto.request.AuthRegisterRequest;
import com.kentoes.jwtAuth.models.dto.request.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface AuthController {
    @PostMapping("/login")
    ResponseEntity<?> login(HttpServletRequest request, @Valid @RequestBody AuthRequest authRequest, Errors errors);

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody AuthRegisterRequest authRegisterRequest, Errors errors);
}
