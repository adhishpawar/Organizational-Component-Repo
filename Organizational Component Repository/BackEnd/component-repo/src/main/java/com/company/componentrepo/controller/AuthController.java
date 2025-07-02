package com.company.componentrepo.controller;

import com.company.componentrepo.dto.ApiResponse;
import com.company.componentrepo.dto.AuthRequest;
import com.company.componentrepo.dto.RegisterRequest;
import com.company.componentrepo.service.UserService;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try{
            String msg = userService.register(request);
            return ResponseEntity.ok(new ApiResponse<>(true,msg,null));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            String token = userService.login(request.email(), request.password());
            return ResponseEntity.ok(new ApiResponse<>(true,"Login Successful",token));
        }catch (RuntimeException e)
        {
            return ResponseEntity.status(401).body(new ApiResponse<>(false,e.getMessage(), null));
        }
    }
}
