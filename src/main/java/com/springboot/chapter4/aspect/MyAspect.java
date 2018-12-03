package com.springboot.chapter4.aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {
  
	@Pointcut("execution(* com.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
	public void pointCut() {
		
	}
	
	@Before("Pointcut()")
	public void before() {
		System.out.println("before....");
	}
	
	@After("Pointcut()")
	public void after() {
		System.out.println("after....");
	}
	
	@AfterReturning("Pointcut()")
	public void afterReturning() {
		System.out.println("afterReturning....");
	}
	
	@AfterThrowing("Pointcut()")
	public void afterThrowing() {
		System.out.println("afterThrowing....");
	}
	
}
