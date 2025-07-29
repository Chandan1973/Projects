package com.onlinelearningplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearningplatform.models.Course;
import com.onlinelearningplatform.models.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
		List<Question> findAllQuestionByCourse(Course course);
}