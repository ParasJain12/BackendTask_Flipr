package com.flipr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipr.model.User;
import com.flipr.services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        return userService.signUp(user);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User user) {
        String response = userService.signIn(user.getEmail(), user.getPassword());
        if (response.equals("Login successful!")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
