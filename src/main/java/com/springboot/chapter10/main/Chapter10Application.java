package com.springboot.chapter10.main;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.chapter10.main.Chapter10Application;

@SpringBootApplication(scanBasePackages = "com.springboot.chapter10")
@MapperScan(basePackages = "com.springboot.chapter10")
//, annotationClass = Repository.class
public class Chapter10Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter10Application.class, args);
	
	}
	

}
