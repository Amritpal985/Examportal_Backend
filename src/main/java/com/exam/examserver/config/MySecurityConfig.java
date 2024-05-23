package com.exam.examserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig{


    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // AuthenicationMaanger
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(management -> management.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> {
                            try {
                                authorize.requestMatchers("/generate-token").permitAll()
                                        .requestMatchers("/user/").permitAll()
                                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .csrf(csrf -> csrf.disable());

        
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
