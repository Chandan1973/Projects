package com.onlinelearningplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{
	
	List<Video> findAllVideosByCourse(Course course);
}
