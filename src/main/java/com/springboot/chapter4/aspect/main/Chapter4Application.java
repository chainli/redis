package com.springboot.chapter4.aspect.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.springboot.chapter4.aspect.MyAspect;


@SpringBootApplication(scanBasePackages = {"com.springboot.chapter4.aspect"})

public class Chapter4Application {

	@Bean(name = "myAspect")
	public MyAspect initMyAspect() {
		return new MyAspect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter4Application.class, args);
	
	}
}
