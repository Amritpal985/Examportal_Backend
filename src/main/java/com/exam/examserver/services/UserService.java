package com.exam.examserver.services;

import java.util.Set;

// import org.springframework.stereotype.Service;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;

// @Service
public interface UserService {
    public User createUser(User user,Set<UserRole>userRoles) throws Exception;

    // get user by username
    public User getUser(String username);

    public void deleteUser(Long userId);
    
}
