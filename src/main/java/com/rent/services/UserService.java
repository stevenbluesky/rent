package com.rent.services;

import java.util.List;

import com.rent.entity.Users;

public interface UserService {
	//新增
	void add(Users user);
	//修改
	void edit(Users user);
	
	//修改
	void edit(List<Users> users);
	
	//删除
	void del(Integer id);
	
	void del(Integer[] id);
	//通过id查找
	Users findById(Integer id);
	
	Users login(String name,String pwd);
	
	List<Users> findAll();
	List<Users> findByName(String name);
}
