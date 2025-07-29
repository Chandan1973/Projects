package com.onlinelearningplatform.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.onlinelearningplatform.models.*;
import com.onlinelearningplatform.repository.QuestionRepo;
import com.onlinelearningplatform.repository.VideoRepository;
import com.onlinelearningplatform.service.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private VideoRepository vrepo;

    @Autowired
    private QuestionRepo qrepo;

    @Autowired
    private QuizService qService;

    private Result result;
    private Boolean submitted;

    @GetMapping("dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null)
            return "redirect:/student/login";
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("course", courses);
        model.addAttribute("isActive", true);
        return "UserDashboard";
    }

    @PostMapping("/enroll")
    public String enrollCourse(@RequestParam("courseId") int courseId,
                               @RequestParam("courseName") String courseName,
                               Model model,
                               HttpSession session) {

        if (session.getAttribute("userId") == null)
            return "redirect:/student/login";

        int userId = (int) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String email = (String) session.getAttribute("email");

        Course course = courseService.findCourseById(courseId);

        Enrollment enroll = new Enrollment();
        enroll.setCourse(course);
        enroll.setCoursename(courseName);
        enroll.setDescription(course.getDescription());
        enroll.setEnrolleddate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        enroll.setEnrolleduserId(userId);
        enroll.setEnrolledusername(userName);
        enroll.setEnrolleduseremail(email);
        enroll.setProfessorId(course.getProfessorId());

        enrollmentService.saveEnrollment(enroll);

        model.addAttribute("isActive", true);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/mycourse")
    public String myCourse(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null)
            return "redirect:/student/login";

        int id = (int) session.getAttribute("userId");
        List<Enrollment> list = enrollmentService.findAllEnrolleduserByid(id);

        List<Course> courseList = new ArrayList<>();
        for (Enrollment e : list) {
            courseList.add(e.getCourse());
        }

        model.addAttribute("mycourse", courseList);
        model.addAttribute("isActive", true);
        return "myCourse";
    }

    @GetMapping("/viewdetails/{id}")
    public String viewdetails(@PathVariable("id") int id, Model model, HttpSession session) {
        Course course = courseService.findCourseById(id);
        List<Video> videolist = vrepo.findAllVideosByCourse(course);

        model.addAttribute("course", course);
        model.addAttribute("videolist", videolist);
        model.addAttribute("quizAvailable", true);
        model.addAttribute("isActive", true);
        return "viewdetails";
    }

    @GetMapping("/quiz/start")
    public String quiz(@RequestParam int courseId, Model m) {
        Course course = courseService.findCourseById(courseId);
        List<Question> qlist = qrepo.findAllQuestionByCourse(course);

        QuestionForm qForm = new QuestionForm();
        qForm.setQuestions(qlist);

        m.addAttribute("course", course);
        m.addAttribute("qForm", qForm);
        m.addAttribute("qlist", qlist);
        m.addAttribute("isActive", true);
        submitted = false;
        return "quiz";
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(@ModelAttribute("qForm") QuestionForm qForm, Model m) {
        if (qForm == null || qForm.getQuestions() == null) {
            m.addAttribute("error", "Quiz form submission failed. Please try again.");
            return "quiz";
        }

        int totalQuestions = qForm.getQuestions().size();
        int correctAnswers = 0;

        for (Question answer : qForm.getQuestions()) {
            Question question = qrepo.findById(answer.getId()).orElse(null);
            if (question != null && question.getAns().equalsIgnoreCase(answer.getChoose())) {
                correctAnswers++;
            }
        }

        int marksObtained = (correctAnswers * 100) / totalQuestions;
        m.addAttribute("isActive", true);
        m.addAttribute("totalQuestions", totalQuestions);
        m.addAttribute("correctAnswers", correctAnswers);
        m.addAttribute("marksObtained", marksObtained);
        return "result";
    }

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);
        return "scoreboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
}
