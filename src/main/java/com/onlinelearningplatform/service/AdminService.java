package com.onlinelearningplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearningplatform.repository.AdminRepository;
import com.onlinelearningplatform.repository.UserRepository;

@Service
public class AdminService 
{
	@Autowired
  AdminRepository adminRepo ;
  
	@Autowired
	UserRepository userRepo;
}
