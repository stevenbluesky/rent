package com.rent.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rent.common.config.Global;
import com.rent.common.utils.MyDateUtil;
import com.rent.common.utils.RestfulUtil;
import com.rent.condition.RenDaliyCondition;
import com.rent.condition.RenterCondition;
import com.rent.dao.PrHouseMapper;
import com.rent.dao.PrhMasterMapper;
import com.rent.dao.PrhPaymentMapper;
import com.rent.dao.PrhRentalMapper;
import com.rent.entity.*;
import com.rent.services.DoorlockUserService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhMasterService;
import com.rent.services.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RenterServiceImpl implements RenterService {

	@Autowired
	private PrHouseMapper prHouseMapper;
	@Autowired
	private PrHouseService prHouseService;
	@Autowired
	private PrhRentalMapper prhRentalMapper;
	@Autowired
	private PrhMasterMapper prhMasterMapper;
	@Autowired
	private PrhPaymentMapper prhPaymentMapper;

	@Autowired
	private PrhMasterService prhMasterService;
	@Autowired
	private DoorlockUserService doorlockUserService;

	@Override
	public boolean updateHousePrice(Integer[] chk, String[] caname, String reason, String decDate) {
		try {
			for (int i = 0; i < caname.length; i++) {
				if (chk[i] != 0 && caname[i] != "") {
					PrHouse p = prHouseService.findById(chk[i]);
					System.out.println(caname[i].trim());
					BigDecimal big = new BigDecimal(caname[i].trim());
					p.setRentMod(big);
					p.setReason(reason);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 灏忓啓鐨刴m琛ㄧず鐨勬槸鍒嗛挓

					Date date = sdf.parse(decDate);
					p.setDecDate(date); // 濉椂闂�

					prHouseService.updatePrHouse(p);
				}
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public Integer findCountByCondition(RenterCondition rental) {

		return prhRentalMapper.findCountByCondition(rental);
	}

	// 闁跨喐鏋婚幏鐤棅闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
	private void setGuideProperty(List<PrhRental> prhRental) {
		for (PrhRental e : prhRental) {

			PrhMaster type = prhMasterService.findById(e.getAccnt());
			e.setAccnts(type);
		}
	}

	// 闁跨喐鏋婚幏鐤棅闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗
	private void setGuideProperty(PrhRental prhRental) {
		// 闁跨喐鏋婚幏铚傜瑹闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		PrhMaster type = prhMasterService.findById(prhRental.getAccnt());

		prhRental.setAccnts(type);

	}

	@Override
	public List<PrhRental> findByConditionAndPaged(RenterCondition condition, Integer currage, Integer size) {

		int begin = (currage - 1) * size + 1;
		int end = begin + size - 1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrhRental> prhRental = prhRentalMapper.findByConditionAndPaged(condition);
		this.setGuideProperty(prhRental);

		return prhRental;
	}

	@Override
	public void createRentals(int masterId) throws ParseException {
		

		Double totalPrice = this.calTotalPrice(masterId);

		PrhMaster master = prhMasterService.findById(masterId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Double monthPrice = Double.valueOf(master.getRate() + "");

		Date begin = master.getBdate();
		Date end = master.getEdate();
		int monthNum = MyDateUtil.monthNum(begin, end);
		System.out.println("" + begin);
		// 
		PrhRental rentalDeposit = new PrhRental();
		rentalDeposit.setInumber(0);
		if (master.getDeposit() == null) {
			master.setDeposit(BigDecimal.valueOf(0.0));
		}

		rentalDeposit.setRate(Double.valueOf(master.getDeposit() + ""));
		rentalDeposit.setAccnt(masterId);
		rentalDeposit.setSta("F");

		prhRentalMapper.insert(rentalDeposit);
		//是否是1月
		boolean isMonth1=(master.getBdate().getMonth()+1)==1;
		Integer currDay=master.getBdate().getDate();
		
		
		for (int i = 1; i <= monthNum; i++) {
			PrhRental rental = new PrhRental();
			rental.setAccnt(masterId);
			rental.setSta("F");
			rental.setCharge(totalPrice);
			
			//不是最后一个月
			if (i != monthNum) {
				rental.setInumber(i);
				rental.setRate(monthPrice);
				
				String beginStr = df.format(begin);
				rental.setCharge1(master.getSetrate());
				rental.setCharge2(master.getPrHouse().getMonthPrice());
				
				
				rental.setBdate(df.parse(beginStr));
				
				//是否是月底
				if (MyDateUtil.isLastDayInMonth(begin)) {
					rental.setEdate(MyDateUtil.addDate(MyDateUtil.getLastDayNextMonth(begin), -1) );
				}else{
					rental.setEdate(MyDateUtil.addDate(MyDateUtil.addMonth(df.parse(beginStr), 1), -1));	
				}
				
				prhRentalMapper.insert(rental);
				begin=MyDateUtil.addDate(MyDateUtil.toDate(MyDateUtil.getDateFormat(rental.getEdate())), 1);

			} else {
				System.out.println();
				/*boolean isFullMonth = (end.getDate() - begin.getDate()) == -1;*/
				Date tempEndDate = MyDateUtil.addDate(MyDateUtil.addYear(MyDateUtil.cloneDate(master.getBdate()), 1),-1);
				boolean isFullMonth =MyDateUtil.isTheSame(master.getEdate(), tempEndDate);
				
				if (!isFullMonth) {
					int dayNum = MyDateUtil.getDateNum(begin, end);
					rental.setInumber(i);
					rental.setRate(monthPrice / 30 * dayNum);
					rental.setCharge1(BigDecimal.valueOf(master.getSetrate().doubleValue() / 30 * (dayNum + 1)));
					rental.setCharge2(
							BigDecimal.valueOf(master.getPrHouse().getMonthPrice().doubleValue() / 30 * (dayNum + 1)));
					rental.setBdate(begin);
					rental.setEdate(end);

					prhRentalMapper.insert(rental);

				} else {
					rental.setInumber(i);
					rental.setRate(monthPrice);
					rental.setCharge1(master.getSetrate());
					rental.setCharge2(master.getPrHouse().getMonthPrice());
					String beginStr = df.format(begin);
					rental.setBdate(df.parse(beginStr));
					rental.setEdate(end);
					prhRentalMapper.insert(rental);
					MyDateUtil.addMonth(begin, 1);
				}

			}

		}

	}

	private Double calTotalPrice(int masterId) {
		PrhMaster master = prhMasterMapper.selectByPrimaryKey(masterId);

		Date begin = MyDateUtil.toDate(MyDateUtil.getDateFormat(master.getBdate()));
		Date end = MyDateUtil.toDate(MyDateUtil.getDateFormat(master.getEdate()));

		Double monthPrice = Double.valueOf(master.getRate() + "");
		Double total = 0.0;

		if (master.getDeposit() != null) {
			total += master.getDeposit().doubleValue();
		}

		int monthNum = MyDateUtil.monthNum(begin, end);

		for (int i = 1; i <= monthNum; i++) {
			if (i != monthNum) {
				MyDateUtil.addMonth(begin, 1);
				System.out.println(MyDateUtil.getDateFormat(begin));
				
				total += monthPrice;

			} else {
				System.out.println(MyDateUtil.getDateFormat(begin) + "  " + MyDateUtil.getDateFormat(end));

				boolean isFullMonth = (end.getDate() - begin.getDate()) == -1;
				if (!isFullMonth) {
					int dayNum = MyDateUtil.getDateNum(begin, end);
					total += monthPrice / 30 * (dayNum + 1);

				} else {
					MyDateUtil.addMonth(begin, 1);
					total += monthPrice;

				}

			}

		}
		return total;
	}

	@Override
	public PrhRental findById(Integer id) {

		PrhRental rental = prhRentalMapper.findById(id);

		setGuideProperty(rental);

		return rental;
	}

	@Override
	public List<PrhRental> findByAccnt(Integer accnt) {

		List<PrhRental> rentals = prhRentalMapper.findByAccnt(accnt);
		this.setGuideProperty(rentals);
		return rentals;
	}

	@Override
	public Integer payMoneyAndUpdateAndInsert(Integer[] chk, String ino, String pay, Integer payType,String batch) {
		Integer count = 0;
		Integer flag = 0;
		// 淇敼 娆犺垂鐘舵��
		for (int i = 0; i < chk.length; i++) {

			PrhRental rental = this.findById(chk[i]);

			PrhMaster master = prhMasterService.findById(rental.getAccnt());
			master.setBatch(batch);
			PrHouse houses = prHouseMapper.selectByPrimaryKey(master.getHouseId());

			boolean flagRent = prhMasterService.checkBeforeRental(master);
			if (!flagRent) {
				houses.setState(Short.valueOf("3")); // 淇敼鎴� 娆犺垂
				prHouseMapper.updateByPrimaryKey(houses);
			}
			if (rental.getSta().equals("T")) {// 宸茬粨娓�

				return 4;
			} else {

				if (rental.getInumber() == 1) {
					
					if (master.getSta().equals("7")) {
						/* master.setSta("8"); */
						master.setSta("1");
						master.setSrc(null);
						PrHouse house = prHouseMapper.selectByPrimaryKey(master.getHouseId());
						house.setState(Short.valueOf("5"));
						prHouseMapper.updateByPrimaryKey(house);
					}
					if (master.getSta().equals("5")) {
						//续租成功
						master.setSta("6");

						PrhMaster oldMaster = prhMasterService.findById(master.getOldMasterId());
						oldMaster.setType(null);
						master.setSrc(null);
						oldMaster.setSta("9");
						prhMasterService.updatePrhMaster(oldMaster);// 修改原主单
						List<DoorlockUser> allByMasterid = doorlockUserService.findAllByMasterid(oldMaster.getId());
						for(DoorlockUser d:allByMasterid){
							d.setMasterid(master.getId());
							doorlockUserService.updateByPrimaryKey(d);
						}
						List<DoorlockUser> availableAllByMasterid = doorlockUserService.findAvailableAllByMasterid(master.getId());
						for(DoorlockUser d:availableAllByMasterid) {
							if(d.getValidthrough().getTime()<=master.getEdate().getTime()) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								String begin = sdf.format(d.getValidfrom());
								String end = sdf.format(master.getEdate());
								end = end.substring(0,10)+" 23:59";
								String params = "{\"method\": \"thing.service.UpdateUserValidDateTime\",\"deviceid\": \"" + d.getDeviceid() + "\",\"async\":true,\"nodeid\":1,\"params\":{\"UserID\":" + d.getUsercode() + ",\"ValidBeginDateTime\":\""+begin+"\",\"ValidEndDateTime\":\""+end+"\"}}";
								String result = RestfulUtil.postHttps(params, "lock");
								JSONObject resultMap = JSON.parseObject(result);
								int resultcode = resultMap.getIntValue("resultcode");
								String receipt = resultMap.getString("receipt");
								if(resultcode==1){
									d.setStatus(Global.STATUS_UPDATING_TIME);
									d.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
									d.setReceipt(receipt);
									try {
										d.setValidthrough(sdf.parse(end));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									doorlockUserService.updateByPrimaryKey(d);
								}else{
									System.out.println("更新有效期命令失败了");
								}
							}
						}
					}

					master.setRefer1("");
					Integer f = prhMasterService.updatePrhMaster(master);
					System.out.println();
					if (f == 1) {
						rental.setSta("T");
						System.out.println("chk" + chk[i]);
						rental.setIlogdate(new Date());
						rental.setIno(ino);
						BigDecimal iam = new BigDecimal(rental.getRate());
						rental.setIamount(iam);
						prhRentalMapper.updateByPrimaryKey(rental);

						Integer maxid = prhPaymentMapper.findMaxId();
						if (maxid == null) {
							maxid = 1;
						}

						int mid = maxid + 1;

						PrhPayment payment = new PrhPayment();

						payment.setPrtno(rental.getId().toString());

						payment.setCredit(rental.getRate());
						payment.setLogdate(new Date());
						payment.setSysdate(new Date());
						payment.setPaycode(payType.toString());
						payment.setId(mid);

						// 鎶奸噾鎴栫閲�
						if (rental.getInumber() == 0) {
							payment.setRef1("D");
						} else {
							payment.setRef1("R");
						}

						
						master.setRefer1("");
						prhMasterService.updatePrhMaster(master);
						prhPaymentMapper.insert(payment);
						flag = flag + 1;
						count = count + 1;
					}
				} else {// 闄や簡绗竴涓湀鐨� 鎵�鏈変氦閽辫处鍗曢兘鍦ㄨ繖鍒ゆ柇 淇敼

					// 濡傛灉鎴垮瓙鏄瑺璐规敼鎴愪箣鍓嶇殑鐘舵��
					if (houses.getState() == 3 && houses.getCheapPrice() != null) {

						PrHouse house = prHouseMapper.selectByPrimaryKey(master.getHouseId());
						house.setState(Short.parseShort(house.getCheapPrice().toString()));
						house.setCheapPrice(null);
						prHouseMapper.updateByPrimaryKey(house);
					}

					rental.setSta("T");
					// rental.setIno(ino);
					System.out.println("chk" + chk[i]);
					rental.setIlogdate(new Date());
					rental.setIno(ino);
					BigDecimal iam = new BigDecimal(rental.getRate());
					rental.setIamount(iam);
					prhRentalMapper.updateByPrimaryKey(rental);

					Integer maxid = prhPaymentMapper.findMaxId();
					if (maxid == null) {
						maxid = 1;
					}

					int mid = maxid + 1;
					PrhPayment payment = new PrhPayment();

					System.out.println("rental.getId().toString()" + rental.getId().toString());

					payment.setPrtno(rental.getId().toString());

					payment.setCredit(rental.getRate());
					payment.setLogdate(new Date());
					payment.setSysdate(new Date());
					payment.setPaycode(payType.toString());
					payment.setId(mid);
					// 鎶奸噾鎴栫閲�
					if (rental.getInumber() == 0) {
						payment.setRef1("D");
					} else {
						payment.setRef1("R");
					}

					
					master.setRefer1("");
					prhMasterService.updatePrhMaster(master);

					prhPaymentMapper.insert(payment);
					flag++;
					count++;
				}
			}

		}
		if (count != chk.length) {
			System.out.println("count:" + count);

			System.out.println(1);
			return 1;
		} else {
			System.out.println(2);
			if (flag != chk.length) {
				System.out.println(3);
				return 2;
			} else {
				System.out.println(4);
				return 3;
			}

		}

	}

	@Override
	public Integer updateById(Integer id) {
		PrhRental record = prhRentalMapper.findById(id);
		record.setSta("F");

		if (prhRentalMapper.updateByPrimaryKey(record) == 1) {

			// 閺嶈宓乺ecord id娣囶喗鏁肩拹锕�濮�
			PrhPayment payment = prhPaymentMapper.findByPrtNo(record.getId());
			payment.setLogshift("1");
			if (prhPaymentMapper.updateByPrimaryKey(payment) == 1) {
				// 娑撱倓閲滈柈鎴掓叏閺�瑙勫灇閸旓拷
				return 3;
			} else {

				// 娣囶喗鏁紁ayment閻樿埖锟戒礁銇戠拹锟�
				return 2;

			}
		} else {
			// 娣囶喗鏁紃ental閻樿埖锟戒礁銇戠拹锟�
			return 1;
		}
	}

	@Override
	public Integer delByMaster(int master) {
		return prhRentalMapper.delByMaster(master);
	}

	@Override
	public Integer updateandinsert(Integer[] chk) {
		Integer count = 0; 
		Integer flag = 0; 
		PrhMaster master=null;
		for (int i = 0; i < chk.length; i++) {
			PrhRental record = prhRentalMapper.findById(chk[i]);
			if (master==null) {
				master=prhMasterService.findById(record.getAccnt());
			}
			
			
			record.setSta("F");
			if (prhRentalMapper.updateByPrimaryKey(record) == 1) {
				count++;
				PrhPayment payment1 = prhPaymentMapper.findByPrtNo(record.getId());
				/*PrhPayment payment2 = prhPaymentMapper.findByPrtNo(record.getId());*/
				
				payment1.setLogshift("1");
				if (prhPaymentMapper.updateByPrimaryKey(payment1) == 1) {
					

					flag++;

				}

			}
		}

				//全部作废在租状态应变为登记
		
				if ("1".equals(master.getSta())) {
					
					List<PrhRental> rentals = prhRentalMapper.findByAccnt(master.getId());
					int cnt=0;
					for (PrhRental r : rentals) {
						if ("T".equals(r.getSta()) ) {
							cnt++;
						}
						
					}
					System.out.println("个数:"+cnt);
					if (cnt==0) {
						master.setSta("7");
						prhMasterService.updatePrhMaster(master);
					}
					
				}
		
		if (count == chk.length) {
			
			if (flag == chk.length) {
				
				return 2;
			}
			return 1;
		}
		
		return 4;
	}

	@Override
	public List<PrhRental> findByMasterId(Integer masterID) {
		List<PrhRental> prhRental = prhRentalMapper.findByMasterId(masterID);
		setGuideProperty(prhRental);
		return prhRental;
	}

	@Override
	public Integer accountRental(Integer[] chk2, Integer userId) {

		for (int i = 0; i < chk2.length; i++) {
			//
			PrhRental rental = prhRentalMapper.findById(chk2[i]);
			rental.setAudit1("T");
			rental.setLogid1(userId.toString());// 瀹℃牳鍛�
			rental.setAudit1time(new Date());

			prhRentalMapper.updateByPrimaryKey(rental);
		}

		return 2;
	}

	@Override
	public Integer checkRefer(Integer accntid2) {
		Integer flag = 0;
		Integer flag2 = 0;
		// 鏍规嵁id 鏌ヨ鎵�鏈夎处鍔′俊鎭槸鍚︿綍鏃�
		List<PrhRental> rentals = prhRentalMapper.findByAccnt(accntid2);

		for (PrhRental prhRental : rentals) {
			if (prhRental.getSta().equals("T")) {
				flag2++;
			}
			if (prhRental.getAudit1() != null) {
				if (prhRental.getAudit1().equals("T")) {//
					flag++;// 涓哄凡浠樻竻鐨�
				}
			}
		}
		if (flag == flag2) {
			PrhMaster master = prhMasterService.findById(accntid2);
			master.setRefer1("1");
			prhMasterService.updatePrhMaster(master);
			System.out.println("鍏ㄩ儴鏍哥畻");
			return 1;
		}

		else {
			System.out.println("娌″叏閮ㄦ牳绠�");
			return 2;

		}

	}

	@Override
	public List<PrhRental> findArrears() {
		// TODO Auto-generated method stub
		Date date = new Date();
		return prhRentalMapper.findArrears(date);
	}

	@Override
	public Integer findCountByExpiringCondition(RenDaliyCondition condition) {
		//
		return prhRentalMapper.findCountByExpiringCondition(condition);
	}

	@Override
	public List<PrhRental> findCountByExpiringConditionAndPaged(RenDaliyCondition condition, Integer currpage,
			Integer size) {
		System.out.println("娴嬭瘯3");
		int begin = (currpage - 1) * size + 1;
		int end = begin + size - 1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrhRental> rentals = prhRentalMapper.findCountByExpiringConditionAndPaged(condition);
		setGuideProperty(rentals);

		return rentals;
	}

	// 閫�绉熺粨绠�
	@Override
	public boolean updateTui(List<PrhRental> rentals) {

		System.out.println("璐﹀崟鎬绘暟" + rentals.size());
		try {

			for (PrhRental rental : rentals) {
				// 鏌ヨ鏈�澶d
				Integer maxid = prhPaymentMapper.findMaxId();
				if (maxid == null) {
					maxid = 1;
				}

				int mid = maxid + 1;

				// 宸茬粨娓�
				if (rental.getSta().equals("T")) {
					rental.setSta("M");

					Date now = new Date();
					boolean flag = true;

					int minus1 = 0;
					int minus2 = 0;

					if (rental.getBdate() != null) {
						minus1 = MyDateUtil.getDateNum(now, rental.getBdate());
						minus2 = MyDateUtil.getDateNum(now, rental.getEdate());
					} else {
						minus1 = 1;// 闅忔満璁剧疆澶т簬0鐨勬暟瀛�
						minus2 = 1;
					}

					// 璁＄畻閲戦
					Double money = 0.0;

					if (minus1 > 0) { // 鍏ㄩ閫�绉�
						money = rental.getRate();
					} else if (minus2 < 0) { // 涓嶉��娆�
						flag = false;

					} else if (minus1 < 0 && minus2 > 0) { // 褰撴湀闇�瑕佽绠�
						int day = MyDateUtil.getDateNum(now, rental.getEdate());
						if (day > 0) {
							money = rental.getRate() / 30.0 * day;
						} else {
							flag = false;// 涓嶉��娆�
						}
					}

					if (flag) {

						System.out.println(rental.getBdate());

						// 閫�绉熸祦姘�
						PrhPayment payment = new PrhPayment();
						payment.setPrtno(rental.getId().toString());
						payment.setCredit(money);
						payment.setLogdate(now);
						payment.setSysdate(now);
						payment.setId(mid);
						payment.setRef2("T");// 閫�绉熸爣璇�

						// 鎶奸噾銆佺閲� 鏍囪瘑
						if (rental.getInumber() == 0) {
							payment.setRef1("D");
						} else {
							payment.setRef1("R");
						}
						prhPaymentMapper.insert(payment);
					}
				}
				// 鏈粨娓�
				if (rental.getSta().equals("F")) {
					rental.setSta("N");
				}

				Integer f = prhRentalMapper.updateByPrimaryKey(rental);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
