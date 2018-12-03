package com.springboot.chapter5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.service.JdbcTmplUserService;

@Controller
@RequestMapping("/jdbc")
public class jdbcController {
	@Autowired
	JdbcTmplUserService jdbcTmplUserService = null;
	
	@GetMapping("/get")
    @ResponseBody
    public User getUser(Long id) {
	 return	jdbcTmplUserService.getUser(id);
	}
	
	/*
	@RequestMapping("/insert")
    @ResponseBody
    public User getUser() {
		User user = new User();
		user.setId(2L);
		user.setSex(2);
		user.setUserName("李伟");
		user.setNote("test");
	 return	jdbcTmplUserService.getUser(id);
	}
	*/

}
