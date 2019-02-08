package com.rent.modules.sys.dao;

import java.util.List;

import com.rent.common.persistence.CrudDao;
import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.modules.sys.entity.Tenement;

@MyBatisDao
public interface TenementDao extends CrudDao<Tenement> {

	public Tenement getTenementByName(String name);
	
	public List<Tenement> listAll();
}
