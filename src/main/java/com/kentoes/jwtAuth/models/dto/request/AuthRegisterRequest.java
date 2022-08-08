package com.kentoes.jwtAuth.models.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AuthRegisterRequest {
    @NotEmpty(message = "username is required!")
    @Size(max = 30)
    private String username;
    @NotEmpty(message = "fullname is required!")
    @Size(max = 100)
    private String fullName;
    @NotEmpty(message = "password is required!")
    private String password;
    @NotEmpty(message = "email is required!")
    @Email
    @Size(max = 50)
    private String email;
    private String address;
    private String phone;
    private String satker;
}
