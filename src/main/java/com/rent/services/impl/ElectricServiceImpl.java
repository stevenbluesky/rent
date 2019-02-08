package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.ElectricService;

import com.rent.dao.ElectricMapper;

import com.rent.entity.Electric;
@Service
@Transactional(readOnly = true)
public class ElectricServiceImpl implements ElectricService{
	
	@Autowired
	private ElectricMapper electricMapper;
	
	
	public ElectricMapper getElectricMapper() {
		return electricMapper;
	}
	public void setElectricMapper(ElectricMapper electricMapper) {
		this.electricMapper = electricMapper;
	}
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	public int addElectric(Electric electric){
		return electricMapper.insert(electric);
	}
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	public int updateElectric(Electric electric){
		return electricMapper.updateByPrimaryKey(electric);
	}
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	public int delElectric(Integer id){
		return electricMapper.deleteByPrimaryKey(id);
	}
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	public int delElectric(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= electricMapper.deleteByPrimaryKey(id);
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
	public Electric findById(Integer id){
		return electricMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	public List<Electric> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return electricMapper.findAllPaged(begin, end);
	}
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	public Integer getTotalCount(){
		return electricMapper.getTotalCount();
	}
}
