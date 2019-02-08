package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.modules.sys.dao.RepaireDao;
import com.rent.modules.sys.entity.Repaire;
import com.rent.services.RepaireService;
@Service
@Transactional(readOnly = true)
public class RepaireServiceImpl implements RepaireService{
	@Autowired
	private RepaireDao repaireDao;
	public RepaireDao getRepaireDao() {
		return repaireDao;
	}
	public void setRepaireDao(RepaireDao repaireDao) {
		this.repaireDao = repaireDao;
	}


	@Override
	public List<Repaire> findByMaster(Integer masterId) {
		return repaireDao.findByMaster(masterId);
	}

}
