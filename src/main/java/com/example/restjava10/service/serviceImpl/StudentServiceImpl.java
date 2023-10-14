package com.example.restjava10.service.serviceImpl;

import com.example.restjava10.dto.*;
import com.example.restjava10.entity.Student;
import com.example.restjava10.exception.AlreadyExistException;
import com.example.restjava10.exception.NotFoundException;
import com.example.restjava10.repository.StudentRepository;
import com.example.restjava10.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j //Simple logger facade for java
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public SimpleResponse saveStudent(StudentRequestRecord studentRequest) {
        if (studentRepository.existsByEmail(studentRequest.email())) {
            throw new AlreadyExistException(
                    "User with email: " + studentRequest.email() + " already exists!");
        }

        Student student = new Student();
        student.setFirstName(studentRequest.firstName());
        student.setLastName(studentRequest.lastName());
        student.setAge(studentRequest.age());
        student.setEmail(studentRequest.email());
        student.setCreatedDate(LocalDate.now());
        student.setGraduationDate(studentRequest.graduationDate());
        student.setBlocked(false);
        studentRepository.save(student);
        log.info(String.format("Student with id: %s successfully saved", student.getId()));
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
                () -> {
                    String message="Student with id: " + id + " is not found!";
                    log.error(message);
                    return new NotFoundException(message);
                });
    }

    @Override
    public SimpleResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student1 = studentRepository.findById(id).orElseThrow(
                () -> {
                    String message="Student with id: " + id + " is not found!";
                    log.error(message);
                    return new NotFoundException(message);
                });
        student1.setFirstName(studentRequest.getFirstName());
        student1.setLastName(studentRequest.getLastName());
        student1.setAge(studentRequest.getAge());
        student1.setEmail(studentRequest.getEmail());
        student1.setGraduationDate(studentRequest.getGraduationDate());
        studentRepository.save(student1);
        log.info(String.format("Student with id: %s successfully updated", student1.getId()));
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully updated", student1.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            log.error("Student with id: " + id + " is not found!");
            throw new NotFoundException(
                    "Student with id: " + id + " is not found!");
        }
        studentRepository.deleteById(id);
        log.info("Student with id: " + id + " is deleted!");
        return  SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Student with id: " + id + " is deleted!")
                .build();
    }

    @Override
    public PaginationRequest getAllByPagination(int currentPage, int pageSize) {
        Pageable pageable= PageRequest.of(currentPage-1,pageSize);
        Page<StudentResponse> students = studentRepository.getAllStudentsPagin(pageable);

        return PaginationRequest.builder()
                .students(students.getContent())
                .currentPage(students.getNumber()+1)
                .pageSize(students.getTotalPages())
                .build();
    }
}
