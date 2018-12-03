package com.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
/*
public class RedisApplicationTests {

	@Test
	public void contextLoads() {
	}
*/	

	//@SpringBootTest(classes = Application.class)
	public class RedisApplicationTests {
	
	public void contextLoads() {
	}
	    
	    @Autowired
	    RedisComponent redisComponent;
	        
	    @SuppressWarnings("deprecation")
		@Test
	    public void testRedis(){
	    	//redisComponent.set("hello2","world2");
	    	Assert.assertEquals(redisComponent.get("hello2"),"world2");
	    	
	    	
	       // conn.set("hello".getBytes(), "world".getBytes());
	       // System.out.println(get("hello".getBytes())));
	    }

	}


