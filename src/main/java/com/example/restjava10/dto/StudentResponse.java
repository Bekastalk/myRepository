package com.example.restjava10.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class StudentResponse {
    private Long id;
    private String fullName;
    private int age;
    private String email;
    private LocalDate createdDate;
    private LocalDate graduationDate;
    private boolean isBlocked;

    public StudentResponse(Long id, String fullName, int age, String email, LocalDate createdDate, LocalDate graduationDate, boolean isBlocked) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.createdDate = createdDate;
        this.graduationDate = graduationDate;
        this.isBlocked = isBlocked;
    }
}
