package com.onlinelearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinelearningplatform.models.Result;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {
	
}
