package com.onlinelearningplatform.config;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 
import java.io.IOException;
import java.util.Collection;
 
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
 
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
 
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
 
            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/dashboard");
                return;
            } else if (role.equals("ROLE_PROFESSOR")) {
                response.sendRedirect("/professor/dashboard");
                return;
            } else if (role.equals("ROLE_USER")) {
                response.sendRedirect("/user/dashboard");
                return;
            }
        }
 
        response.sendRedirect("/default");
    }
}
 