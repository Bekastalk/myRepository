package com.example.restjava10.service.serviceImpl;

import com.example.restjava10.dto.AuthenticationResponse;
import com.example.restjava10.dto.SignInRequest;
import com.example.restjava10.dto.SignUpRequest;
import com.example.restjava10.entity.User;
import com.example.restjava10.repository.UserRepository;
import com.example.restjava10.security.jwt.JwtService;
import com.example.restjava10.service.AuthenticationService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        System.out.println("test1");
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new EntityExistsException(
                    "User with email: " + signUpRequest.email() + " already exists!");
        }
        System.out.println("test2");
        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role())
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email())
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "User with email: " + signInRequest.email() + " doesn't exist!"));

        if(signInRequest.email().isBlank()){
            throw new BadCredentialsException("Email is blank");
        }
        if(!passwordEncoder.matches(signInRequest.password(), user.getPassword())){
            throw new BadCredentialsException("Wrong password");
        }

        String token=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


















}
