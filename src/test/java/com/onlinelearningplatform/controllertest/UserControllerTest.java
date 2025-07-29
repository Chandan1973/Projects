package com.onlinelearningplatform.controllertest;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.onlinelearningplatform.controller.UserController;
import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Enrollment;
import com.onlinelearningplatform.models.Question;
import com.onlinelearningplatform.models.QuestionForm;
import com.onlinelearningplatform.repository.QuestionRepo;
import com.onlinelearningplatform.service.CourseService;
import com.onlinelearningplatform.service.EnrollmentService;
import com.onlinelearningplatform.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private EnrollmentService enrollmentService;

    @Mock
    private QuestionRepo qrepo;
    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserController userController;

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDashboard_redirectIfNotLoggedIn() {
        when(session.getAttribute("userId")).thenReturn(null);

        String result = userController.dashboard(model, session);

        assertEquals("redirect:/student/login", result);
    }

    @Test
    void testDashboard_showsCoursesWhenLoggedIn() {
        when(session.getAttribute("userId")).thenReturn(1);
        when(courseService.getAllCourses()).thenReturn(List.of(new Course(), new Course()));

        String result = userController.dashboard(model, session);

        verify(model).addAttribute("course", List.of(new Course(), new Course()));
        verify(model).addAttribute("isActive", true);
        assertEquals("UserDashboard", result);
    }

    @Test
    void testEnrollCourse_enrollsWhenLoggedIn() {
        when(session.getAttribute("userId")).thenReturn(1);
        when(courseService.findCourseById(1)).thenReturn(new Course());
        when(enrollmentService.findAllEnrolleduserByid(1)).thenReturn(List.of());

        String result = userController.enrollCourse(1, "Course 1", model, session);

        verify(enrollmentService).saveEnrollment(any(Enrollment.class));
        assertEquals("redirect:/user/dashboard", result);
    }

    @Test
    void testEnrollCourse_redirectIfAlreadyEnrolled() {
        when(session.getAttribute("userId")).thenReturn(1);
        when(courseService.findCourseById(1)).thenReturn(new Course());
        when(enrollmentService.findAllEnrolleduserByid(1)).thenReturn(List.of(new Enrollment()));

        String result = userController.enrollCourse(1, "Course 1", model, session);

        verify(model).addAttribute("message", "You are already enrolled in this course.");
        assertEquals("redirect:/user/dashboard", result);
    }

//    @Test
//    void testViewDetails() {
//        when(courseService.findCourseById(1)).thenReturn(new Course());
//        when(session.getAttribute("userId")).thenReturn(1);
//
//        String result = userController.viewdetails(1, model, session);
//
//        verify(model).addAttribute("course", new Course());
//        assertEquals("viewdetails", result);
//    }

    @Test
    void testQuiz() {
        Course course = new Course();
        course.setId(1);
        when(courseService.findCourseById(1)).thenReturn(course);
        when(session.getAttribute("userId")).thenReturn(1);

        String result = userController.quiz(1, model, redirectAttributes);

        verify(model).addAttribute("course", course);
        assertEquals("quiz", result);
    }

    @Test
    void testSubmitQuiz() {
        when(session.getAttribute("userId")).thenReturn(1);
        when(qrepo.findById(1)).thenReturn(java.util.Optional.of(new Question()));
        when(qrepo.findAllQuestionByCourse(any(Course.class))).thenReturn(List.of(new Question()));

        QuestionForm qForm = new QuestionForm();
        qForm.setQuestions(List.of(new Question()));

        String result = userController.submitQuiz(qForm, model);

        assertTrue(result.contains("marksObtained"));
        verify(model).addAttribute("marksObtained", anyInt());
    }
}
