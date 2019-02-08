package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.dao.PrhPaycodeMapper;
import com.rent.entity.PrhPaycode;
import com.rent.services.PrhPayCodeService;
@MyBatisDao
public class PrhPayCodeServiceImpl implements PrhPayCodeService {

	@Autowired
	private PrhPaycodeMapper prhPaycodeMapper;
	@Override
	
	public List<PrhPaycode> findAllPayCode() {
			
		return prhPaycodeMapper.findAllPayCode();
	}

}
