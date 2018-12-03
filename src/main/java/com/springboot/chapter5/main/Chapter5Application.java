package com.springboot.chapter5.main;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import com.springboot.chapter5.dao.MybatisUserDao;
import com.springboot.chapter5.main.Chapter5Application;



@SpringBootApplication(scanBasePackages = "com.springboot.chapter10")
@MapperScan("com.springboot.chapter5.dao")
@EntityScan(basePackages = "com.springboot.chapter5.pojo")
public class Chapter5Application {
	
	@Autowired
	SqlSessionFactory sqlSessionFactory = null;

	/*
	@Bean
	public MapperFactoryBean<MybatisUserDao> initMyBatisUserDao(){
		MapperFactoryBean<MybatisUserDao> bean = new MapperFactoryBean<>();
		//bean.setMapperInterface(MybatisUserDao.class);
		bean.setSqlSessionFactory(sqlSessionFactory);
		return bean;
	}
   */

	public static void main(String[] args) {
		SpringApplication.run(Chapter5Application.class, args);
	
	}
	
	@Autowired
	PlatformTransactionManager trnsactionManager  = null;
	
	@PostConstruct
	public void viewTransactionManager() {
		System.out.println(trnsactionManager.getClass().getName());
	}

}
