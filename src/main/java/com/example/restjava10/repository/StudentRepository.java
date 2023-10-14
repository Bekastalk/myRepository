package com.example.restjava10.repository;

import com.example.restjava10.dto.StudentRequest;
import com.example.restjava10.dto.StudentResponse;
import com.example.restjava10.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select new com.example.restjava10.dto.StudentResponse(" +
            "s.id,concat(s.firstName,' ',s.lastName) ,s.age,s.email,s.createdDate,s.graduationDate,s.isBlocked) " +
            " from Student s")
    List<StudentResponse>getAllStudents();

    @Query("select new com.example.restjava10.dto.StudentResponse(" +
            "s.id,concat(s.firstName,' ',s.lastName) ,s.age,s.email,s.createdDate,s.graduationDate,s.isBlocked) " +
            " from Student s")
    Page<StudentResponse> getAllStudentsPagin(Pageable pageable);

    @Query("select new com.example.restjava10.dto.StudentResponse(" +
            "s.id,concat(s.firstName,' ',s.lastName) ,s.age,s.email,s.createdDate,s.graduationDate,s.isBlocked) " +
            " from Student s where s.id=:id")
    Optional<StudentResponse>getStudentById(Long id);

    boolean existsByEmail(String email);
}
