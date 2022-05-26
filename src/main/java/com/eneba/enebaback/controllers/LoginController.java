package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.CustomUser;
import com.eneba.enebaback.dto.UserRegisterDTO;
import com.eneba.enebaback.dto.jwt.JwtRequest;
import com.eneba.enebaback.dto.jwt.JwtResponse;
import com.eneba.enebaback.services.UserServiceImpl;
import com.eneba.enebaback.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/auth")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        final CustomUser userDetails = userService.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    @PostMapping("/register")
    public Long register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.RegisterUser(userRegisterDTO).getId();
    }

}
