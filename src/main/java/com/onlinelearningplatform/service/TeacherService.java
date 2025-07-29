//package com.onlinelearningplatform.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.elearning.models.Teacher;
//import com.onlinelearningplatform.models.Course;
//import com.onlinelearningplatform.repository.CourseRepository;
//import com.onlinelearningplatform.repository.TeacherRepository;
//
//@Service
//public class TeacherService {
//
//    @Autowired
//    private TeacherRepository teacherRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    public List<Course> getCoursesByTeacher(Long teacherId) {
//        return courseRepository.findAll() // Add logic to filter by teacher
//    }
//
//    public void addCourseForTeacher(Course course, Long teacherId) {
//        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
//        course.setTeacher(teacher);
//        courseRepository.save(course);
//    }
//}