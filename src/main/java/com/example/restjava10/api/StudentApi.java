package com.example.restjava10.api;

import com.example.restjava10.dto.SimpleResponse;
import com.example.restjava10.dto.StudentRequest;
import com.example.restjava10.dto.StudentRequestRecord;
import com.example.restjava10.dto.StudentResponse;
import com.example.restjava10.service.StudentService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;

   @PermitAll
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveStudent(@RequestBody StudentRequestRecord studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @PermitAll
    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{studentId}")
    public SimpleResponse updateStudent(@PathVariable Long studentId,
                                         @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentId,studentRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{studentId}")
    public SimpleResponse deleteStudent(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

}
