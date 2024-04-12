package com.example.sem5_springdata_ex2.repository;

import com.example.sem5_springdata_ex2.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
