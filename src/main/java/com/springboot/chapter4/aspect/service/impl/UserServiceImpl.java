package com.springboot.chapter4.aspect.service.impl;

import org.springframework.stereotype.Service;

import com.redis.test.pojo.User;
import com.springboot.chapter4.aspect.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public void printUser(User user) {
		if(user == null)
		{
			throw new RuntimeException("检查用户参数是否为空....");
		}
		System.out.println("id = "+ user.getId());
		System.out.println("\tusername = "+ user.getUserName());
		System.out.println("\tnote ="+ user.getNote());

	}

}
