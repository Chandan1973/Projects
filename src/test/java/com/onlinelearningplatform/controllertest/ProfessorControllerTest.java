package com.onlinelearningplatform.controllertest;

import com.onlinelearningplatform.controller.ProfessorController;
import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Enrollment;
import com.onlinelearningplatform.models.Question;
import com.onlinelearningplatform.models.Video;
import com.onlinelearningplatform.service.CourseService;
import com.onlinelearningplatform.service.EnrollmentService;
import com.onlinelearningplatform.repository.QuestionRepo;
import com.onlinelearningplatform.repository.VideoRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private EnrollmentService enrollmentService;

    @MockBean
    private QuestionRepo qrepo;

    @MockBean
    private VideoRepository vrepo;

    @MockBean
    private HttpSession session;

    @Test
    void testGetDashboard() throws Exception {
        when(session.getAttribute("professorId")).thenReturn(1);
        List<Course> courses = Collections.emptyList();
        when(courseService.fetchCourseByProfessorId(1)).thenReturn(courses);

        mockMvc.perform(get("/professor/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("professordashboard"))
                .andExpect(model().attribute("course", courses))
                .andExpect(model().attribute("isActive", true));
    }

    @Test
    void testGetAddCoursePage() throws Exception {
        when(session.getAttribute("professorId")).thenReturn(1);

        mockMvc.perform(get("/professor/addcourse"))
                .andExpect(status().isOk())
                .andExpect(view().name("addcourses"))
                .andExpect(model().attribute("isActive", true));
    }

//    @Test
//    void testAddNewCourse() throws Exception {
//        when(session.getAttribute("professorId")).thenReturn(1);
//        Course course = new Course();
//        course.setCourseid("COURSE123");
//        course.setProfessorId(1);
//        
//        MockMultipartFile file = new MockMultipartFile("image", "test.jpg", "image/jpeg", "image content".getBytes());
//        
//        when(courseService.addNewCourse(course)).thenReturn(course);
//
//        mockMvc.perform(post("/professor/addcourse")
//                        .param("courseid", "COURSE123")
//                        .param("professorId", "1")
//                        .file(file))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/professor/dashboard"));
//    }

    @Test
    void testDeleteCourse() throws Exception {
        when(session.getAttribute("professorId")).thenReturn(1);
        int courseId = 1;

        when(courseService.deleteCourse(courseId)).thenReturn(true);

        mockMvc.perform(get("/professor/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/professor/dashboard"));
    }

    @Test
    void testEditCoursePage() throws Exception {
        when(session.getAttribute("professorId")).thenReturn(1);
        int courseId = 1;
        Course course = new Course();
        course.setCourseid("COURSE123");

        when(courseService.findCourseById(courseId)).thenReturn(course);

        mockMvc.perform(get("/professor/editcourse/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("editcourse"))
                .andExpect(model().attribute("courseById", course));
    }

//    @Test
//    void testAddQuiz() throws Exception {
//        when(session.getAttribute("professorId")).thenReturn(1);
//        int courseId = 1;
//        Question question = new Question();
//        question.setTitle("Sample Question");
//        question.setOptionA("Option A");
//        question.setOptionB("Option B");
//        question.setOptionC("Option C");
//        question.setOptionD("Option D");
//        question.setAns("A");
//
//        mockMvc.perform(post("/professor/quizform/1")
//                        .param("title", "Sample Question")
//                        .param("optionA", "Option A")
//                        .param("optionB", "Option B")
//                        .param("optionC", "Option C")
//                        .param("optionD", "Option D")
//                        .param("ans", "A"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("quizform"));
//    }
//
////    @Test
//    void testUploadVideo() throws Exception {
//        when(session.getAttribute("professorId")).thenReturn(1);
//        MockMultipartFile file = new MockMultipartFile("file", "test.mp4", "video/mp4", "video content".getBytes());
//
//        mockMvc.perform(post("/professor/uploadvideo/1")
//                        .file(file))
//                .andExpect(status().isOk())
//                .andExpect(view().name("uploadvideo"));
//    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(get("/professor/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/professor/login"));
    }
}
