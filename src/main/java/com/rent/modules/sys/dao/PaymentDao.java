package com.rent.modules.sys.dao;

import java.util.List;

import com.rent.common.persistence.CrudDao;
import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.modules.sys.entity.Payment;

@MyBatisDao
public interface PaymentDao extends CrudDao<Payment>{

	public List<Payment> getRepaireItems(Payment rep);
	
	public void deleteByRepaireID(Payment rep);
}
