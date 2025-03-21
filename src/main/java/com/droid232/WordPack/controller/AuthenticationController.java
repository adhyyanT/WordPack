package com.droid232.WordPack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.droid232.WordPack.dto.LoginUserDto;
import com.droid232.WordPack.dto.RegisterUserDto;
import com.droid232.WordPack.entity.User;
import com.droid232.WordPack.service.AuthenticationService;
import com.droid232.WordPack.service.JwtService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserDto registerUserDto, HttpServletResponse response) {
        User user = authenticationService.signup(registerUserDto);
        String token = jwtService.generateToken(user);
        response.setHeader("Authorization", String.format("Bearer %s", token));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto, HttpServletResponse response) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(authenticatedUser);
        response.setHeader("Authorization", String.format("Bearer %s", token));
        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        response.setHeader("Authorization", null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
