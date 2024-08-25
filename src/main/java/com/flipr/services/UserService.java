package com.flipr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flipr.model.User;
import com.flipr.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String signUp(User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			return "Email is already registered!";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "User registered successfully with ID: " + user.getId();
	}

	public String signIn(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			if (passwordEncoder.matches(password, user.get().getPassword())) {
				return "Login successful!";
			} else {
				return "Invalid password!";
			}
		} else {
			return "Email not found!";
		}
	}
}
