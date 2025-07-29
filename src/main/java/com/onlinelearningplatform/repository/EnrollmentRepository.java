package com.onlinelearningplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Enrollment;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>
{
   // public Enrollment findByCoursename(String coursename);
	
	//public Enrollment findByCourseid(int courseid);
	
	public List<Enrollment> findByCourse(Course course);
	
	public List<Enrollment> findByenrolleduserId(int enrolleduserId);
	
	public List<Enrollment> findAllEnrolledprofessorByid(int professorId);
	
	//public List<Enrollment> findEnrollementByCourseidAndProfessorId(int courseid,int enrolleduserId);
}