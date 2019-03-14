package com.rent.services.impl;

import com.rent.dao.UsersMapper;
import com.rent.entity.Role;
import com.rent.entity.Users;
import com.rent.services.EstateService;
import com.rent.services.RoleService;
import com.rent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private void setGuildProperty(Users user) {
		if (user.getEstateId()!=null) {
			user.setEstate(estateService.findById(user.getEstateId()));	
		}
		
		if (user.getRoles()!=null) {			
			String roles = user.getRoles();
			String[] split = roles.split("-");
			for (String id : split) {
				int i = Integer.parseInt(id);
				Role role = roleService.findById(i);
				user.getRolesList().add(role);

			}
		}


	}

	private void setGuildProperty(List<Users> users) {
		for (Users user : users) {
			user.setEstate(estateService.findById(user.getEstateId()));
			if (user.getRoles()!=null) {			
				String roles = user.getRoles();
				String[] split = roles.split("-");
				for (String id : split) {
					int i = Integer.parseInt(id);
					Role role = roleService.findById(i);
					user.getRolesList().add(role);
				}
			}

		}
	}

	@Autowired
	private EstateService estateService;

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UsersMapper getUsersMapper() {
		return usersMapper;
	}

	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	@Override
	public Users login(String name, String pwd) {
		Users user = new Users();
		user.setName(name);
		user.setPwd(pwd);
				
		Users u = usersMapper.findByUsers(user);
		
		System.out.println(u);
		if (u!=null) {
			
			setGuildProperty(u);
		}
		
		return u;
	}

	@Override
	public List<Users> findAll() {

		List<Users> users = usersMapper.findAll();
		
		for (Users user : users) {
			user.setEstate(estateService.findById(user.getEstateId()));
			if (user.getRoles()!=null) {			
				String roles = user.getRoles();
				String[] split = roles.split("-");
				for (String id : split) {
					int i = Integer.parseInt(id);
					Role role = roleService.findById(i);
					user.getRolesList().add(role);
				}
			}

		}
		return users;
	}

	@Override
	public void add(Users user) {
		usersMapper.insert(user);
		
	}

	@Override
	public void edit(Users user) {
		usersMapper.updateByPrimaryKey(user);
		
	}
	@Override
	public void edit(List<Users> users) {
		if (users!=null&&users.size()!=0) {
			for (Users u : users) {
				usersMapper.updateByPrimaryKey(u);
			}
		}
		
	}
	@Override
	public void del(Integer id) {
		usersMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Users findById(Integer id) {
		Users u = usersMapper.selectByPrimaryKey(id);
		setGuildProperty(u);
		return u;
		
	}

	@Override
	public void del(Integer[] id) {
		for (Integer i : id) {
			usersMapper.deleteByPrimaryKey(i);	
		}
		
	}

	@Override
	public List<Users> findByName(String name) {
		return usersMapper.findByName(name);
	}

	

}
