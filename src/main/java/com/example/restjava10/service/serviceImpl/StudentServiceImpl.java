package com.example.restjava10.service.serviceImpl;

import com.example.restjava10.dto.SimpleResponse;
import com.example.restjava10.dto.StudentRequest;
import com.example.restjava10.dto.StudentRequestRecord;
import com.example.restjava10.dto.StudentResponse;
import com.example.restjava10.entity.Student;
import com.example.restjava10.repository.StudentRepository;
import com.example.restjava10.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public SimpleResponse saveStudent(StudentRequestRecord studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.firstName());
        student.setLastName(studentRequest.lastName());
        student.setAge(studentRequest.age());
        student.setEmail(studentRequest.email());
        student.setCreatedDate(LocalDate.now());
        student.setGraduationDate(studentRequest.graduationDate());
        student.setBlocked(false);
        studentRepository.save(student);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved", student.getId()))
                .build();
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepository.getStudentById(id).orElseThrow(
                () -> new NoSuchElementException(
                        "Student with id: " + id + " is not found!"));
    }

    @Override
    public SimpleResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student1 = studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(
                        "Student with id: " + id + " is not found!"));
        student1.setFirstName(studentRequest.getFirstName());
        student1.setLastName(studentRequest.getLastName());
        student1.setAge(studentRequest.getAge());
        student1.setEmail(studentRequest.getEmail());
        student1.setGraduationDate(studentRequest.getGraduationDate());
        studentRepository.save(student1);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved", student1.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException(
                    "Student with id: " + id + " is not found!");
        }
        studentRepository.deleteById(id);
        return  SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Student with id: " + id + " is deleted!")
                .build();
    }
}
