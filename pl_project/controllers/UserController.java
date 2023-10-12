package com.pl_project.pl_project.controllers;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.pl_project.pl_project.payload.UserResponse;
import com.pl_project.pl_project.services.FileService;
import com.pl_project.pl_project.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ResponseEntity<UserResponse>login(@RequestParam("username")String username,@RequestParam("password")String password){
		UserResponse userResponse=new UserResponse();
		userResponse = userService.login(username, password);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse>register(@RequestParam("username")String username,@RequestParam("password")String password){
		UserResponse userResponse=new UserResponse();
		userResponse = userService.register(username, password);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
}
