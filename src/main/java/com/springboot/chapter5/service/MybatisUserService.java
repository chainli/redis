package com.springboot.chapter5.service;

import com.springboot.chapter5.pojo.User;

public interface MybatisUserService {
    public User getUser(Long id);
    public int insertUser(User user);
}
