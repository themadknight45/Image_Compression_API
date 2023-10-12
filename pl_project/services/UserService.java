package com.pl_project.pl_project.services;

import org.springframework.stereotype.Service;

import com.pl_project.pl_project.payload.UserResponse;

@Service
public interface UserService {
	UserResponse login(String username,String password);
	UserResponse register(String username,String password);
}
