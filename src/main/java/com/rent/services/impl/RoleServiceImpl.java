package com.rent.services.impl;

import static org.hamcrest.CoreMatchers.both;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.RoleMapper;
import com.rent.entity.Module;
import com.rent.entity.Role;
import com.rent.entity.Users;
import com.rent.services.ModuleService;
import com.rent.services.RoleService;
import com.rent.services.UserService;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ModuleService moduleService;

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.findAll();
	}

	@Override
	public void add(Role role) {
		roleMapper.insert(role);

	}

	@Override
	public void edit(Role role) {
		roleMapper.updateByPrimaryKey(role);

	}

	@Override
	public Role findById(Integer id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		role.setModuleList(setModules(role));
		setRightsCountOfLevel1(role);
		return role;
	}

	@Override
	public List<Role> findByName(String name) {
		return roleMapper.findByName(name);
	}

	@Override
	public int delete(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delete(Integer[] ids) {
		// 查询角色是否被使用
		boolean isOnUse = false;
		for (Integer id : ids) {
			if (this.getOnUseNum(id) != 0) {
				isOnUse = true;
				break;
			}
		}
		// 是否正在使用
		if (isOnUse) {
			return -1;
		} else {
			if (ids != null && ids.length != 0) {
				for (Integer id : ids) {
					roleMapper.deleteByPrimaryKey(id);
				}
			}
		}

		return 1;
	}

	public Integer getOnUseNum(Integer id) {
		return roleMapper.getOnUseNum(id);
	}

	private List<Module> setModules(Role role) {
		List<Module> modulesList = new ArrayList<Module>();
		String modules = role.getModules();
		if (modules != null && modules.length() != 0) {
			String[] splits = modules.split("-");
			boolean isAdd=false;
			for (String s : splits) {
				Module module = moduleService.findById(Integer.parseInt(s));
				
				if (module!=null&& module.getPid()==0&&!isAdd) {
					role.setIsHouseManager(true);
					
					isAdd=true;
				}else if(module!=null&& module.getPid()!=0&&!isAdd){
					if (module!=null) {
						Module parent = moduleService.findById(module.getPid());
						if (parent!=null) {
							if (parent.getPid()==0&&!isAdd) {
								role.setIsHouseManager(true);
								isAdd=true;
							}
						}
					}
					 
				}
				
				modulesList.add(module);

			}
		}
		return modulesList;
	}

	// 设置数量
	private void setRightsCountOfLevel1(Role role) {
		Map<Integer, Module> modulesList = new HashMap<Integer, Module>();
		String modules = role.getModules();
		if (modules != null && modules.length() != 0) {
			String[] splits = modules.split("-");
			for (String s : splits) {
				Module module = moduleService.findById(Integer.parseInt(s));
				if (module!=null) {
					modulesList.put(module.getId(), module);	
				}
				
				// 判断
			}
		}

		// 遍历
		for (Integer mId : modulesList.keySet()) {

			Module module = modulesList.get(mId);
			// 二级菜单
			if (module.getLevels() == 2) {
				Module level1 = modulesList.get(module.getPid());
				if (level1 != null) {
					Integer count = level1.getRightsCountOfLevel1();
					level1.setRightsCountOfLevel1(count == null ? 1 : count + 1);
				}

				// 按钮菜单
			} else if (module.getLevels() == 3) {
				// 一级菜单
				Module level1 = modulesList.get(modulesList.get(module.getPid()).getPid());
				if (level1 != null) {
					level1.setRightsCountOfLevel1(
							level1.getRightsCountOfLevel1() == null ? 1 : level1.getRightsCountOfLevel1() + 1);
				}
			}
		}

		List<Module> ms = new ArrayList<Module>();

		for (Integer key : modulesList.keySet()) {
			ms.add(modulesList.get(key));
		}
		role.setModuleList(ms);

	}
}
