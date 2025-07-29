package com.onlinelearningplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinelearningplatform.models.User;

public interface AdminRepository extends JpaRepository<User, Integer>
{

	public User findByusername(String username);
 
}
