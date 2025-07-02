package com.company.componentrepo.service;

import com.company.componentrepo.config.MyUserDetailsService;
import com.company.componentrepo.dto.RegisterRequest;
import com.company.componentrepo.entity.User;
import com.company.componentrepo.jwt.JwtService;
import com.company.componentrepo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    public String register(RegisterRequest request){
        if(userRepository.findByEmail(request.email()).isPresent())
            throw new RuntimeException("Email Already registered..!!");

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);

        return "User Registered SuccessFully";
    }

    public String login(String email, String password){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        if(auth.isAuthenticated()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return jwtService.generateToken(userDetails);
        }else {
            throw new RuntimeException("Invalid Credentials");
        }

    }
}
