package com.example.sem5_springdata_ex2.controller;

import com.example.sem5_springdata_ex2.domain.Course;
import com.example.sem5_springdata_ex2.domain.Student;
import com.example.sem5_springdata_ex2.repository.CourseRepository;
import com.example.sem5_springdata_ex2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public Student addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).get();//.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId).get();//.orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    // другие методы для удаления курса у студента и т.д.
}
