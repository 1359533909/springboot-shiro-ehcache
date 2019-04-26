package com.mxl.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxl.demo.entity.User;
import com.mxl.demo.mapper.UserMapper;
import com.mxl.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl"+name);
		User user = userMapper.findUserByName(name);
		return user;
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		User user = userMapper.findUserById(id);
		return user;
	}

}
