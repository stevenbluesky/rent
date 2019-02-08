package com.rent.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.common.utils.MyConvertUtil;
import com.rent.condition.HouseAndDateCondition;
import com.rent.condition.MasterCondition;
import com.rent.condition.MasterReletCondition;
import com.rent.condition.RenDaliyCondition;
import com.rent.condition.renAcountCondition;
import com.rent.dao.PrHouseMapper;
import com.rent.dao.PrhMasterMapper;
import com.rent.dao.PrhRentalMapper;
import com.rent.dao.SubsidyMapper;
import com.rent.dao.SubsidyTypeMapper;
import com.rent.entity.Card;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;
import com.rent.entity.Profile;
import com.rent.entity.RentPayWay;
import com.rent.entity.SubsidyType;
import com.rent.services.impl.SubsidyCal;
@Service
public class PrhMasterServiceImpl implements PrhMasterService {

// 灏佽瀵艰埅灞炴��
	private void setGuideProperty(List<PrhMaster> prhMasters) {
		for (PrhMaster b : prhMasters) {
			
			b.setPrHouse(prHouseService.findById(b.getHouseId()));
			b.setProfile(fileManagementService.findById(b.getGuestNo()));
			
			if (b.getSubsidyTypeId()!=null&&b.getSubsidyTypeId()!=-1) {
				b.setSubsidyType(subsidyTypeMapper.selectByPrimaryKey(b.getSubsidyTypeId()));
			}else{
				SubsidyType subsidyType=new SubsidyType(-1, "正常");
				b.setSubsidyType(subsidyType);
			}
			
			
			b.setPrHouse(prHouseService.findById(b.getHouseId()));
			
			if (b.getRentCode()!=null) {
				b.setRentPayWay(rentPayWayService.findById(b.getRentCode()));
			}
			
			List<Card> cards = cardService.findByMasterAndLink(b.getId(), null,0,1);
			if (cards!=null&&cards.size()!=0) {
				b.setCard(cards.get(0));
			}
			List<Card> icards = cardService.findByMasterAndLink(b.getId(), null,1,1);
			if (icards!=null&&icards.size()!=0) {
				b.setIdentityCard(icards.get(0));
			}
		}
	}

	// 灏佽瀵艰埅灞炴��
	private void setGuideProperty(PrhMaster b) {
		
		b.setPrHouse(prHouseService.findById(b.getHouseId()));
		b.setProfile(fileManagementService.findById(b.getGuestNo()));
		
		if (b.getSubsidyTypeId()!=null&&b.getSubsidyTypeId()!=-1) {
			b.setSubsidyType(subsidyTypeMapper.selectByPrimaryKey(b.getSubsidyTypeId()));
		}else{
			SubsidyType subsidyType=new SubsidyType(-1, "正常");
			b.setSubsidyType(subsidyType);
		}
		
		
		b.setPrHouse(prHouseService.findById(b.getHouseId()));
		if (b.getRentCode()!=null) {
			b.setRentPayWay(rentPayWayService.findById(b.getRentCode()));
		}
		
		List<Card> cards = cardService.findByMasterAndLink(b.getId(), null,0,1);
		if (cards!=null&&cards.size()!=0) {
			b.setCard(cards.get(0));
		}
		List<Card> icards = cardService.findByMasterAndLink(b.getId(), null,1,1);
		if (icards!=null&&icards.size()!=0) {
			b.setIdentityCard(icards.get(0));
		}
	}
	
	@Autowired
	private CardService cardService;
	
	public CardService getCardService() {
		return cardService;
	}
	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	@Autowired
	private PrhMasterMapper prhMasterMapper;
	
	@Autowired
	private PrHouseService prHouseService;
	
	@Autowired
	private FileManagementService fileManagementService;
	@Autowired
	private SubsidyTypeMapper subsidyTypeMapper;
	@Autowired
	private PrhRentalMapper prhRentalMapper;
	@Autowired
	private RentPayWayService rentPayWayService;
	@Autowired
	private SubsidyService subsidyService;
	
	
	public SubsidyService getSubsidyService() {
		return subsidyService;
	}

	public void setSubsidyService(SubsidyService subsidyService) {
		this.subsidyService = subsidyService;
	}

	public RentPayWayService getRentPayWayService() {
		return rentPayWayService;
	}

	public void setRentPayWayService(RentPayWayService rentPayWayService) {
		this.rentPayWayService = rentPayWayService;
	}

	public SubsidyTypeMapper getSubsidyTypeMapper() {
		return subsidyTypeMapper;
	}

	public void setSubsidyTypeMapper(SubsidyTypeMapper subsidyTypeMapper) {
		this.subsidyTypeMapper = subsidyTypeMapper;
	}

	public FileManagementService getFileManagementService() {
		return fileManagementService;
	}

	public void setFileManagementService(FileManagementService fileManagementService) {
		this.fileManagementService = fileManagementService;
	}

	public PrHouseService getPrHouseService() {
		return prHouseService;
	}

	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}

	public PrhMasterMapper getPrhMasterMapper() {
		return prhMasterMapper;
	}

	public void setPrhMasterMapper(PrhMasterMapper prhMasterMapper) {
		this.prhMasterMapper = prhMasterMapper;
	}

	@Override
	public int addPrhMaster(PrhMaster prhMaster) {
		
		prhMasterMapper.insert(prhMaster);
		return prhMasterMapper.getCurrId();
	}

	@Override
	public int updatePrhMaster(PrhMaster prhMaster) {
		return prhMasterMapper.updateByPrimaryKey(prhMaster);
	}
	@Override
	public int updatePrhMaster(List<PrhMaster>  prhMaster){
		for (PrhMaster p : prhMaster) {
			prhMasterMapper.updateByPrimaryKey(p);
		}
		return 1;
	}
	
	@Override
	public int delPrhMaster(Integer id) {
		return prhMasterMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int delPrhMaster(Integer[] ids){
		if (ids!=null&&ids.length!=0) {
			for (Integer i : ids) {
				prhMasterMapper.deleteByPrimaryKey(i);
			}
		}
		return 1;
	}

	@Override
	public PrhMaster findById(Integer id) {
		PrhMaster selectByPrimaryKey = prhMasterMapper.findById(id);
		setGuideProperty(selectByPrimaryKey);
		return selectByPrimaryKey;
	}
	

	@Override
	public String findMasterIdByStaAndHouseId(String sta, Integer houseId) {
		
		PrhMaster m=  prhMasterMapper.getMasterIdBystaAndHouseId(sta, houseId);
		if(m==null){
			return null;
		}
		 String n = m.getGuestNo();
		 return n;
	}

	@Override
	public PrhMaster getByAllowInNo(String allowInNo) {
		List<PrhMaster> byAllowInNo = prhMasterMapper.getByAllowInNo(allowInNo);
		return byAllowInNo!=null&&byAllowInNo.size()!=0?byAllowInNo.get(0):null;
	}

	@Override
	public PrhMaster getByContract(String contract) {
		List<PrhMaster> byContract = prhMasterMapper.getByContract(contract);
		return byContract!=null&&byContract.size()!=0?byContract.get(0):null;
	}

	@Override
	public List<PrhMaster> findByCondition(MasterCondition condition) {
		List<PrhMaster> a = prhMasterMapper.findByCondition(condition);
		setGuideProperty(a);
		return a;
		
	}

	@Override
	public int getCountByCondition(MasterCondition condition) {
		return  prhMasterMapper.getCountByCondition(condition);		
	}

	@Override
	public List<PrhMaster> findByReletCondition(MasterReletCondition condition) {
		List<PrhMaster> s = prhMasterMapper.findByReletCondition(condition);
		setGuideProperty(s);
		return s;
	}

	@Override
	public Integer getCountByReletCondition(MasterReletCondition condition) {
		return prhMasterMapper.getCountByReletCondition(condition);
	}

	/**
	    * 更新当前主单的补贴以及租金
	    * @return
	    */
	@Override
	public int updateSubsidyAndRate(int masterId) {
		//主单信息
		PrhMaster master = this.findById(masterId);
		//房间信息
		PrHouse house=master.getPrHouse();
		int  subsidyTypeId=master.getSubsidyTypeId();
		int perNum=master.getNumbs()!=null?master.getNumbs():1;
		System.out.println("人数："+perNum);
		// 查询当前补贴信息(保障外和保障内) 一个人的补贴
				SubsidyCal inSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), subsidyTypeId, 1,
						master.getBdate());
				SubsidyCal outSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), subsidyTypeId, 2,
						master.getBdate());

				if (inSubsidyCal != null && outSubsidyCal != null) {
					inSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
					inSubsidyCal.setPerNum(perNum);
					outSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
					outSubsidyCal.setPerNum(perNum);
					// 补贴
					Double subPrice = inSubsidyCal.calInSubsidy() + outSubsidyCal.calOutSubsidy();
					master.setSetrate(BigDecimal.valueOf(subPrice));
					// 实收金额
					BigDecimal realMoney = BigDecimal.valueOf(Double.parseDouble(house.getMonthPrice().toString()) - subPrice);
					master.setRate(realMoney.doubleValue()>=0?realMoney:BigDecimal.valueOf(0));
					this.updatePrhMaster(master);
					System.out.println("补贴："+subPrice);
					return 1;
				}else{
					 return 0;
				}
				
	}
	//计算补贴
	 public Double calSubsidy(Integer estateId,Integer subsidyTypeId,Integer perNum,Double area, Date calDate){
		// 查询当前补贴信息(保障外和保障内) 一个人的补贴
			SubsidyCal inSubsidyCal = subsidyService.findSubsidyCal(estateId, subsidyTypeId, 1,calDate);
			SubsidyCal outSubsidyCal = subsidyService.findSubsidyCal(estateId, subsidyTypeId, 2,calDate);
			if (inSubsidyCal != null && outSubsidyCal != null) {
				inSubsidyCal.setArea(Double.valueOf(area));
				inSubsidyCal.setPerNum(perNum);
				outSubsidyCal.setArea(Double.valueOf(area));
				outSubsidyCal.setPerNum(perNum);
				// 补贴
				Double subPrice = inSubsidyCal.calInSubsidy() + outSubsidyCal.calOutSubsidy();
				return subPrice;
			}else{
				return -1.0;
			}
		 
	 }
	@Override
	public List<PrhMaster> findByReletApplyPaged(MasterReletCondition condition) {
		   List<PrhMaster> s = prhMasterMapper.findByReletApplyPaged(condition);
		   setGuideProperty(s);
		   return s;
	}

	@Override
	public Integer getCountByReletApplyPaged(MasterReletCondition condition) {
		
		return prhMasterMapper.getCountByReletApplyPaged(condition);
	}

	@Override
	public List<PrhMaster> findByCheckInRecordPaged(MasterReletCondition condition) {
		List<PrhMaster> so = prhMasterMapper.findByCheckInRecordPaged(condition);
		setGuideProperty(so);
		return so;
	}

	@Override
	public Integer getCountByCheckInRecordPaged(MasterReletCondition condition) {
		return prhMasterMapper.getCountByCheckInRecordPaged(condition);
	}

	@Override
	public Integer findCountByRenDaliyCondition(RenDaliyCondition condition) {
			
		return prhMasterMapper.findCountByRenDaliyCondition(condition);
	}

	@Override
	public List<PrhMaster> findByRenDaliyConditionAndPaged(RenDaliyCondition condition, Integer currage, Integer size) {
		
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		
		List<PrhMaster> prhMaster = prhMasterMapper.findByRenDaliyConditionAndPaged(condition);
		
		setGuideProperty(prhMaster);
		return prhMaster;
	}

	@Override
	public List<PrhMaster> findByRenDaliyConditionAndPagedTui(RenDaliyCondition condition, Integer currage,
			Integer size) {
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		
		List<PrhMaster> prhMaster = prhMasterMapper.findByRenDaliyConditionAndPagedTui(condition);
		
		setGuideProperty(prhMaster);
		return prhMaster;
	}

	@Override
	public Integer findCountByRenDaliyConditionTui(RenDaliyCondition condition) {
		return prhMasterMapper.findCountByRenDaliyConditionTui(condition);
	}

	@Override
	public List<PrhMaster> findMasterByrentAccountAndPaged(renAcountCondition condition, Integer currage,
			Integer size) {
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrhMaster> prhmasters = prhMasterMapper.findMasterByrentAccountAndPaged(condition);
		
		this.setGuideProperty(prhmasters);
		return prhmasters;
	}

	@Override
	public Integer findMasterCountByrentAccount(renAcountCondition condition) {
		
		return prhMasterMapper.findMasterCountByrentAccount(condition);
	}

	@Override
	public List<PrhMaster> findByGuestNo(String guestno) {
		List<PrhMaster> masters =prhMasterMapper.findByGuestNo(guestno);
		setGuideProperty(masters);
		return masters;
	}

	@Override
	public List<PrhMaster> findByCusNo(String cusNo){
		List<PrhMaster> masters =prhMasterMapper.findByCusNo(cusNo);
		setGuideProperty(masters);
		return masters;
	}
	
	@Override
	public Integer findAllCountByCondition(MasterCondition condition) {
		return prhMasterMapper.findAllCountByCondition(condition);
	}

	@Override
	public List<PrhMaster> findAllByConditionAndPaged(MasterCondition condition, Integer currage, Integer size) {
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrhMaster> prhmasters = prhMasterMapper.findAllByConditionAndPaged(condition);
		this.setGuideProperty(prhmasters);
		return prhmasters;
	}

	@Override
	public List<PrhMaster> findHistory(String guestno) {
		
		return prhMasterMapper.findHistory(guestno);
	}

	@Override
	public List<PrhMaster> findByHouseId(Integer houseid) {
		 List<PrhMaster> list= prhMasterMapper.findByHouseId(houseid);
		 setGuideProperty(list);
		return list;
	}

	@Override
	public List<PrhMaster> findArreaseHouse() {
		List<PrhMaster> masters =prhMasterMapper.findArreaseHouse();
		if(masters.isEmpty()){
			return masters;
		}else{
			setGuideProperty(masters);
			return masters;
		}
	
	}

	@Override
	public Integer findCountByRenDaliyConditionTai(RenDaliyCondition condition) {
		if(condition.getToday()!=null){
			return prhMasterMapper.findCountByRenDaliyConditionTaiDate(condition);
		}else{
			return prhMasterMapper.findCountByRenDaliyConditionTai(condition);	
		}
		
		
	}

	@Override
	public List<PrhMaster> findCountByRenDaliyConditionTaiAndPaged(RenDaliyCondition condition, Integer currpage,
			Integer size) {
		
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrhMaster> prhmasters = prhMasterMapper.findCountByRenDaliyConditionTaiAndPaged(condition);
		this.setGuideProperty(prhmasters);
		return prhmasters;
		
	}

	@Override
	public List<PrhMaster> findByRenDaliyConditionAndPagedHe(RenDaliyCondition condition, Integer currpage,
			Integer size) {
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		
		List<PrhMaster> prhMaster = prhMasterMapper.findByRenDaliyConditionAndPagedHe(condition);
		
		setGuideProperty(prhMaster);
		return prhMaster;
	}

	@Override
	public Integer findCountByRenDaliyConditionHe(RenDaliyCondition condition) {
		// TODO Auto-generated method stub
		return prhMasterMapper.findCountByRenDaliyConditionHe(condition);
	}

	@Override
	public boolean checkBeforeRental(PrhMaster master) {
		// TODO Auto-generated method stub
		
		HouseAndDateCondition condition = new HouseAndDateCondition();
		condition.setAccnt(master.getId());
		Date date = new Date();
		condition.setDate(date);
		
		List<PrhRental> rentals = prhRentalMapper.findByAccntAndDate(condition);
		for (PrhRental prhRental : rentals) {
			if(prhRental.getSta().equals("F")){
				return false;
			}
		}
		
		return true;
	}

	@Override
	public List<PrhMaster> findCanGetCardPaged(MasterReletCondition condition) {
		 List<PrhMaster> s = prhMasterMapper.findCanGetCardPaged(condition);
		 setGuideProperty(s);
		 return s;
	}

	@Override
	public Integer getCanGetCardCount(MasterReletCondition condition) {
		return prhMasterMapper.getCanGetCardCount(condition);
	}

	@Override
	public List<PrhMaster> findMastersByEstate(Integer estateId) {
		 List<PrhMaster> s = prhMasterMapper.findMastersByEstate(estateId);
		 setGuideProperty(s);
		 return s;
	}

	
	@Override
	public Integer getLiveInGuestCount(String idno) {
		return prhMasterMapper.getLiveInGuestCount(idno);
	}

	@Override
	public List<PrhMaster> findRentersExportPaged(MasterCondition condition) {
		List<PrhMaster> master = prhMasterMapper.findRentersExportPaged(condition);
		setGuideProperty(master);
		return master;
		
	}

	@Override
	public Integer getCountRentersExport(MasterCondition condition) {
		return prhMasterMapper.getCountRentersExport(condition);
	}

	@Override
	public List<PrhMaster> findRentersExport(MasterCondition condition) {
		List<PrhMaster> master = prhMasterMapper.findRentersExport(condition);
		setGuideProperty(master);
		return master;
	}

	@Override
	public List<String> findAllBatchs(String batch,Integer estateId) {
		return prhMasterMapper.findAllBatchs(batch,estateId);
	}

	@Override
	public List<PrhMaster> findMastersByBatchs(String batch, Integer estateId) {
		List<PrhMaster> master = prhMasterMapper.findMastersByBatchs(batch, estateId);
		setGuideProperty(master);
		return master;
	}

}
