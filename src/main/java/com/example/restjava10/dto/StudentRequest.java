package com.example.restjava10.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private LocalDate graduationDate;

}
