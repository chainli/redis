package com.springboot.chapter7.main;

import javax.annotation.PostConstruct;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

import com.springboot.chapter5.dao.MybatisUserDao;
import com.springboot.chapter5.main.Chapter5Application;



@SpringBootApplication(scanBasePackages = "com.springboot.chapter7")
@MapperScan(basePackages = "com.springboot.chapter7")
//@EntityScan(basePackages = "com.springboot.chapter5.pojo")
public class Chapter7Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter7Application.class, args);
	
	}
	
	@Autowired
	PlatformTransactionManager trnsactionManager  = null;
	
	@PostConstruct
	public void viewTransactionManager() {
		System.out.println(trnsactionManager.getClass().getName());
	}
	
	//@Autowired
	//private RedisTemplate redisTemplte = null;
	
	@Autowired
	private RedisConnectionFactory connectionFactory = null;
	
	@Autowired
	private MessageListener redisMsgListener = null;
	
	private ThreadPoolTaskScheduler taskScheduler = null;
	
	@Bean
	public ThreadPoolTaskScheduler initTaskScheduler() {
		if(taskScheduler != null) {
			return taskScheduler;
		}
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(20);
		return taskScheduler;
	}
	
	@Bean
	public RedisMessageListenerContainer initRedisContainer() {
		RedisMessageListenerContainer container =
				new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setTaskExecutor(initTaskScheduler());
		Topic topic = new ChannelTopic("topic1");
		container.addMessageListener(redisMsgListener, topic);
		return container;
	}

}
