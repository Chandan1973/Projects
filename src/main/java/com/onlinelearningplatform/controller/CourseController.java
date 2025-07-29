//package com.onlinelearningplatform.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//
//import com.onlinelearningplatform.models.ExternalCourse;
//
//@Controller
//public class CourseController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @GetMapping("/external_courses")
//    public String showExternalCourses(Model model) {
//       // RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));  // 👈 Force JSON response
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<ExternalCourse[]> response = restTemplate.exchange(
//                "https://api.sampleapis.com/codingresources/codingResources",
//                HttpMethod.GET,
//                entity,
//                ExternalCourse[].class
//        );
//
//        ExternalCourse[] externalCourses = response.getBody();
//        model.addAttribute("externalCourses", externalCourses);
//
//        return "external_courses";  // make sure this view exists
//    }
//
//}
