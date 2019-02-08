package com.rent.services;

import java.util.List;

import com.rent.entity.Role;

public interface RoleService {
	
	void add(Role role);
	void edit(Role role);
	
	
	
	int delete(Integer id);
	
	int delete(Integer [] id);
	
	Role findById(Integer id);
	
	List<Role> findAll();
	
	List<Role> findByName(String name);
	
	Integer getOnUseNum(Integer id);
	
}
