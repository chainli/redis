package com.redis.test.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import com.redis.test.pojo.User;

@Configuration
//@ComponentScan("com.redis.test.config.*")
@ComponentScan(basePackageClasses = {User.class})
public class AppConfig {
	
	//@Bean(name = "user")
	 /* 
	public User initUser() {
		User user =new User();
		user.setId(1L);
		user.setNote("note_1");
		user.setUserNmae("user_name_1");
		return user;
		
	}
*/
}
