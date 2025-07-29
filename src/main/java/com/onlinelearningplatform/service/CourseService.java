package com.onlinelearningplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.repository.CourseRepository;
import com.onlinelearningplatform.repository.EnrollmentRepository;

@Service
public class CourseService 
{
	@Autowired
	private CourseRepository courseRepo;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
	
	public Course saveCourse(Course course)
	{
		return courseRepo.save(course);
	}
	public boolean updateCourse(int id,Course course)
	{
		courseRepo.save(course);
		return true;
	}
	
	public Course addNewCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public Course findCourseById(int courseId) {
		return courseRepo.findById(courseId).get();
	}
	
	public List<Course> getAllCourses()
	{
		return (List<Course>)courseRepo.findAll();
	}
	
	public void updateEnrolledcount(String coursename, int enrolledcount)
	{
		courseRepo.updateEnrolledcount(enrolledcount, coursename);
	}
	
	public Course fetchCourseByCoursename(String coursename)
	{
		return courseRepo.findByCoursename(coursename);
	}
	
	public List<Course> fetchCourseByProfessorId(int professorId){
		return courseRepo.findByProfessorId(professorId);
	}
	
	public boolean deleteCourse(int courseId) {
		courseRepo.deleteById(courseId);
		return true;
	}
	public List<Course> getAllCoursesWithEnrollments() {
	    List<Course> courses = courseRepository.findAll();
	    for (Course course : courses) {
	        course.setEnrollments(enrollmentRepository.findByCourse(course)); // assuming this works
	    }
	    return courses;
	}

}