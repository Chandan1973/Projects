package com.onlinelearningplatform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Professor;
import com.onlinelearningplatform.models.User;
import com.onlinelearningplatform.service.CourseService;
import com.onlinelearningplatform.service.ProfessorService;
import com.onlinelearningplatform.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private ProfessorService professorService;


	@Autowired
	private CourseService courseService;
	
	@GetMapping("/choose-role")
	public String chooserole()
	{
		return "chooserole";
	}
	
	
	
	@GetMapping("/student/login")
	public String login() {
		return "studentlogin";
	}

	@PostMapping("/student/login")
	public String loginUser(@ModelAttribute User user, Model model,HttpSession session) throws Exception {
		String currEmail = user.getEmail();
		String currPassword = user.getPassword();

		User userObj = null;
		if (currEmail != null && currPassword != null) {
			userObj = userService.fetchUserByEmail(currEmail);
		}
		if (userObj == null || !userObj.getPassword().equals(currPassword)) {
			model.addAttribute("Password is Incorrect");
			return "redirect:/student/login";
		}
		session.setAttribute("userName",userObj.getUsername() );
		session.setAttribute("email",currEmail );
		session.setAttribute("userId",userObj.getId() );
		List<Course> courses = courseService.getAllCourses();

		model.addAttribute("course", courses);
		model.addAttribute("isActive",true);
		return "UserDashboard";

	}
	@GetMapping("/professor/login")
	public String professorLogin() {
		return "professorlogin";
	}

//	@PostMapping("/professor/login")
//	public String loginDoctor(@ModelAttribute Professor professor, Model model,HttpSession session) throws Exception {
//		String currEmail = professor.getEmail();
//		String currPassword = professor.getPassword();
//
//		Professor professorObj = null;
//		if (currEmail != null && currPassword != null) {
//			professorObj = professorService.fetchProfessorByEmailAndPassword(currEmail, currPassword);
//		}
//		if (professorObj == null) {
//			model.addAttribute("message","Password is Incorrect");
//			return "redirect:/professor/login";
//		}
//		session.setAttribute("email",professorObj.getProfessorname() );
//		session.setAttribute("email",currEmail );
//		session.setAttribute("professorId",professorObj.getId() );
//		
//		List<Course> courses = courseService.getAllCourses();
//		model.addAttribute("course", courses);
//		model.addAttribute("isActive",true);
//		return "professordashboard";
//		
//	}
	
//	@PostMapping("/professor/login")
//	public String loginProfessor(@RequestParam String email,
//	                              @RequestParam String password,
//	                              HttpSession session,
//	                              Model model) {
//	    Professor professor = professorService.fetchProfessorByEmailAndPassword(email, password);
//
//	    if (professor == null) {
//	        model.addAttribute("popup", "Invalid email or password!");
//	        return "professorlogin";
//	    }
//
//	    // Check if status is reject
//	    if ("reject".equalsIgnoreCase(professor.getStatus())) {
//	        model.addAttribute("popup", "Your request to access LearnSphere has been rejected. Contact Admin.");
//	        return "professorlogin";
//	    }
//
//	    // Check if status is pending
//	    if (!"accept".equalsIgnoreCase(professor.getStatus())) {
//	        model.addAttribute("popup", "Your account is not yet approved by admin.");
//	        return "professorlogin";
//	    }
//
//	    // Successful login
//	    session.setAttribute("professorId", professor.getId());
//	    return "redirect:/professor/dashboard";
//	}

	
	@PostMapping("/professor/login")
	public String loginProfessor(@RequestParam String email,
	                             @RequestParam String password,
	                             Model model,
	                             HttpSession session) {
	    Professor professor = professorService.fetchProfessorByEmailAndPassword(email, password);

	    if (professor == null) {
	        model.addAttribute("popup", "Invalid Email or Password!");
	        return "professorlogin";
	    }

	    if ("reject".equalsIgnoreCase(professor.getStatus())) {
	        String reason = professor.getComment() != null ? professor.getComment() : "No reason provided";
	        model.addAttribute("popup", "Your registration has been rejected. Reason: " + reason.replace("\n", " "));
	        return "professorlogin";
	    }


	    if ("pending".equalsIgnoreCase(professor.getStatus())) {
	        model.addAttribute("popup", "Your profile is still under review. Please wait for admin approval.");
	        return "professorlogin";
	    }

	    // ✅ Valid login
	    session.setAttribute("professorId", professor.getId());
	    return "redirect:/professor/dashboard";
	}

	
	
}