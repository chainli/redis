package com.redis.test.config;





import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import com.redis.test.pojo.User;

public class IocTest {
	//private static Logger log = Logger.getLogger(IocTest.class);
	public static void main (String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		User user = ctx.getBean(User.class);
		//log.info(user.getUserNmae());
	}

}
