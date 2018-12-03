package com.springboot.chapter5.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.service.MybatisUserService;

@Controller
@RequestMapping("/mybatis")
public class MyBatisController {
	
	@Autowired
	private MybatisUserService myBatisUserService = null;
	
	@RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
		return myBatisUserService.getUser(id);
	}
	
	@RequestMapping("/insertUser")
    @ResponseBody
    public Map<String ,Object> insertUser(String userName,String note) {
		User user = new User();
		user.setUserName(userName);
		user.setNote(note);
		int update = myBatisUserService.insertUser(user);
		Map<String ,Object> result = new HashMap();
		result.put("success", update == 1);
		result.put("user", user);
		return result;
	}
}
