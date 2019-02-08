package com.rent.services;

import java.util.List;

import com.rent.entity.PaymentCode;

public interface PaymentCodeService {
	
	int addPaymentCode(PaymentCode paymentCode);
	
	int updatePaymentCode(PaymentCode paymentCode);
	
	
	int delPaymentCode(Integer id);
	

	
	int delPaymentCode(Integer[] ids);
	
	PaymentCode findById(Integer id);
	
	
	List<PaymentCode> findAllPaged(Integer currpage,Integer size);
	
	
	Integer getTotalCount();
	
	
	 
}
