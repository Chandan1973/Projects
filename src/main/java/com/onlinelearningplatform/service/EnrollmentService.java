package com.onlinelearningplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Enrollment;
import com.onlinelearningplatform.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
	@Autowired
	private EnrollmentRepository enrollmentRepo;

	public Enrollment saveEnrollment(Enrollment enrollment) {
		return enrollmentRepo.save(enrollment);
	}

	public Enrollment addNewEnrollment(Enrollment enrollment) {
		return enrollmentRepo.save(enrollment);
	}

	public List<Enrollment> getAllEnrollments() {
		return (List<Enrollment>) enrollmentRepo.findAll();
	}
	public List<Enrollment> findAllEnrolleduserByid(int enrolleduserId){
		return enrollmentRepo.findByenrolleduserId(enrolleduserId);
	}
	public List<Enrollment> findAllEnrolledprofessorByid(int professorId){
		return enrollmentRepo.findAllEnrolledprofessorByid(professorId);
	}
//	public Enrollment fetchByCoursename(String coursename) {
//		return enrollmentRepo.findByCoursename(coursename);
//	}
//	public List<Enrollment> findEnrollementByCourseidenrolleduserId(int enrolleduserId,int courseid) {
//		return enrollmentRepo.findEnrollementByCourseidAndProfessorId(enrolleduserId,courseid);
//	}
	
	public List<Enrollment> findByCourseAndEnrolleduserId(Course course, int userId) {
	    List<Enrollment> all = enrollmentRepo.findByCourse(course);
	    return all.stream()
	              .filter(e -> e.getEnrolleduserId() == userId)
	              .toList(); // Java 17+ syntax
	}

}