package com.rent.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import com.rent.common.utils.SpringContextHolder;
import com.rent.modules.sys.dao.MenuDao;
import com.rent.modules.sys.entity.Menu;

public class MenuUtils {

	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	
	public static List<Menu> getMenuList(){ 
		return menuDao.findAllList(new Menu());
	}
	
	public static List<Integer> getAllUnits(){
		List<Integer> units=new ArrayList<Integer>();
		for (int i = 1; i <=8; i++) {
			units.add(i);
		}
		return units;
	}
}
