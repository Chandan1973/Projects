//package com.onlinelearningplatform.config;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
// 
//@Configuration
//@EnableWebSecurity
//public class securityConfig {
// 
//    @Autowired
//    private UserDetailsService userDetailsService;
// 
//    @Bean
//    public AuthenticationProvider authProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        return provider;
//    }
// 
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/user/**").hasRole("USER")
//                .requestMatchers("/professor/**").hasRole("PROFESSOR")
//                .requestMatchers("/", "/login", "/signup", "/css/**").permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .defaultSuccessUrl("/dashboard", true)
//                .failureUrl("/login?error=true")
//                .permitAll()
//            )
//            .sessionManagement(sess -> sess
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//            );
// 
//        return http.build();
//    }
//}
// 


package com.onlinelearningplatform.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 
@Configuration
@EnableWebSecurity
public class securityConfig {
 
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/login", "/choose-role", "/student/login","/student/register", "/professor/login","/professor/register","/professor/register/**","/css/**", "/js/**", "/images/**","/uploads/**", "/student/**", "/professor/**", "/admin/**", "/user/**","/studentlogin.css","/login.css","/dashboard.css","/index.css","/login.jpg","/userdashboard.css","/style.css","/dash.jpg").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/professor/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable()) // ❌ Disable default Spring login form
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }

 
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(); // You will create this class
    }
}