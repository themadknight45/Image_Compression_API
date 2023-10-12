package com.pl_project.pl_project.services;

import java.io.File;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pl_project.pl_project.dao.UserDao;
import com.pl_project.pl_project.dao.UserInformationDao;
import com.pl_project.pl_project.entities.User;
import com.pl_project.pl_project.payload.UserResponse;

@Service
public class UserServiceImpl implements UserService{
	@Value("${project.image}")
	private String path;
	@Value("${project.token}")
	private int Token;
	@Autowired
	private UserDao dao;
	@Autowired 
	private UserInformationDao informatiodao;
	
	@Override
	public UserResponse login(String username, String password) {
		UserResponse userResponse=new UserResponse();
		boolean exists=dao.existsById(username);
		if(!exists) {
			userResponse.setLoginMessage("Please Register & Come Again");
			return userResponse;
		}
		Optional<User>optional=dao.findById(username);
		User existing_User=optional.get();
		
		if(password.equals(existing_User.getPassword())) {
			userResponse.setLoginMessage("Welcome "+username);
			userResponse.setLoginStatus("Login Sucessful");
			userResponse.setToken(Token);
		}
		else {
			userResponse.setLoginMessage("Invalid password");
		}
		return userResponse;
	}
	
	@Override
	public UserResponse register(String username, String password) {
		User  user=new User(username,password);
		UserResponse userResponse=new UserResponse();
		boolean exists=dao.existsById(username);
		if(exists) {
			Optional<User>optional=dao.findById(username);
			User existing_User=optional.get();
			if(password.equals( existing_User.getPassword())) {
				userResponse.setLoginMessage("Already registered. Please login");
			}
			else {
				userResponse.setLoginMessage("Username not Available.Please try a different username");
			}
		}
		else {
			File f=new File(path+"//"+username);
			if(!f.exists())
				f.mkdir();
			dao.save(user);
			userResponse.setLoginMessage("Welcome "+username);
			userResponse.setLoginStatus("Login Sucessful");
			userResponse.setToken(Token);
		}
		return userResponse;
	}
}
