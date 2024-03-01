package com.empresa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByscreenName(String screenName);
}
