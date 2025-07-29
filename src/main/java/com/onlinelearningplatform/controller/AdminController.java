package com.onlinelearningplatform.controller;

import com.onlinelearningplatform.models.User;
import com.onlinelearningplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.onlinelearningplatform.models.Professor;
import com.onlinelearningplatform.service.ProfessorService;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.service.CourseService;


import java.util.List;

@Controller
@RequestMapping("/admin") // iska matlab sab URL /admin se start honge
public class AdminController {
	
	@Autowired
    private UserService userService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private CourseService courseService;
	
    // GET: login page dikhane ke liye
    @GetMapping("/login")
    public String showLoginPage() {
        return "adminlogin"; // ye tumhari HTML file ka naam hona chahiye
    }

    // POST: login credentials check karne ke liye
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Hardcoded admin credentials
        if (email.equals("admin@gmail.com") && password.equals("admin123")) {
            session.setAttribute("adminEmail", email); // session me email save
            return "redirect:/admin/dashboard"; // login ke baad dashboard page
        } else {
            model.addAttribute("error", "Galat Email ya Password!");
            return "adminlogin"; // dubara login page dikhao
        }
    }

    // GET: dashboard dikhane ke liye
//    @GetMapping("/dashboard")
//    public String showDashboard(Model model, HttpSession session) {
//        String adminEmail = (String) session.getAttribute("adminEmail");
//
//        if (adminEmail == null) {
//            return "redirect:/admin/login"; // agar login nahi kiya to wapas login page
//        }
//
//        // yahan tum dashboard me data bhejna chaho to bhej sakti ho
//        model.addAttribute("adminEmail", adminEmail);
//
//        return "admindashboard"; // ye tumhari dashboard wali HTML file hai
//    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        String adminEmail = (String) session.getAttribute("adminEmail");
        if (adminEmail == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("adminEmail", adminEmail);
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("courseCount", courseService.getAllCourses().size());
        model.addAttribute("professorCount", professorService.getAllProfessors().size());

        return "admindashboard";
    }
    
    
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }
    
    @GetMapping("/professors")
    public String showAllProfessors(Model model) {
        List<Professor> professors = professorService.getAllProfessors();
        model.addAttribute("professors", professors);
        return "admin-professors";
    }

    
    @GetMapping("/professor/accept/{email}")
    public String acceptProfessor(@PathVariable String email) {
        professorService.updateStatus(email);
        return "redirect:/admin/professors";
    }

//    @GetMapping("/professor/reject/{email}")
//    public String rejectProfessor(@PathVariable String email) {
//        professorService.rejectStatus(email);
//        return "redirect:/admin/professors";
//    }
    
//    @GetMapping("/professor/rejectform/{email}")
//    public String showRejectForm(@PathVariable String email, Model model) {
//        model.addAttribute("email", email);
//        return "reject-professor"; // new HTML file
//    }
//
//@GetMapping("/professor/reject/{email}")
//public String rejectProfessor(@PathVariable String email) {
//    String comment = "Eligibility not met (degree or experience missing)";
//    professorService.rejectStatusWithComment(email, comment);
//    return "redirect:/admin/professors";
//}
    
    
    @GetMapping("/professor/rejectform/{email}")
    public String showRejectForm(@PathVariable String email, Model model) {
        model.addAttribute("email", email);
        return "reject-professor";
    }

    @PostMapping("/professor/reject")
    public String rejectProfessor(@RequestParam String email, @RequestParam String comment) {
        professorService.rejectStatusWithComment(email, comment);

        // Optional: Send email
        // emailService.send(email, "Your request was rejected", comment);

        return "redirect:/admin/professors";
    }



    


@GetMapping("/courses")
public String showCourses(Model model) {
    List<Course> courses = courseService.getAllCoursesWithEnrollments();
    model.addAttribute("courses", courses);
    return "admin-courses";
}



@GetMapping("/addcourse")
public String showAddCourseForm(Model model) {
    model.addAttribute("course", new Course());
    return "redirect:/admin/addcourses";
}

//@PostMapping("/addcourse")
//public String addCourse(@ModelAttribute("course") Course course,
//                        @RequestParam("image") MultipartFile imageFile) {
//    try {
//        String fileName = imageFile.getOriginalFilename();
//        Path uploadPath = Paths.get(new ClassPathResource("static/uploads").getFile().getAbsolutePath());
//        Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//        course.setImageurl(fileName);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    courseService.saveCourse(course);
//    return "redirect:/admin/courses";
//}

@GetMapping("/deletecourse/{id}")
public String deleteCourse(@PathVariable int id) {
    courseService.deleteCourse(id);
    return "redirect:/admin/courses";
}

@GetMapping("/editcourse/{id}")
public String showEditForm(@PathVariable int id, Model model) {
    Course course = courseService.findCourseById(id);
    model.addAttribute("course", course);

    return "addcourses"; // Same form used for add/edit
}

    
}
