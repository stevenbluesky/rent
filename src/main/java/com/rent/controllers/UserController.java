package com.rent.controllers;

import com.rent.common.config.Global;
import com.rent.common.utils.Md5Utils;
import com.rent.common.utils.MyConvertUtil;
import com.rent.entity.Estate;
import com.rent.entity.Module;
import com.rent.entity.Role;
import com.rent.entity.Users;
import com.rent.modules.sys.service.SystemService;
import com.rent.services.EstateService;
import com.rent.services.ModuleService;
import com.rent.services.RoleService;
import com.rent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller("userController")
public class UserController {

	@Autowired
	private EstateService estateService;

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ModuleService moduleService;

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Autowired
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	// 转到登录页
	@RequestMapping("toWelcome.do")
	public String toWelcome(Integer state, ModelMap map, HttpSession session) {
		return "sys/welcome.jsp";
	}
		
		
	// 转到登录页
	@RequestMapping("toLogin")
	public String toLogin(Integer state, ModelMap map, HttpSession session) {
		/*
		 * state： 0 正常进入登录页 1 session过期 或者url强行绕过登录 2 退出
		 * 
		 * 
		 */
		if (state == null) {
			state = 0;
		}

		if (state == 1) {
			map.put("tip", "请先登录");
		} else if (state == 2) {
			session.removeAttribute(Global.USER);
		}
		map.put("isLogin", "1");
		return "sys/login.jsp";
	}

	// 登录
	@RequestMapping("login")
	@ResponseBody
	public String login(String name, String pwd, ModelMap map, HttpSession session) {
		Users user = userService.login(name, Md5Utils.toMD5(pwd));

		if (user != null) {
			// 存session
			session.setAttribute(Global.USER, user);
			return "1";
		} else {
			map.put("tip", "用户名或密码错误");
			return "0";
		}
	}

	// 转到选择页面
	@RequestMapping("toChooseManager")
	public String toChooseManager() {
		return "sys/chooseManager.jsp";
	}

	// 转到参数列表
	@RequestMapping("toParamIndex")
	public String toParamIndex(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("df");
		return "sys/paramIndex.jsp";
	}

	// 转到模块管理
	@RequestMapping("toModuleList.do")
	public String toModuleList(Integer tabNo, Integer menuLevel, Integer level1, ModelMap map) {
		if (tabNo == null) {
			tabNo = 1;
		}
		/*
		 * if (menuLevel != null && menuLevel == -1) { menuLevel = null; }
		 */
		if (menuLevel == null) {
			menuLevel = -1;
		}
		if (level1 != null && level1 == -1) {
			level1 = null;
		}
		// 菜单列表
		Module module = new Module(null, null, null, level1, menuLevel);

		List<Module> modules = moduleService.findByCondition(module);

		// 一级菜单集合（下拉框）
		List<Module> level1Modules = moduleService.findByCondition(new Module(null, null, null, null, 1));
		map.put("level1Modules", level1Modules);
		map.put("modules", modules);
		map.put("level1", level1); // 当前一级菜单
		map.put("menuLevel", menuLevel); // 当前菜单等级
		map.put("tabNo", tabNo); // 当前选项卡

		return "sys/module/module.jsp";
	}

	// 转到模块管理
	@RequestMapping("toModuleBtn.do")
	public String toModuleBtn(Integer tabNo, Integer level1, Integer level2, String op, ModelMap map) {

		if (tabNo == null) {
			tabNo = 2;
		}

		if (level2 != null && level2 == -1) {
			level2 = -100;
		}

		// 一级菜单集合（下拉框）
		List<Module> level1Modules = moduleService.findByCondition(new Module(null, null, null, null, 1));
		if (level1 == null && (level1Modules != null && level1Modules.size() != 0)) {
			level1 = level1Modules.get(0).getId();
		}
		// 二级级菜单集合（下拉框）
		List<Module> level2Modules = moduleService.findByCondition(new Module(null, null, null, level1, 2));
		if (level2 == null && (level2Modules != null && level2Modules.size() != 0)) {
			if (!"dropdown".equals(op)) {
				level2 = level2Modules.get(0).getId();
			}
		}
		// 菜单列表
		Module module = new Module(null, null, null, level2, 3);

		List<Module> modules = moduleService.findByCondition(module);

		// 菜单列表
		map.put("modules", modules);
		// 一级菜单
		map.put("level1Modules", level1Modules);
		map.put("level1", level1);
		// 二级菜单
		map.put("level2Modules", level2Modules);
		map.put("level2", level2);

		map.put("tabNo", tabNo); // 当前选项卡

		return "sys/module/module.jsp";
	}

	// 转到角色管理
	@RequestMapping("toRoleList.do")
	public String toRoleList(Integer tabNo, ModelMap map) {
		// 角色集合
		List<Role> roles = roleService.findAll();
		map.put("roles", roles);

		return "sys/role/role.jsp";
	}

	// 转到新增管理
	@RequestMapping("toRoleAdd.do")
	public String toRoleAdd(Integer level1, String name, String modulesIds, Integer moduleNum, Integer[] level1Counts,
			ModelMap map) throws UnsupportedEncodingException {

/*		if (name != null) {
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
		}*/
		if (moduleNum == null) {
			moduleNum = 0;
		}
		List<String> modulesIdList = null;
		if (modulesIds != null && modulesIds.length() != 0) {
			modulesIdList = new ArrayList<String>(Arrays.asList(modulesIds.split("-")));
		}

		// 一级菜单集合
		List<Module> level1Modules = moduleService.findByCondition(new Module(null, null, null, null, 1));
		if (level1 == null && (level1Modules != null && level1Modules.size() != 0)) {
			level1 = level1Modules.get(0).getId();
		}
		// 二级级菜单集合（下拉框）

		List<Module> level2Modules = moduleService.findByConditionWithBtn(new Module(null, null, null, level1, 2));
		// 一级菜单id
		map.put("level1", level1);
		// 一级二级菜单
		map.put("level1Modules", level1Modules);
		map.put("level2Modules", level2Modules);
		// 文本框
		map.put("name", name);
		map.put("modulesIds", modulesIds);
		map.put("modulesIdList", modulesIdList);
		map.put("moduleNum", moduleNum);
		map.put("level1Counts", level1Counts);
		return "sys/role/roleAdd.jsp";
	}

	// 新增角色
	@RequestMapping("addRole.do")
	@ResponseBody
	public String addRole(String name, String modulesIds, ModelMap map) {

		if (roleService.findByName(name) != null && roleService.findByName(name).size() != 0) {

			return "0";
		}
		
		//模块数组
				String[] array = modulesIds.split("-");
				String[] splits = array;
				
				
				Map<Integer, Boolean> isAdd = new HashMap<Integer, Boolean>();//存一级菜单是否新增过
				
				for (String s : splits) {
					//遍历查询每一个模块
					Module module = moduleService.findById(Integer.valueOf(s));
					//二级菜单
					if (module.getLevels() == 2) {
						// 是否存在
						if (isAdd.get(module.getPid()) == null || isAdd.get(module.getPid()) == false) {
							modulesIds += ("-" + module.getPid());
							isAdd.put(module.getPid(), true);
						}
					}
				}
				
			
				
				
				//去除重复数据
				array= modulesIds.split("-");
				array = MyConvertUtil.distinctArray(array);
				
				List<String> notchooseLevel1=new ArrayList<String>();
				
				//遍历所有模块，删除多余的一级菜单
				for (String s : array) {
					//遍历查询每一个模块
					Module module = moduleService.findById(Integer.valueOf(s));
					if (module.getLevels()==1) {
						//遍历选中的一级菜单
						boolean con = isAdd.containsKey(Integer.valueOf(s));
						if (!con) {
							notchooseLevel1.add(s);
							System.out.println("没选中的"+s);
						}
					}
				}
				
				
				for (String string : notchooseLevel1) {
					System.out.print(" "+string+"    ");
					array = MyConvertUtil.delArrayValues(array, string);
				}
				modulesIds=MyConvertUtil.arrayToString(array, "-");
		
		Role role = new Role(null, name, modulesIds);
		roleService.add(role);
		System.out.println("新增成功");

		return "1";
	}

	// 用户管理页面
	@RequestMapping("getUserList.do")
	public String getUserList(ModelMap map) {
		List<Users> users = userService.findAll();
		map.put("users", users);
		return "sys/users/user.jsp";
	}

	// 转到用户新增
	@RequestMapping("toUserAdd.do")
	public String toUserAdd(ModelMap map) {
		List<Estate> estates = estateService.findAll();
		map.put("estates", estates);
		List<Role> roles = roleService.findAll();
		map.put("roles", roles);

		return "sys/users/userAdd.jsp";
	}

	// 转到用户修改
	@RequestMapping("toUserEdit.do")
	public String toUserEdit(Integer userId, ModelMap map) {
		// 物业集合
		List<Estate> estates = estateService.findAll();
		map.put("estates", estates);
		// 角色集合
		List<Role> roles = roleService.findAll();
		map.put("roles", roles);

		// 用户信息
		Users u = userService.findById(userId);

		map.put("u", u);
		return "sys/users/userEdit.jsp";
	}

	// 用户新增
	@RequestMapping("userAdd.do")
	@ResponseBody
	public String userAdd(Users user, Integer[] roleIds, ModelMap map) {
		
		if (user.getEstateId()==-1) {
			user.setEstateId(null);
		}
		List<Users> findByName = userService.findByName(user.getName());
		System.out.println(findByName);
		if (findByName!=null&&findByName.size()!=0) {
			return "0";
		}
		
		
		String roleStr = "";
		user.setPwd(Md5Utils.toMD5(user.getPwd()));
		for (int i = 0; i < roleIds.length; i++) {
			if (i == 0) {
				roleStr = roleIds[i].toString();
			} else {
				roleStr += "-" + roleIds[i].toString();
			}
		}
		user.setRoles(roleStr);
		user.setDel(0);
		userService.add(user);
		return "1";
	}

	// 用户修改
	@RequestMapping("userEdit.do")
	@ResponseBody
	public String userEdit(Users user, Integer[] roleIds, ModelMap map) {
		if (user.getEstateId()==-1) {
			user.setEstateId(null);
		}
		Users oldUser = userService.findById(user.getId());
		if (user.getPwd() == null || user.getPwd().length() == 0) {
			user.setPwd(oldUser.getPwd());
		}

		String roleStr = "";
		user.setPwd(Md5Utils.toMD5(user.getPwd()));
		for (int i = 0; i < roleIds.length; i++) {
			if (i == 0) {
				roleStr = roleIds[i].toString();
			} else {
				roleStr += "-" + roleIds[i].toString();
			}
		}
		user.setRoles(roleStr);
		user.setDel(0);
		userService.edit(user);
		return "1";
	}

	// 删除角色
	@RequestMapping("delRole.do")
	public String delRole(Integer[] chk, ModelMap map) {
		
		
		int result = roleService.delete(chk);
		if (result == -1) {
			map.put("tip", "角色已经被使用，不能删除！");
		} else {
			map.put("tip", "删除成功！");
		}
		return "../toRoleList.do";
	}

	// 删除用户
	@RequestMapping("delUser.do")
	public String delUser(Integer[] chk, ModelMap map) {
		List<Users> users=new ArrayList<Users>();
		for (Integer i : chk) {
			Users u = userService.findById(i);
			u.setDel(1);
			users.add(u);
		}
		userService.edit(users);
		return "../getUserList.do";
	}

	// 转到修改角色
	@RequestMapping("toRoleEdit.do")
	public String toRoleEdit(Integer roleId, Integer level1, String name, String modulesIds, Integer moduleNum,
			Integer[] level1Counts, Integer isFirst, ModelMap map) throws UnsupportedEncodingException {

		if (name != null) {
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
		}
		if (moduleNum == null) {
			moduleNum = 0;
		}
		List<String> modulesIdList = null;
		if (modulesIds != null && modulesIds.length() != 0) {
			modulesIdList = new ArrayList<String>(Arrays.asList(modulesIds.split("-")));
		}

		// 一级菜单集合
		List<Module> level1Modules = moduleService.findByCondition(new Module(null, null, null, null, 1));
		if (level1 == null && (level1Modules != null && level1Modules.size() != 0)) {
			level1 = level1Modules.get(0).getId();
		}
		// 二级级菜单集合（下拉框）

		List<Module> level2Modules = moduleService.findByConditionWithBtn(new Module(null, null, null, level1, 2));

		if (roleId != null) {
			Role role = roleService.findById(roleId);
			if ((modulesIdList == null || modulesIdList.size() == 0) && role.getModuleList() != null
					&& role.getModuleList().size() != 0) {
				modulesIdList = new ArrayList<String>();
				List<Module> list = role.getModuleList();
				for (Module module : list) {
					modulesIdList.add(module.getId().toString());
				}
			}
			moduleNum = role.getModuleList().size();
			map.put("role", role);

		}

		// 一级菜单id
		map.put("level1", level1);
		// 一级二级菜单
		map.put("level1Modules", level1Modules);
		map.put("level2Modules", level2Modules);
		// 文本框
		map.put("name", name);
		map.put("modulesIds", modulesIds);
		map.put("modulesIdList", modulesIdList);
		map.put("moduleNum", moduleNum);
		map.put("level1Counts", level1Counts);
		map.put("isFirst", isFirst);
		return "sys/role/roleEdit.jsp";
	}

	// 修改角色
	@RequestMapping("editRole.do")
	@ResponseBody
	public String editRole(Integer id, String name, String modulesIds, ModelMap map) {
		
		Role oldRole = roleService.findById(id);//当前角色
		//角色名重复
		if (roleService.findByName(name) != null && roleService.findByName(name).size() != 0
				&& !oldRole.getName().equals(name)) {
			return "0";
		}
		//模块数组
		String[] array = modulesIds.split("-");
		String[] splits = array;
		
		
		Map<Integer, Boolean> isAdd = new HashMap<Integer, Boolean>();//存一级菜单是否新增过
		
		for (String s : splits) {
			//遍历查询每一个模块
			Module module = moduleService.findById(Integer.valueOf(s));
			//二级菜单
			if (module.getLevels() == 2) {
				// 是否存在
				if (isAdd.get(module.getPid()) == null || isAdd.get(module.getPid()) == false) {
					modulesIds += ("-" + module.getPid());
					isAdd.put(module.getPid(), true);
				}
			}
		}
		
	
		
		
		//去除重复数据
		array= modulesIds.split("-");
		array = MyConvertUtil.distinctArray(array);
		
		List<String> notchooseLevel1=new ArrayList<String>();
		
		//遍历所有模块，删除多余的一级菜单
		for (String s : array) {
			//遍历查询每一个模块
			Module module = moduleService.findById(Integer.valueOf(s));
			if (module.getLevels()==1) {
				//遍历选中的一级菜单
				boolean con = isAdd.containsKey(Integer.valueOf(s));
				if (!con) {
					notchooseLevel1.add(s);
					System.out.println("没选中的"+s);
				}
			}
		}
		
		
		for (String string : notchooseLevel1) {
			System.out.print(" "+string+"    ");
			array = MyConvertUtil.delArrayValues(array, string);
		}
		modulesIds=MyConvertUtil.arrayToString(array, "-");
		//修改
		Role role = new Role(id, name, modulesIds);
		roleService.edit(role);
		

		return "1";
	}
	
	
	//转到修改密码
	@RequestMapping("toEditPwd.do")
	public String toEditPwd(){
		return "sys/editPwd.jsp";
	}
	//修改密码
		@RequestMapping("editPwd.do")
		@ResponseBody
		public String editPwd(String oldPwd,String newPwd,String reNewPwd,HttpSession session){
			System.out.println(oldPwd);
			Users user= (Users)session.getAttribute(Global.USER);
			System.out.println(user.getName());
			if (user!=null) {
				Users users = userService.login(user.getName(),Md5Utils.toMD5(oldPwd));
				if (users!=null) {
					users.setPwd(Md5Utils.toMD5(newPwd));
					userService.edit(users);
					return "1";
				}else{
					return "0";
				}
			}
			
			return "0";	
		}
		
	
}
