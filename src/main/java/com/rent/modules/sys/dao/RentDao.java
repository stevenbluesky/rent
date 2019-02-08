package com.rent.modules.sys.dao;

import java.util.List;

import com.rent.common.persistence.CrudDao;
import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.modules.sys.entity.Rent;

@MyBatisDao
public interface RentDao extends CrudDao<Rent>{

	public List<Rent> listAll();
	
	public List<Rent> search(Rent rent);
	
	public List<Rent> getRentsByTenement(Rent rent);
	
}
