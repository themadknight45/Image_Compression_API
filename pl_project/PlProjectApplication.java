package com.pl_project.pl_project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pl_project.pl_project.dao.UserDao;
import com.pl_project.pl_project.entities.User;

@SpringBootApplication
public class PlProjectApplication {
	public static void main(String[] args) {
	ConfigurableApplicationContext context=SpringApplication.run(PlProjectApplication.class, args);
		UserDao repo=context.getBean(UserDao.class);
//		User user=new User("Rishi Raja","Mypaswordnew");
//		User user1=repo.save(user);
//		user=new User("Goluye","Password2fcc");
//		User user2=repo.save(user);
//		System.out.println(user1);
//		System.out.println(user2);
	}
}
