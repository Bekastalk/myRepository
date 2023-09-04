package com.example.restjava10.dto;

import lombok.Builder;

@Builder
public record SignInRequest(
        String email,
        String password
) {
}
