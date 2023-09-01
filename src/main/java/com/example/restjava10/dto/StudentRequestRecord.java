package com.example.restjava10.dto;

import java.time.LocalDate;

public record StudentRequestRecord(
        String firstName,
        String lastName,
        int age,
        String email,
        LocalDate createdDate,
        LocalDate graduationDate,
        boolean isBlocked)
{
}
