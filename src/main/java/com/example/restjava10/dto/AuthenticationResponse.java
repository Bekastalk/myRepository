package com.example.restjava10.dto;

import com.example.restjava10.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String email,
        Role role
) {
}
