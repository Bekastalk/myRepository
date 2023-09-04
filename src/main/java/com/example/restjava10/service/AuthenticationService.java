package com.example.restjava10.service;

import com.example.restjava10.dto.AuthenticationResponse;
import com.example.restjava10.dto.SignInRequest;
import com.example.restjava10.dto.SignUpRequest;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);
}
