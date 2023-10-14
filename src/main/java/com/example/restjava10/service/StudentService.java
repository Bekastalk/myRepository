package com.example.restjava10.service;

import com.example.restjava10.dto.*;
import com.example.restjava10.entity.Student;

import java.util.List;

public interface StudentService {

    SimpleResponse saveStudent(StudentRequestRecord studentRequest);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    SimpleResponse updateStudent(Long id, StudentRequest studentRequest);

    SimpleResponse deleteStudent(Long id);


    PaginationRequest getAllByPagination(int currentPage, int pageSize);
}
