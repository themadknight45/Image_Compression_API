package com.pl_project.pl_project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pl_project.pl_project.entities.User_Information;

public interface UserInformationDao extends JpaRepository<User_Information, String>{
	
}
