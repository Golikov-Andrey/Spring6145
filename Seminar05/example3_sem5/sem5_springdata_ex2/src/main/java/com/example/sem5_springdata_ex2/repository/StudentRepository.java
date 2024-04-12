package com.example.sem5_springdata_ex2.repository;

import com.example.sem5_springdata_ex2.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
