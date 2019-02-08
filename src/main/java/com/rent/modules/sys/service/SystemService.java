/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.modules.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.common.persistence.Page;
import com.rent.common.service.BaseService;
import com.rent.dao.PrHouseMapper;
import com.rent.dao.UsersMapper;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.entity.Users;
import com.rent.modules.sys.dao.PaymentDao;
import com.rent.modules.sys.dao.RentDao;
import com.rent.modules.sys.dao.RepaireDao;
import com.rent.modules.sys.dao.TenementDao;
import com.rent.modules.sys.entity.Payment;
import com.rent.modules.sys.entity.Rent;
import com.rent.modules.sys.entity.Repaire;
import com.rent.modules.sys.entity.Tenement;

/**
 * 系统管理，安全相关实体的管理�?,包括用户、角色�?�菜�?.
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	public static List<Estate> statusList = new ArrayList<Estate>();
	public static List<String> houseStatus = new ArrayList<String>();
	public static List<String> houseValidateStatus = new ArrayList<String>();
	
	static{
		houseStatus.add("7");
		houseStatus.add("8");
		houseValidateStatus.add("4");
		houseValidateStatus.add("5");
		Estate registry0 = new Estate();
		registry0.setName("全部");
		Estate registry = new Estate();
		registry.setName("已登记");
		Estate registry1 = new Estate();
		registry1.setName("完成检查");
		Estate registry2 = new Estate();
		registry2.setName("维修中");
		Estate registry3 = new Estate();
		registry3.setName("维修完成");
		Estate registry4 = new Estate();
		registry4.setName("合格");
		Estate registry5 = new Estate();
		registry5.setName("不合格");
		statusList.add(registry0);
		statusList.add(registry);
		statusList.add(registry1);
		statusList.add(registry2);
		statusList.add(registry3);
		statusList.add(registry4);
		statusList.add(registry5);
	}
	
	@Autowired
	private RepaireDao repaireDao;
	
	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private TenementDao tenementDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private PrHouseMapper phHouseDao;
	
	@Autowired
	private UsersMapper usersDao;
	
	public List<Payment> getRepairePayments(Payment payment) {
		return paymentDao.getRepaireItems(payment);
	}
	
	@Transactional(readOnly = false)
	public void savePayments(List<Payment> list) {
		if(list != null && list.size() > 0) {
			paymentDao.deleteByRepaireID(list.get(0));
		}
		for(int i = 0 ; i < list.size(); i++){
			Payment pay = list.get(i);
			if(pay.getPaymentID() == null || pay.getPaymentID().trim().length() == 0) {
				pay.preInsert();
				pay.setPaymentID(pay.getId());
				paymentDao.insert(pay);
			} else {
				paymentDao.insert(pay);
			}
		}
	}
	
	public void deletePayments(Payment payment){
		paymentDao.delete(payment);
	}
	
	public Page<Repaire> findRepaire(Page<Repaire> page, Repaire user) {
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(repaireDao.findList(user));
		return page;
	}
	
	public Page<Repaire> findSubmit(Page<Repaire> page, Repaire user) {
		user.setPage(page);
		page.setList(repaireDao.findSubmit(user));
		return page;
	}
	
	public void updatePrHouseState(Repaire rep) {
		PrHouse pr = new PrHouse();
		pr.setId(rep.getHouseID());
		if(rep.getHouseStatus() != null && !rep.getHouseStatus().isEmpty()) {
			pr.setState(Short.parseShort(rep.getHouseStatus()));
			
			
		}
		if(rep.getOriginHouseStatus()!=null&&!rep.getOriginHouseStatus().isEmpty())
		{
			pr.setOriginState(Short.parseShort(rep.getOriginHouseStatus()));
		}
		if(rep.getHouseStatus() != null && !rep.getHouseStatus().isEmpty()&&"4".equals(rep.getHouseStatus())) {
			pr.setRemark("维修完成");
			
			
		}
		phHouseDao.updateByPrimaryKeySelective(pr);
	}
	
	public void updatePrHouseState2(Repaire rep) {
		PrHouse pr = new PrHouse();
		pr.setId(rep.getHouseID());
		if(rep.getOriginHouseStatus() != null && !rep.getOriginHouseStatus().isEmpty()) {
			pr.setState(Short.parseShort(rep.getOriginHouseStatus()));
			
			
		}
		
		phHouseDao.updateByPrimaryKeySelective(pr);
	}
	
	@Transactional(readOnly = false)
	public void save(Repaire rep){
		updatePrHouseState(rep);
		rep.preInsert();
		repaireDao.insert(rep);
	}
	
	@Transactional(readOnly = false)
	public void deleteRepaire(String id,Repaire rep){
		repaireDao.delete(id);
		List<Repaire> list = repaireDao.findByHouse(rep);
		if(list == null || list.size() == 0)
		{
			updatePrHouseState2(rep);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void updateRepaire(Repaire rep){
		updatePrHouseState(rep);
		repaireDao.update(rep);
	}
	
	@Transactional(readOnly = false)
	public void repaireCheck(Repaire rep){
		int sum = 0;
		List<Payment> list = rep.getPayments();
		if(list != null && list.size() > 0) {
			for(int i = 0 ; i< list.size(); i++){
				sum += list.get(i).getSum();
			}
			rep.setPayment(sum+"");
			savePayments(list);
		}
		repaireDao.updateStatus(rep);
	}
	
	@Transactional(readOnly = false)
	public void addApprover(Repaire rep){
		repaireDao.addApprover(rep);
	}
	
	@Transactional(readOnly = false)
	public void updateRepaireStatus(Repaire rep){
		repaireDao.updateRepaireStatus(rep);
	}
	
	@Transactional(readOnly = false)
	public Repaire getRepaireByID(String id){
		return repaireDao.get(id);
	}
	
	@Transactional(readOnly = false)
	public void submitThird(Repaire rep){
		repaireDao.submitThird(rep);
	}
	
	@Transactional(readOnly = false)
	public void validateUpdate(Repaire rep) {
		List<Repaire> list = repaireDao.findByHouse(rep);
		if(rep.getStatus().equals(statusList.get(6).getName()) || list == null || list.size() == 0){
			repaireDao.validateUpdate(rep);
			return;
		}
		boolean flag = true;
		for (ListIterator<Repaire> iter = list.listIterator(); iter.hasNext(); ) {
			Repaire element = iter.next();
			if(!element.getStatus().equals(statusList.get(5).getName()) && !element.getId().equals(rep.getId())){
				flag = false;
				break;
			}
		}
		Repaire tmp = new Repaire();
		tmp.setHouseID(rep.getHouseID());
		if(flag && rep.getHouseStatus() != null && rep.getHouseStatus().equals(houseStatus.get(0))) {
			tmp.setHouseStatus(houseValidateStatus.get(0));
			updatePrHouseState(tmp);
		}
		if(flag && rep.getHouseStatus() != null && rep.getHouseStatus().equals(houseStatus.get(1))) {
			//tmp.setHouseStatus(houseValidateStatus.get(1));
			tmp.setHouseStatus(rep.getOriginHouseStatus());
			updatePrHouseState(tmp);
		}
		repaireDao.validateUpdate(rep);
	}
	
	public Page<Rent> searchRent(Page<Rent> page,Rent rep){
		rep.setPage(page);
		page.setList(rentDao.search(rep));
		return page;
	}
	
	public Page<Repaire> searchRepaire(Page<Repaire> page,Repaire rep){
		rep.setPage(page);
		page.setList(repaireDao.search(rep));
		return page;
	}
	
	public Page<Rent> listAllRent(Page<Rent> page, Rent user) {
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(rentDao.findList(user));
		return page;
	}
	
	public Page<Rent> getRentByTenement(Page<Rent> page, Rent user) {
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(rentDao.getRentsByTenement(user));
		return page;
	}
	
	public List<Repaire> getRepaireByMasterID(Repaire rep) {
		return repaireDao.getRepaireByMasterID(rep);
	}
	
	public List<Repaire> getRepaireByHouseID(Repaire rep) {
		return repaireDao.findByHouse(rep);
	}
	
	public Rent getRentByID(String rentID){
		Rent rent = rentDao.get(rentID);
		return rent;
	}
	
	public List<Tenement> listAllTenement() {
		return tenementDao.listAll();
	}
	
	public Tenement getTenementByName(String name) {
		return tenementDao.getTenementByName(name);
	}
	
	public Tenement getTenement(String id){
		return tenementDao.get(id);
	}
	
	public List<Users> getThirdUsers(Users user){
		return usersDao.findThirdUsers();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
