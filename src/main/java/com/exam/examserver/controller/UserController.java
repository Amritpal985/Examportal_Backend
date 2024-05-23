package com.exam.examserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.services.UserService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User creatUser(@RequestBody User user) throws Exception{
        Set<UserRole>userRoles=new HashSet<>();

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Role role=new Role();
        role.setRoleId(44L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoles.add(userRole);


        return this.userService.createUser(user, userRoles);

    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void  deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }


}
