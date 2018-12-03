package com.springboot.chapter5.service;

import java.util.List;

import com.springboot.chapter5.pojo.User;

public interface JdbcTmplUserService {

	 public User getUser(Long id);
	 public List<User>fidnUsers(String userName ,String note);
	 public int insertUser(User user);
	 public int updateUser(User user);
	 public int deleteUser(Long id);
	 
}
