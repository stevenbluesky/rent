package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.RepaireReasonService;

import com.rent.dao.RepaireReasonMapper;

import com.rent.entity.RepaireReason;
		  
@Service
@Transactional(readOnly = true)
public class RepaireReasonServiceImpl implements RepaireReasonService{
	@Autowired
	private RepaireReasonMapper repaireReasonMapper;
	
	
	public RepaireReasonMapper getRepaireReasonMapper() {
		return repaireReasonMapper;
	}
	public void setRepaireReasonMapper(RepaireReasonMapper repaireReasonMapper) {
		this.repaireReasonMapper = repaireReasonMapper;
	}
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	public int addRepaireReason(RepaireReason repaireReason){
		return repaireReasonMapper.insert(repaireReason);
	}
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	public int updateRepaireReason(RepaireReason repaireReason){
		return repaireReasonMapper.updateByPrimaryKey(repaireReason);
	}
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	public int delRepaireReason(Integer id){
		return repaireReasonMapper.deleteByPrimaryKey(id);
	}
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	public int delRepaireReason(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= repaireReasonMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
	}
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	public RepaireReason findById(Integer id){
		return repaireReasonMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	public List<RepaireReason> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return repaireReasonMapper.findAllPaged(begin, end);
	}
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	public Integer getTotalCount(){
		return repaireReasonMapper.getTotalCount();
	}
}
