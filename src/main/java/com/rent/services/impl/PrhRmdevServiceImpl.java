package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.condition.RmdevCondition;
import com.rent.dao.PrhRmdevMapper;
import com.rent.entity.Building;
import com.rent.entity.PrhRmdev;
import com.rent.services.PrhRmdevService;

@Service
@Transactional(readOnly = true)
public class PrhRmdevServiceImpl implements PrhRmdevService {
	@Autowired
	private PrhRmdevMapper prhRmdevMapper;

	@Override
	public List<PrhRmdev> findById(Integer houseid) {
		
		return prhRmdevMapper.findById(houseid);
	}
		
	@Override
	public List<PrhRmdev> findByConditionPaged(RmdevCondition condition, Integer currage, Integer size) {
		int begin = (currage - 1) * size + 1;
		int end = begin + size - 1;
		condition.setEnd(end);
		condition.setBegin(begin);
		List<PrhRmdev> PrhRmdev = prhRmdevMapper.findByConditionPaged(condition);
			
		return PrhRmdev;
	}

	@Override
	public Integer insert(PrhRmdev prhRmdev) {
		
		return prhRmdevMapper.insert(prhRmdev);
	}

	@Override
	public int updateStateByid(Integer id) {
		
		return prhRmdevMapper.updateStateByid(id);
	}

	@Override
	public Integer findMaxID() {
		
		return prhRmdevMapper.findMaxID();
	}
	
}
