package com.onlinelearningplatform.controllertest;

import com.onlinelearningplatform.controller.LoginController;

import com.onlinelearningplatform.models.Professor;
import com.onlinelearningplatform.models.User;
import com.onlinelearningplatform.service.CourseService;
import com.onlinelearningplatform.service.ProfessorService;
import com.onlinelearningplatform.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private ProfessorService professorService;

	@MockBean
	private CourseService courseService;

	@MockBean
	private HttpSession session;

	@Test
	void testStudentLoginPage() throws Exception {
		mockMvc.perform(get("/student/login")).andExpect(status().isOk()).andExpect(view().name("studentlogin"));
	}

	@Test
	void testProfessorLoginPage() throws Exception {
		mockMvc.perform(get("/professor/login")).andExpect(status().isOk()).andExpect(view().name("professorlogin"));
	}

	@Test
	void testStudentLoginSuccess() throws Exception {
		// Mock User
		User mockUser = new User();
		mockUser.setEmail("test@student.com");
		mockUser.setPassword("password");
		mockUser.setUsername("Test Student");
		mockUser.setId(1);

		// Mock Services
		when(userService.fetchUserByEmail("test@student.com")).thenReturn(mockUser);
		when(courseService.getAllCourses()).thenReturn(Collections.emptyList());

		// Perform POST request
		mockMvc.perform(post("/student/login").param("email", "test@student.com").param("password", "password"))
				.andExpect(status().isOk()).andExpect(view().name("UserDashboard"))
				.andExpect(model().attribute("course", Collections.emptyList()))
				.andExpect(model().attribute("isActive", true));

		// Verify Session Attributes
		verify(session).setAttribute("userName", "Test Student");
		verify(session).setAttribute("email", "test@student.com");
		verify(session).setAttribute("userId", 1L);
	}

	@Test
	void testStudentLoginFailure() throws Exception {
		when(userService.fetchUserByEmail("wrong@student.com")).thenReturn(null);

		mockMvc.perform(post("/student/login").param("email", "wrong@student.com").param("password", "wrongpassword"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/student/login"));
	}

	@Test
	void testProfessorLoginSuccess() throws Exception {
		// Mock Professor
		Professor mockProfessor = new Professor();
		mockProfessor.setEmail("prof@college.com");
		mockProfessor.setPassword("password");
		mockProfessor.setProfessorname("Dr. Smith");
		mockProfessor.setId(1);

		// Mock Services
		when(professorService.fetchProfessorByEmailAndPassword("prof@college.com", "password"))
				.thenReturn(mockProfessor);
		when(courseService.getAllCourses()).thenReturn(Collections.emptyList());

		// Perform POST request
		mockMvc.perform(post("/professor/login").param("email", "prof@college.com").param("password", "password"))
				.andExpect(status().isOk()).andExpect(view().name("professordashboard"))
				.andExpect(model().attribute("course", Collections.emptyList()))
				.andExpect(model().attribute("isActive", true));

		// Verify Session Attributes
		verify(session).setAttribute("email", "prof@college.com");
		verify(session).setAttribute("professorId", 1L);
	}

	@Test
	void testProfessorLoginFailure() throws Exception {
		when(professorService.fetchProfessorByEmailAndPassword("wrong@prof.com", "wrongpassword")).thenReturn(null);

		mockMvc.perform(post("/professor/login").param("email", "wrong@prof.com").param("password", "wrongpassword"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/professor/login"));
	}
}