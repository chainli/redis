package com.springboot.chapter5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.chapter5.dao.MybatisUserDao;
import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.service.MybatisUserService;

@Service
public class MybatisUserServiceImpl implements MybatisUserService {
    
	@Autowired
	private MybatisUserDao myBatisUserDao = null; 
	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return myBatisUserDao.getUser(id);
	}
	
	@Override
	@Transactional
	public int insertUser(User user) {
		return myBatisUserDao.insertUser(user);
	}

}
