package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.PrhPaymentMapper;
import com.rent.dao.PrhRentalMapper;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhPayment;
import com.rent.entity.PrhRental;
import com.rent.services.PrhPayMentService;
import com.rent.services.RenterService;
@Service
@Transactional(readOnly = true)
public class PrhPayMentServiceImpl implements PrhPayMentService {

	@Autowired
	private PrhPaymentMapper prhPaymentMapper;
	@Autowired
	private PrhRentalMapper prhRentalMapper;
	@Autowired
	private RenterService renterService;
	
	@Override
	public int insert(PrhPayment record) {
		// TODO Auto-generated method stub
		return prhPaymentMapper.insert(record);
	}
	@Override
	public boolean insertMore(Integer[] chk) {
		try {
			
			for (Integer integer : chk) {
				//閺嶈宓乮d閺屻儴顕楅張顏冪帛濞撳懐娈戦梿鍡楁値 娣囶喗鏁奸悩鑸碉拷锟�
				PrhRental rental = prhRentalMapper.findById(integer);
				
				PrhPayment ment = new PrhPayment();
				ment.setPrtno(rental.getId().toString());
				
			}
		} catch (Exception e) {
				e.printStackTrace();
				return false;
		}
			
		
		return true;
	}
	
	private void setGuideProperty(List<PrhPayment> prhPayment) {
		for (PrhPayment e : prhPayment) {

			PrhRental type = renterService.findById(Integer.valueOf(e.getPrtno()));
			e.setRentals(type);
		}
	}

	
	private void setGuideProperty(PrhPayment prhPayment) {
		
		
		PrhRental type = renterService.findById(Integer.valueOf(prhPayment.getPrtno()) );
		prhPayment.setRentals(type);

	}
	@Override
	public List<PrhPayment> findByAccntId(Integer accntId) {
		 List<PrhPayment>  PrhPayments = prhPaymentMapper.findByAccntId(accntId);
		 System.out.println("???unknown???"+PrhPayments.size());
		this.setGuideProperty(PrhPayments);
		return PrhPayments;
	}
	@Override
	public List<PrhPayment> findByMasterIdAndHistory(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
