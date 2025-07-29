package com.onlinelearningplatform.controllertest;



import com.onlinelearningplatform.controller.RegistrationController;
import com.onlinelearningplatform.models.Professor;
import com.onlinelearningplatform.models.User;
import com.onlinelearningplatform.service.ProfessorService;
import com.onlinelearningplatform.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProfessorService professorService;

    @Test
    void testStudentRegisterPage() throws Exception {
        mockMvc.perform(get("/student/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentregister"));
    }

    @Test
    void testProfessorRegisterPage() throws Exception {
        mockMvc.perform(get("/professor/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("professorregister"));
    }

    @Test
    void testStudentRegisterSuccess() throws Exception {
        User user = new User();
        user.setEmail("teststudent@example.com");
        user.setUserid("123ABC");
        
        // Mock the UserService to return a newly registered user
        when(userService.fetchUserByEmail(user.getEmail())).thenReturn(null); // No user exists with this email
        when(userService.saveUser(user)).thenReturn(user);

        mockMvc.perform(post("/student/register")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection()) // Expecting redirect after successful registration
                .andExpect(view().name("redirect:/student/login"));
    }

//    @Test
//    void testStudentRegisterUserAlreadyExists() throws Exception {
//        User user = new User();
//        user.setEmail("teststudent@example.com");
//
//        // Mock the UserService to simulate that a user with the given email already exists
//        when(userService.fetchUserByEmail(user.getEmail())).thenReturn(new User());
//
//        mockMvc.perform(post("/student/register")
//                        .flashAttr("user", user))
//                .andExpect(status().isOk()) // Should not redirect, stays on the same page with error
//                .andExpect(view().name("studentregister")); // Optionally, add logic to check error messages here
//    }

    @Test
    void testProfessorRegisterSuccess() throws Exception {
        Professor professor = new Professor();
        professor.setEmail("testprofessor@example.com");
        professor.setProfessorid("123ABC");
        
        // Mock the ProfessorService to return a newly registered professor
        when(professorService.fetchProfessorByEmail(professor.getEmail())).thenReturn(null); // No professor exists with this email
        when(professorService.saveProfessor(professor)).thenReturn(professor);

        mockMvc.perform(post("/professor/register")
                        .flashAttr("professor", professor))
                .andExpect(status().is3xxRedirection()) // Expecting redirect after successful registration
                .andExpect(view().name("redirect:/professor/login"));
    }

//    @Test
//    void testProfessorRegisterAlreadyExists() throws Exception {
//        Professor professor = new Professor();
//        professor.setEmail("testprofessor@example.com");
//
//        // Mock the ProfessorService to simulate that a professor with the given email already exists
//        when(professorService.fetchProfessorByEmail(professor.getEmail())).thenReturn(new Professor());
//
//        mockMvc.perform(post("/professor/register")
//                        .flashAttr("professor", professor))
//                .andExpect(status().isOk()) // Should not redirect, stays on the same page with error
//                .andExpect(view().name("professorregister")); // Optionally, add logic to check error messages here
//    }
}