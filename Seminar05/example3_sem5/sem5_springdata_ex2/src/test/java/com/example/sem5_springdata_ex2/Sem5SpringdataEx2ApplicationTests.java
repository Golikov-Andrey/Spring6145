package com.example.sem5_springdata_ex2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sem5SpringdataEx2ApplicationTests {

	@Test
	void contextLoads() {
	}

//	@RunWith(SpringRunner.class)
//	@SpringBootTest
//	public class StudentControllerTest {
//
//		@Autowired
//		private StudentController studentController;
//
//		@Test
//		public void testCreateStudent() {
//			Student student = new Student();
//			student.setName("John Doe");
//
//			Student createdStudent = studentController.createStudent(student);
//
//			assertNotNull(createdStudent);
//			assertNotNull(createdStudent.getId());
//			assertEquals("John Doe", createdStudent.getName());
//		}
//
//		@Test
//		public void testCreateCourse() {
//			Course course = new Course();
//			course.setName("Maths");
//
//			Course createdCourse = studentController.createCourse(course);
//
//			assertNotNull(createdCourse);
//			assertNotNull(createdCourse.getId());
//			assertEquals("Maths", createdCourse.getName());
//		}
//
//		@Test
//		public void testAddCourseToStudent() {
//			Student student = new Student();
//			student.setName("John Doe");
//			Student createdStudent = studentController.createStudent(student);
//
//			Course course = new Course();
//			course.setName("Maths");
//			Course createdCourse = studentController.createCourse(course);
//
//			Student updatedStudent = studentController.addCourseToStudent(createdStudent.getId(), createdCourse.getId());
//
//			assertNotNull(updatedStudent);
//			assertEquals(1, updatedStudent.getCourses().size());
//			assertTrue(updatedStudent.getCourses().contains(createdCourse));
//		}
//
//		// другие тесты для удаления курса у студента и т.д.
//	}


}
