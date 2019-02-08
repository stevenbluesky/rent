package com.rent.services;

import java.util.List;

import com.rent.entity.Module;

public interface ModuleService {
	
	int add(Module module);
	
	int edit(Module module);
	
	int del(Integer id);
	
	Module findById(Integer id);
	
	List<Module> findByCondition(Module module);
	
	List<Module> findByConditionWithBtn(Module module);
	
	List<Module> findChildrenById(Integer pid);	
}
