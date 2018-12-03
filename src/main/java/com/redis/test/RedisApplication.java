package com.redis.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@SpringBootApplication

public class RedisApplication {
	
	@Autowired
    RedisComponent redisComponent;
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String,String> test(){
		//@Autowired
	    //RedisComponent redisComponent = new RedisComponent();
		Map<String,String> map = new HashMap<String,String>();
		redisComponent.set("world3",Double.toString(Math.random()));
		System.out.println(redisComponent.get("world3"));
		map.put("hello", redisComponent.get("world3"));
		return map;
	}
	
	public static void main(String[] args) {
	
		
		SpringApplication.run(RedisApplication.class, args);
		//redisComponent.set("hello1","world1");
    	//System.out.println(redisComponent.get("hello1"));
	}
	
	/*
	@Configuration
	public class RedisConfig {
	    @Bean
	    public RedisConnectionFactory redisCF(){
	        //如果什么参数都不设置，默认连接本地6379端口
	        JedisConnectionFactory factory = new JedisConnectionFactory();
	        
	       // factory.setPort(6379);
	       // factory.setHostName("localhost");
	        return factory;
	    }
	   */ 
	    /*
	    @Bean
	    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
	        //创建一个模板类
	        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	        //将刚才的redis连接工厂设置到模板类中
	        template.setConnectionFactory(factory);
	        return template;
	    }
	    */
	




}
