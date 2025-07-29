//package com.onlinelearningplatform.service;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.onlinelearningplatform.models.ExternalCourse;
//
//@Service
//public class ExternalCourseService {
//
//	@Autowired
//	private RestTemplate restTemplate;
//	
//    private final String API_URL = "https://fakestoreapi.com/products";
//
//    public List<ExternalCourse> fetchExternalCourses() {
//        RestTemplate restTemplate = new RestTemplate();
//        ExternalCourse[] courseArray = restTemplate.getForObject(API_URL, ExternalCourse[].class);
//        return Arrays.asList(courseArray);
//    }
//}
//
