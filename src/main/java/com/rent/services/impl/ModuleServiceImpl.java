package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.ModuleMapper;
import com.rent.entity.Module;
import com.rent.services.ModuleService;
@Service
@Transactional(readOnly = true)
public class ModuleServiceImpl implements ModuleService{

	@Autowired
	private ModuleMapper moduleMapper;
	

	public ModuleMapper getModuleMapper() {
		return moduleMapper;
	}
	
	private void setGuildProperty(Module module){
		if (module.getPid()!=null) {
			 module.setParentModule(moduleMapper.selectByPrimaryKey(module.getPid()));
			
		 }
	}
	private void setGuildProperty(List<Module> modules){
		for (Module module : modules) {
			if (module.getPid()!=null) {
				 module.setParentModule(moduleMapper.selectByPrimaryKey(module.getPid()));
			}	
		}
	}

	public void setModuleMapper(ModuleMapper moduleMapper) {
		this.moduleMapper = moduleMapper;
	}
	//������ѯ
	@Override
	public List<Module> findByCondition(Module module) {
		 List<Module> findByCondition = moduleMapper.findByCondition(module);
		 setGuildProperty(findByCondition);
		 return findByCondition;
	}
	
	//������ѯ������ť
	@Override
	public List<Module> findByConditionWithBtn(Module module) {
		//������ѯ
		 List<Module> modules = moduleMapper.findByCondition(module);
			for (Module m : modules) {
				if (m.getPid()!=null) {
					 m.setParentModule(moduleMapper.selectByPrimaryKey(module.getPid()));
				}	
				//��Ӱ�ť����
				if (m.getLevels()==2) {
					List<Module> btns = moduleMapper.findByCondition(new Module(null, null, null, m.getId(), 3));
					m.getBtnModules().addAll(btns);
				}
			}
		 return modules;
	}

	@Override
	public int add(Module module) {
		return moduleMapper.insert(module);
	}

	@Override
	public int edit(Module module) {
		return moduleMapper.updateByPrimaryKey(module);
	}

	@Override
	public int del(Integer id) {
		return moduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Module findById(Integer id) {
		return moduleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Module> findChildrenById(Integer pid) {
		return moduleMapper.findChildrenById(pid);
	}

}
