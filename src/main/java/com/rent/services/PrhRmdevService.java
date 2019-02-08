package com.rent.services;

import java.util.List;

import com.rent.condition.RmdevCondition;
import com.rent.entity.Building;
import com.rent.entity.PrhRmdev;

public interface PrhRmdevService {

	/**
	 * ����id�����豸��Ϣ
	 * @return 
	 */
	List<PrhRmdev> findById(Integer houseid);
	
	/**
     * ������ҵid��ҳ��ѯȫ���豸��Ϣ
     * @return �豸
     */
	List<PrhRmdev> findByConditionPaged(RmdevCondition condition,Integer currage,Integer size);
	
	
	Integer insert(PrhRmdev prhRmdev);
	
	 int updateStateByid(Integer id);
	 
	 Integer findMaxID();
}
