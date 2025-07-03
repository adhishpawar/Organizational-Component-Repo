package com.company.componentrepo.controller;

import com.company.componentrepo.dto.ApiResponse;
import com.company.componentrepo.dto.AuthRequest;
import com.company.componentrepo.dto.LoginResponse;
import com.company.componentrepo.dto.RegisterRequest;
import com.company.componentrepo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        String msg = userService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(true, msg, null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request){
        String token = userService.login(request.email(), request.password());
        LoginResponse loginResponse = new LoginResponse(token);
        return ResponseEntity.ok(new ApiResponse<>(true, "Login Successful", loginResponse));
    }
}
