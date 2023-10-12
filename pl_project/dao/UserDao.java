package com.pl_project.pl_project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pl_project.pl_project.entities.User;

public interface UserDao extends JpaRepository<User, String>{

}
