package com.onlinelearningplatform.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinelearningplatform.models.Professor;
import com.onlinelearningplatform.models.User;
//import com.onlinelearningplatform.models.User;
import com.onlinelearningplatform.service.ProfessorService;
import com.onlinelearningplatform.service.UserService;

@Controller
public class RegistrationController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
//	private static final String regex="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	
	
	
	@GetMapping("/student/register")
	public String register(Model model) {
		return "studentregister";
	}
	
	@PostMapping("/student/register")
	public String registerUser(@ModelAttribute User user,Model model) throws Exception
	{
		String currEmail = user.getEmail();
		String newID = getNewID();
		user.setUserid(newID);
		
		if(currEmail != null || !"".equals(currEmail))
		{
			User userObj = userService.fetchUserByEmail(currEmail);
			if(userObj != null)
			{
				throw new Exception("User with "+currEmail+" already exists !!!");
			}
		}
		User userObj = null;
		userObj = userService.saveUser(user);
		return "redirect:/student/login";
	}
	
	@GetMapping("/professor/register")
	public String registerProfessor(Model model) {
		return "professorregister";
	}
//	
//	
//	@PostMapping("/professor/register")
//	public String registerDoctor(@ModelAttribute Professor professor,Model model) throws Exception
//	{
//		String currEmail = professor.getEmail();
//		String newID = getNewID();
//		professor.setProfessorid(newID);
//		professor.setExperience("2 year");
//		professor.setDepartment("IT");
//		
//		professor.setStatus("pending");
//		
//		if(currEmail != null || !"".equals(currEmail))
//		{
//			Professor professorObj = professorService.fetchProfessorByEmail(currEmail);
//			if(professorObj != null)
//			{
//				throw new Exception("Professor with "+currEmail+" already exists !!!");
//			}
//		}
//		Professor professorObj = null;
//		professorObj = professorService.saveProfessor(professor);
//		return "redirect:/professor/login";
//	}
//	
//	public String getNewID()
//	{
//		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz";
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 12; i++) 
//        {
//            int index = (int)(AlphaNumericString.length() * Math.random());
//            sb.append(AlphaNumericString.charAt(index));
//        }
//        return sb.toString();
//	}
	
	@PostMapping("/professor/register")
	public String registerDoctor(@ModelAttribute Professor professor, Model model) throws Exception {
	    String currEmail = professor.getEmail();
	    String newID = getNewID();
	    professor.setProfessorid(newID);
	    professor.setStatus("pending");  // Default status

	    // Check eligibility
	    String degree = professor.getDegreecompleted().toLowerCase();
	    int exp = Integer.parseInt(professor.getExperience());

	    List<String> allowedDegrees = List.of("btech", "mtech", "phd", "msc");

	    if (!allowedDegrees.contains(degree.toLowerCase()) || exp < 2) {
	        model.addAttribute("popup", "You do not meet the eligibility criteria.");
	        return "professorregister";
	    }

	    if (currEmail != null && !"".equals(currEmail)) {
	        Professor professorObj = professorService.fetchProfessorByEmail(currEmail);
	        if (professorObj != null) {
	            throw new Exception("Professor with " + currEmail + " already exists !!!");
	        }
	    }

	    professorService.saveProfessor(professor);
	    return "redirect:/professor/login";
	}

	private String getNewID() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}