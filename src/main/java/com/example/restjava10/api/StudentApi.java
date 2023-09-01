package com.example.restjava10.api;

import com.example.restjava10.dto.SimpleResponse;
import com.example.restjava10.dto.StudentRequest;
import com.example.restjava10.dto.StudentRequestRecord;
import com.example.restjava10.dto.StudentResponse;
import com.example.restjava10.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public SimpleResponse saveStudent(@RequestBody StudentRequestRecord studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public SimpleResponse updateStudent(@PathVariable Long studentId,
                                         @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentId,studentRequest);
    }

    @DeleteMapping("/{studentId}")
    public SimpleResponse deleteStudent(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

}
