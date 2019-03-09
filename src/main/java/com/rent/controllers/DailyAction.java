package com.rent.controllers;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rent.common.config.Global;
import com.rent.common.utils.RestfulUtil;
import com.rent.entity.DoorlockUser;
import com.rent.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.condition.HouseFileContion;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;

//@Service
//@Lazy(false)
public class DailyAction {

	@Autowired
	private FileManagementService fileManagementService;
	@Autowired
	private PrHouseService prHouseService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private BuildingNoService buildingNoService;
	@Autowired
	private BuildingFloorService buildingFloorService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private RenterService enterService;
	@Autowired
	private PrhPayCodeService prhPayCodeService;
	@Autowired
	private EstateService estateService;
	@Autowired
	private PrhMasterService prhMasterService;
	@Autowired
	private RenterService renterService;
	@Autowired
	private PaymentCodeService paymentCodeService;
	@Autowired
	private PrhPayMentService prhPayMentService;

	@Autowired
	private DoorlockUserService doorlockUserService;

	//@Scheduled(cron = "0 0 0 * * ?")
	public void updateRentPlan() {
		// 查询全部house信息 如果 调价日期 = 今天 那么 调价

		Integer flag = prHouseService.doPlan();
		if (flag == 1) {
			System.out.println("修改成功");
		} else if (flag == 0) {
			System.out.println("不修改租金");
		} else {
			System.out.println("修改失败");
		}
	}

	//@Scheduled(cron = "30 50 17 * * ?")
	public void test() {
		System.out.println("测试");
	}

	// 查询所有欠费的主单信息
	//@Scheduled(cron = "0 0 0 * * ?")
	public void checkArrears() {
		//查询所有在租的和续租的 用户的账单 根据 日期判断  是否 欠费修改其状态
		List<PrhRental> rentals = renterService.findArrears();
		System.out.println(rentals.size()+"查到多少条数据");
		//根据这些查到的数据查询 房间id
	
		boolean flag = prHouseService.ExplainPlan(rentals);
		
		if(flag){
			System.out.println("执行成功！");
		}else{
			System.out.println("执行失败！");
		}
		
	}

	/*退租*/
	public void deleteDoorlockUser() {
		List<PrhMaster> masterlist = prhMasterService.findMasterBySta(4);
		for(PrhMaster m : masterlist) {
			List<DoorlockUser> doorlockuserlist = doorlockUserService.findAvailableAllByMasterid(m.getId());
			for(DoorlockUser d: doorlockuserlist){
				String params = "{\"method\": \"thing.service.DeleteUser\",\"deviceid\": \""+d.getDeviceid()+"\",\"async\":true,\"nodeid\":1,\"params\":{\"UserType\":"+d.getUsertype()+",\"UserID\":"+d.getUsercode()+"}}";
				String result = RestfulUtil.postHttps(params,"lock");
				JSONObject resultMap = JSON.parseObject(result);
				int resultcode = resultMap.getIntValue("resultcode");
				String receipt = resultMap.getString("receipt");
				if(resultcode==1){
					d.setStatus(Global.STATUS_DELETEING);
					d.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
					d.setReceipt(receipt);
					doorlockUserService.updateByPrimaryKey(d);
				}
			}
		}
	}
	/*到期未续租，在租状态（1、6）*/
	public void deleteDoorlockUserWithOverdueMaster(){
		List<PrhMaster> masterlist = prhMasterService.findOverdueMasterOfRenting();
		for(PrhMaster m : masterlist) {
			List<DoorlockUser> doorlockuserlist = doorlockUserService.findAvailableAllByMasterid(m.getId());
			for(DoorlockUser d: doorlockuserlist){
				String params = "{\"method\": \"thing.service.DeleteUser\",\"deviceid\": \""+d.getDeviceid()+"\",\"async\":true,\"nodeid\":1,\"params\":{\"UserType\":\""+d.getUsertype()+"\",\"UserID\":"+d.getUsercode()+"}}";
				String result = RestfulUtil.postHttps(params,"lock");
				JSONObject resultMap = JSON.parseObject(result);
				int resultcode = resultMap.getIntValue("resultcode");
				String receipt = resultMap.getString("receipt");
				if(resultcode==1){
					d.setStatus(Global.STATUS_DELETEING);
					d.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
					d.setReceipt(receipt);
					doorlockUserService.updateByPrimaryKey(d);
				}
			}
		}
	}














}
