package com.droid232.WordPack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.droid232.WordPack.dto.LoginResponseDto;
import com.droid232.WordPack.dto.LoginUserDto;
import com.droid232.WordPack.dto.RegisterUserDto;
import com.droid232.WordPack.entity.User;
import com.droid232.WordPack.service.AuthenticationService;
import com.droid232.WordPack.service.JwtService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody RegisterUserDto registerUserDto, HttpServletResponse response) {
        User user = authenticationService.signup(registerUserDto);
        String token = jwtService.generateToken(user);
        response.setHeader("Authorization", String.format("Bearer %s", token));
        return new ResponseEntity<>(new LoginResponseDto(user), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto, HttpServletResponse response) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(authenticatedUser);
        response.setHeader("Authorization", String.format("Bearer %s", token));
        return new ResponseEntity<>(new LoginResponseDto(authenticatedUser), HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        response.setHeader("Authorization", null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
