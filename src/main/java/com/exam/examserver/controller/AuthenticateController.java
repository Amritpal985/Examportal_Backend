package com.exam.examserver.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.config.JwtUtil;
import com.exam.examserver.model.JwtRequest;
import com.exam.examserver.model.JwtResponse;
import com.exam.examserver.model.User;
import com.exam.examserver.services.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    // generate-token
    @PostMapping("/generate-token")
    public ResponseEntity<?>generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
            
        } catch (UsernameNotFoundException e) {
            throw new Exception("username not found");
        }
        
        // user authenticated
        UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED");
        }
        catch(BadCredentialsException e)
        {
            throw new Exception("Bad credentials");
        }
    }

    // get loggedIn user

    @GetMapping("/current-user")
    public User getCurretUser(Principal principal)
    {
        // System.out.println();
        return ((User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
    }


}
