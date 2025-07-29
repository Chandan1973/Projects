package com.onlinelearningplatform.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Enrollment;
import com.onlinelearningplatform.models.Question;
import com.onlinelearningplatform.models.Video;
import com.onlinelearningplatform.repository.QuestionRepo;
import com.onlinelearningplatform.repository.VideoRepository;
import com.onlinelearningplatform.service.CourseService;
import com.onlinelearningplatform.service.EnrollmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private QuestionRepo qrepo;

	@Autowired
	private VideoRepository vrepo;

	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

	@GetMapping("/dashboard")
	public String getTotalCourses(Model model, HttpSession session) throws Exception {
		if (session.getAttribute("professorId") == null)
			return "redirect:/professor/login";
		int id = (int) session.getAttribute("professorId");
		List<Course> courses = courseService.fetchCourseByProfessorId(id);
		model.addAttribute("course", courses);
		model.addAttribute("isActive", true);
		return "professordashboard";
	}

	@GetMapping("/addcourse")
	public String course(Model model, HttpSession session) throws Exception {
		if (session.getAttribute("professorId") == null)
			return "redirect:/professor/login";
		model.addAttribute("isActive", true);
		return "addcourses";
	}

	@PostMapping("/addcourse")
	public String addNewCourse(@ModelAttribute Course course, @RequestParam("image") MultipartFile file, Model model,
			HttpSession session) throws Exception {
		if (session.getAttribute("professorId") == null)
			return "Redirect:/professor/login";

		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());

		String newID = getNewID();
		course.setCourseid(newID);
		int id = (int) session.getAttribute("professorId");
		course.setProfessorId(id);
		course.setImageurl(file.getOriginalFilename());
		Course courseObj = courseService.addNewCourse(course);
		model.addAttribute("isActive", true);
		return "redirect:/professor/dashboard";
	}

	@GetMapping("/coursedetails/{id}")
	public String enrolledCourseList(@PathVariable("id") int id, Model model, HttpSession session) throws Exception {
		if (session.getAttribute("professorId") == null)
			return "redirect:/professor/login";
		int userId = (int) session.getAttribute("professorId");

		List<Enrollment> list = enrollmentService.findEnrollementByCourseidenrolleduserId(id, userId);

		model.addAttribute("isActive", true);
		model.addAttribute("list", list);
		model.addAttribute("courseId", id);
		return "coursedetail";
	}

	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes re)
			throws Exception {
		if (session.getAttribute("professorId") == null)
			return "redirect:/professor/login";
		boolean val = courseService.deleteCourse(id);
		model.addAttribute("isActive", true);
		re.addFlashAttribute("message", "Course Deleted Successfully !");

		return "redirect:/professor/dashboard";
	}

	@GetMapping("/editcourse/{id}")
	public String editCourse(Model model, HttpSession session, @PathVariable("id") int id, RedirectAttributes re)
			throws Exception {
		if (session.getAttribute("professorId") == null)
			return "redirect:/professor/login";
		Course courseById = courseService.findCourseById(id);
		model.addAttribute("isActive", true);
		model.addAttribute("courseById", courseById);
		return "editcourse";
	}

	@PostMapping("/editcourse/{id}")
	public String editCourse(@PathVariable("id") int id,@ModelAttribute Course course, Model model, @RequestParam("image") MultipartFile file, HttpSession session) throws Exception {
		if (session.getAttribute("professorId") == null)
			return "Redirect:/professor/login";
		int pid = (int) session.getAttribute("professorId");
		Course c = courseService.findCourseById(id);
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
		course.setProfessorId(pid);
		course.setImageurl(file.getOriginalFilename());
		boolean b =courseService.updateCourse(course.getId(),course);
		model.addAttribute("isActive", true);
		return "redirect:/professor/dashboard";
	}

	@GetMapping("/quizform/{id}")
	public String quizForm(@PathVariable("id") int id, Model model, HttpSession session) {
		model.addAttribute("courseId", id);
		model.addAttribute("isActive", true);
		return "quizform";
	}

	@PostMapping("/quizform/{id}")
	public String addQuiz(@PathVariable("id") int id,Model model, @ModelAttribute Question question, HttpSession session) {
	
		Course course = courseService.findCourseById(id);
		Question q= new Question();
		q.setTitle(question.getTitle());
		q.setOptionA(question.getOptionA());
		q.setOptionB(question.getOptionB());
		q.setOptionC(question.getOptionC());
		q.setOptionD(question.getOptionD());
		q.setAns(question.getAns());
		q.setChoose("");
		q.setCourse(course);
		qrepo.save(q);
		model.addAttribute("isActive", true);
		return "quizform";
	}

	@GetMapping("/uploadvideo/{id}")
	public String uploadvideo(@PathVariable("id") int id, Model model, HttpSession session) {
		model.addAttribute("courseId", id);
		model.addAttribute("isActive", true);
		return "uploadvideo";
	}

	@PostMapping("/uploadvideo/{id}")
	public String uploadvideos(@PathVariable("id") int id, @RequestParam("file") MultipartFile file, Model model,
			HttpSession session) throws IOException {
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
		Video video = new Video();
		Course course = courseService.findCourseById(id);
		video.setCourse(course);
		video.setDirectory(file.getOriginalFilename());
		model.addAttribute("isActive", true);
		vrepo.save(video);
		return "uploadvideo";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	public String getNewID() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

}
