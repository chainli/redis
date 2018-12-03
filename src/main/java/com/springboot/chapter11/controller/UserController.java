package com.springboot.chapter11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.chapter11.pojo.User;
import com.springboot.chapter11.vo.UserVo;
import com.springboot.chapter4.aspect.service.UserService;
import com.springboot.chapter5.enumeration.SexEnum;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService = null;
	
	@GetMapping("restful")
	public String index() {
		return "restful";
	}
	
	private User changeToPo(UserVo userVo) {
		User user = new User();
		user.setId(userVo.getId());	
		user.setUserName(userVo.getUserName());
		user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
		user.setNote(userVo.getNote());
		return user;
	}
	
	private UserVo changeToVo(User user) {
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());	
		userVo.setUserName(user.getUserName());
		userVo.setSexCode(user.getSex().getId());
		userVo.setSexName(user.getSex().getName());
		userVo.setNote(user.getNote());
		return userVo;
	}
	
	private List<UserVo> changeToVoes(List<User> poList ){
		List<UserVo> voList = new ArrayList<>();
		for(User user:poList) {
			UserVo userVo = this.changeToVo(user);
			voList.add(userVo);
		}
		return voList;
	}
}	
	public class ResultVo{
	    private Boolean success = true;
	    public Boolean getSuccess() {
			return success;
		}

		public void setSuccess(Boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		private String message = null;
	    
	    public ResultVo() {
	    	
	    }
	    
	    public ResultVo(Boolean success,String message) {
	    	this.success = success;
	    	this.message = message;
	    }
	}


