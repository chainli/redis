package com.springboot.chapter5.dao;

import org.springframework.stereotype.Repository;

import com.springboot.chapter5.pojo.User;

@Repository
public interface MybatisUserDao {
   public User getUser(Long id);
   public int insertUser(User user);
}
