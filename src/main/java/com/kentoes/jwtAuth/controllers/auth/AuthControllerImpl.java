package com.kentoes.jwtAuth.controllers.auth;

import com.kentoes.jwtAuth.controllers.BaseController;
import com.kentoes.jwtAuth.models.dto.request.AuthRegisterRequest;
import com.kentoes.jwtAuth.models.dto.request.AuthRequest;
import com.kentoes.jwtAuth.models.entities.errLog.ErrLog;
import com.kentoes.jwtAuth.models.entities.user.User;
import com.kentoes.jwtAuth.services.cabang.CabangService;
import com.kentoes.jwtAuth.services.errLog.ErrLogService;
import com.kentoes.jwtAuth.services.role.RoleService;
import com.kentoes.jwtAuth.services.user.UserDetailServiceImpl;
import com.kentoes.jwtAuth.services.user.UserService;
import com.kentoes.jwtAuth.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthControllerImpl extends BaseController implements AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ErrLogService errLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CabangService cabangService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String errString = null;

    @Override
    public ResponseEntity<?> login(HttpServletRequest request, AuthRequest authRequest, Errors errors) {
        if (errors.hasErrors())
            return errorResult.build(errors);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return singleResult.build(jwtUtil.generateJwtToken(authentication));
    }

    @Override
    public ResponseEntity<?> register(AuthRegisterRequest authRegisterRequest, Errors errors) {
        if (errors.hasErrors())
            return errorResult.build(errors);

        if (userService.existsByUsername(authRegisterRequest.getUsername())) {
            errString = "username is already used!";
            errLogService.save(
                    new ErrLog("", "Controller", errString, this.getClass().getName(), "register"));
            return errorResult.build(errString);
        }

        if (userService.existsByEmail(authRegisterRequest.getEmail())) {
            errString = "email is already used!";
            errLogService.save(
                    new ErrLog("", "Controller", errString, this.getClass().getName(), "register"));
            return errorResult.build(errString);
        }

        User userReq = modelMapper.map(authRegisterRequest, User.class);
        userReq.setCabang(cabangService.findBySatker(authRegisterRequest.getSatker()));
        userReq.addRole(roleService.findByRoleName("ROLE_IDLE"));
        userReq.setPassword(passwordEncoder.encode(authRegisterRequest.getPassword()));
        User userRes = userService.save(userReq);

        return saveResult.build(userRes.getId());
    }
}
