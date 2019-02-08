package com.rent.services;

import java.util.List;

import com.rent.entity.PrhPayment;

public interface PrhPayMentService {

		//新增
	 int insert(PrhPayment record);
	 
	 //多条新增
	 boolean insertMore(Integer[] chk);
	 
	 //根据主单id查找已付清的付款账单
	 
	 List<PrhPayment> findByAccntId(Integer accntId);

	List<PrhPayment> findByMasterIdAndHistory(Integer id);
	 
}
