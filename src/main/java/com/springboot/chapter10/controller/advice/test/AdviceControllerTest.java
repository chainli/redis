package com.springboot.chapter10.controller.advice.test;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.utils.DateUtils;


@Controller
@RequestMapping("/advice")
public class AdviceControllerTest {
	@GetMapping("/test")
	public String test(Date date,ModelMap modelMap) {
		System.out.println(modelMap.get("project_name"));
		System.out.println(DateUtils.dateToString(date,"yyyy-MM-dd"));
		throw new RuntimeException("异常了，跳转到控制器异常信息页面");
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}


