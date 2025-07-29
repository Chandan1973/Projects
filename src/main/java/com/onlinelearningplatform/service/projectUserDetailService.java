package com.onlinelearningplatform.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinelearningplatform.models.User;
import com.onlinelearningplatform.models.UserPrincipal;
import com.onlinelearningplatform.repository.AdminRepository;
import com.onlinelearningplatform.repository.UserRepository;
 
@Service
public class projectUserDetailService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepo;
 
    @Autowired
    private AdminRepository adminRepo;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepo.findByusername(username);
        if (appUser == null) {
            appUser = adminRepo.findByusername(username);
            if (appUser == null) {
                throw new UsernameNotFoundException("User not found");
            }
        }
        return new UserPrincipal(appUser);
    }
}