package com.droid232.WordPack.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.droid232.WordPack.dto.LoginUserDto;
import com.droid232.WordPack.dto.RegisterUserDto;
import com.droid232.WordPack.entity.User;
import com.droid232.WordPack.error.ResourceAlreadyExistsException;
import com.droid232.WordPack.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input) {
        userRepository.findByEmail(input.getEmail())
                .ifPresent((user) -> {
                    throw new ResourceAlreadyExistsException(
                            String.format("Email %s is already in use", input.getEmail()));
                });

        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        return (User) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()))
                .getPrincipal();

    }
}
