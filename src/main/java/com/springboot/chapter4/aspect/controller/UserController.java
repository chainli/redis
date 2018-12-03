package com.springboot.chapter4.aspect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redis.test.pojo.User;
import com.springboot.chapter4.aspect.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService = null;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id ,String userName, String note) {
    	User user = new User();
    	user.setId(id);
    	user.setUserName(userName);
    	user.setNote(note);
    	userService.printUser(user);
    	return user;
    }
}
