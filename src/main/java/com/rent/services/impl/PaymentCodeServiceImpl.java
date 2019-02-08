package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.services.PaymentCodeService;

import com.rent.dao.PaymentCodeMapper;

import com.rent.entity.PaymentCode;
@Service
@Transactional(readOnly = true)
public class PaymentCodeServiceImpl implements PaymentCodeService{
	@Autowired
	private PaymentCodeMapper paymentCodeMapper;
	public PaymentCodeMapper getPaymentCodeMapper() {
		return paymentCodeMapper;
	}
	public void setPaymentCodeMapper(PaymentCodeMapper paymentCodeMapper) {
		this.paymentCodeMapper = paymentCodeMapper;
	}
	
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	public int addPaymentCode(PaymentCode paymentCode){
		return paymentCodeMapper.insert(paymentCode);
	}
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	public int updatePaymentCode(PaymentCode paymentCode){
		System.out.println("testtest");
		return paymentCodeMapper.updateByPrimaryKey(paymentCode);
	}
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	public int delPaymentCode(Integer id){
		return paymentCodeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	public int delPaymentCode(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= paymentCodeMapper.deleteByPrimaryKey(id);
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
	public PaymentCode findById(Integer id){
		return paymentCodeMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	public List<PaymentCode> findAllPaged(Integer currpage,Integer size){
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		return paymentCodeMapper.findAllPaged(begin, end);
	}
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	public Integer getTotalCount(){
		return paymentCodeMapper.getTotalCount(); 
		
	}
}
