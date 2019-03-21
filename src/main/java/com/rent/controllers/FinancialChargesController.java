package com.rent.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rent.common.config.Global;
import com.rent.common.utils.MyDateUtil;
import com.rent.common.utils.NumPageUtil;
import com.rent.common.utils.RestfulUtil;
import com.rent.condition.HouseFileContion;
import com.rent.condition.RenDaliyCondition;
import com.rent.dao.PrhRentalMapper;
import com.rent.entity.*;
import com.rent.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("financialChargesController")
public class FinancialChargesController {

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
	@Autowired
	private PrhRentalMapper prhRentalMapper;

	// 进入日常合同扣租
	@RequestMapping("dailyContractRental")

	public String dailyContractRental(String danyuanid, Integer accnt, Integer estateId, String sta, String buildingId,
			String name, Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		if (request.getMethod().equals("GET")) {
			name = request.getParameter("name");
			buildingId = request.getParameter("buildingId");
			if (name != null) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			}
			if (buildingId != null) {
				buildingId = new String(buildingId.getBytes("iso-8859-1"), "utf-8");
			}
		}
		// 处理当前页
		RenDaliyCondition condition = new RenDaliyCondition();

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小

		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}

		// 查询所有单元集合
		List<BuildingNo> danyuan = null;
		if (estateId != null) {
			danyuan = buildingNoService.findByEstate(estateId);
		}

		if (accnt == null) {
			accnt = -1;
		}
		condition.setAccnt(accnt);

		if (danyuanid == null) {

			danyuanid = "";
		}
		if (danyuanid.equals("-1")) {
			danyuanid = "";
		}
		condition.setDanyuanid(danyuanid);
		condition.setEstateId(estateId);
		if (name == null) {
			name = "";
		}
		condition.setName(name);
		if (buildingId == null) {
			buildingId = "";
		}
		condition.setNo(buildingId);

		if (sta == null) {
			sta = "";
		}
		condition.setSta(sta);

		// 查询总数
		Integer total = prhMasterService.findCountByRenDaliyCondition(condition);
		// 查询所有房型集合

		// 分页查询
		List<PrhMaster> prhMaster = prhMasterService.findByRenDaliyConditionAndPaged(condition, currpage, size);
		// 查询总数
		// 分页工具类
		NumPageUtil page = new NumPageUtil("dailyContractRental?&accnt=" + accnt + "&estateId=" + estateId + "&sta="
				+ sta + "&name=" + name + "&buildingId=" + buildingId, total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域

		page.setList(prhMaster);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("tip", session.getAttribute("message"));
		session.removeAttribute("message");
		map.put("page", page);
		map.put("danyuan", danyuan);
		map.put("danyuanId", danyuanid);
		map.put("condition", condition);
		map.put("estateId", condition.getEstateId());
		map.put("estates", estates);
		return "prh/prMoney/dailyContractRental.jsp";
	}

	// 退组结算清账
	@RequestMapping("leaseSettlementClearing")
	public String leaseSettlementClearing(String src, String danyuanid, Integer accnt, Integer estateId, String sta,
			String buildingId, String name, Integer currpage, ModelMap map, HttpSession session,
			HttpServletRequest request) throws UnsupportedEncodingException {
		if (request.getMethod().equals("GET")) {
			name = request.getParameter("name");
			buildingId = request.getParameter("buildingId");
			if (name != null) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			}
			if (buildingId != null) {
				buildingId = new String(buildingId.getBytes("iso-8859-1"), "utf-8");
			}
		}
		// 处理当前页
		RenDaliyCondition condition = new RenDaliyCondition();

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小

		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}

		// 查询所有单元集合
		List<BuildingNo> danyuan = null;
		if (estateId != null) {
			danyuan = buildingNoService.findByEstate(estateId);
		}

		if (accnt == null) {
			accnt = -1;
		}
		condition.setAccnt(accnt);

		if (danyuanid == null) {

			danyuanid = "";
		}
		if (danyuanid.equals("-1")) {
			danyuanid = "";
		}
		condition.setDanyuanid(danyuanid);
		condition.setEstateId(estateId);
		if (name == null) {
			name = "";
		}
		condition.setName(name);
		if (src == null) {
			src = "";
		}
		condition.setSrc(src);
		if (buildingId == null) {
			buildingId = "";
		}
		condition.setNo(buildingId);

		if (sta == null) {
			sta = "";
		}
		condition.setSta(sta);

		Integer total = prhMasterService.findCountByRenDaliyConditionTui(condition);

		// 查询所有房型集合

		// 分页查询
		List<PrhMaster> prhMaster = prhMasterService.findByRenDaliyConditionAndPagedTui(condition, currpage, size);
		// 查询总数
		// 分页工具类
		NumPageUtil page = new NumPageUtil("leaseSettlementClearing?&accnt=" + accnt + "&estateId=" + estateId + "&sta="
				+ sta + "&name=" + name + "&buildingId=" + buildingId, total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域

		page.setList(prhMaster);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("tip", session.getAttribute("tip"));
		session.removeAttribute("tip");
		map.put("page", page);
		map.put("danyuan", danyuan);
		map.put("danyuanId", danyuanid);
		map.put("condition", condition);
		map.put("estateId", condition.getEstateId());
		map.put("estates", estates);

		return "prh/prMoney/leaseSettlementClearing.jsp";

	}

	// 租金审核调整页面
	@RequestMapping("rentAdjustmentAudit")
	public String rentAdjustmentAudit(Integer estateId, Integer roomtypeid, String danyuanid, String no,
			Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {
		// 处理当前页
		if (request.getMethod().equals("GET")) {
			no = request.getParameter("no");

			if (no != null) {
				no = new String(no.getBytes("iso-8859-1"), "utf-8");
			}
		}

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小
		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}

		HouseFileContion condition = new HouseFileContion();
		if (estateId == null) {
			estateId = -1;
		}
		if (roomtypeid == null) {
			roomtypeid = -1;
		}
		if (no == null) {
			no = "";
		}
		if (danyuanid == null) {
			danyuanid = "";
		}
		if (danyuanid.equals("-1")) {
			danyuanid = "";
		}
		condition.setDanyuanid(danyuanid);
		condition.setNo(no);
		condition.setRoomtypeId(roomtypeid);

		condition.setEstateId(estateId);
		List<PrHouse> PrHouse1 = prHouseService.findByFileCondition(condition);
		int total = PrHouse1.size();
		// 查询所有房型集合
		List<RoomType> roomtype = roomTypeService.findAll();
		List<BuildingNo> danyuan = buildingNoService.findByEstate(estateId);
		List<PrHouse> prHouses = prHouseService.findByFileConditionAndPaged(condition, currpage, size);

		// 查询总数

		// 分页工具类

		NumPageUtil page = new NumPageUtil("rentAdjustmentAudit?estateId=" + estateId + "&no=" + no + "&roomtypeid="
				+ roomtypeid + "&danyuanid=" + danyuanid, total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();
		// 根据房源id 查询租户信息姓名 的方法

		// 存作用域
		page.setList(prHouses);

		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("tip", session.getAttribute("message"));
		session.removeAttribute("message");

		map.put("tip", session.getAttribute("tip"));
		session.removeAttribute("tip");
		map.put("page", page);
		map.put("danyuan", danyuan);
		map.put("danyuanid", danyuanid);
		map.put("estateId", estateId);
		map.put("roomtypeid", roomtypeid);
		map.put("roomtype", roomtype);
		map.put("condition", condition);
		map.put("estates", estates);
		return "prh/prMoney/rentAdjustmentAudit.jsp";
	}

	// 租金批量修改
	// 数组接收复选框的id 根据id 查到
	@RequestMapping("updaterentAdjustmentAudit")
	public String updaterentAdjustmentAudit(String reason, String decDate, Integer estateId, Integer[] chk,
			String[] caname, String state, String no, Integer currpage, ModelMap map, HttpSession session,

			HttpServletRequest request) {

		if (chk.length != caname.length) {

			map.put("estateId", estateId);
			session.setAttribute("tip", "请选择修改过的选项或有选项未选中！");
			return "redirect:/ rentAdjustmentAudit?estateId=" + estateId;
		} else {
			// 传参数给service
			boolean flag = renterService.updateHousePrice(chk, caname, reason, decDate);
			if (flag) {
				map.put("estateId", estateId);
				session.setAttribute("tip", "修改成功！");
				return "redirect:/rentAdjustmentAudit?estateId=" + estateId;
			} else {
				map.put("estateId", estateId);
				session.setAttribute("tip", "批量修改出现错误,请重新尝试！");
				return "redirect:/rentAdjustmentAudit?estateId=" + estateId;
			}
		}

	}
	// 根据rentalid查询

	@RequestMapping("balance")
	public String balance(Integer chk, Integer estateId, ModelMap map, HttpSession session,
			HttpServletRequest request) {

		// 查询全部 付款方式
		Double zongjine = 0.0;
		List<PrhPaycode> pcode = prhPayCodeService.findAllPayCode();
		// 根据 主单id 查询 所有的 rental 中信息

		List<PrhRental> rentals = renterService.findByAccnt(chk);
		// 查询所有金额综合
		for (PrhRental prhRental : rentals) {
			;
			zongjine = prhRental.getRate() + zongjine;
		}

		// 查询已付清
		List<PrhRental> rentalsF = new ArrayList<>();
		// 分两个集合
		for (PrhRental prhRental : rentals) {

			if (prhRental.getSta().equals("F")) {
				rentalsF.add(prhRental);
			}
		}
		// 根据主单id 查询账务表id 查询 付款详情

		List<PrhPayment> prhpaymentsT = prhPayMentService.findByAccntId(chk);

		// 保存 此主单用户的信息

		map.put("tip", session.getAttribute("tip"));
		session.removeAttribute("tip");
		PrhMaster prhMaster = prhMasterService.findById(chk);
		map.put("pcode", pcode);
		map.put("prhMaster", prhMaster);
		map.put("rentalsT", prhpaymentsT);
		map.put("rentalsTsize", prhpaymentsT.size());
		map.put("rentalsF", rentalsF);
		map.put("rentalsFsize", rentalsF.size());
		map.put("zong", zongjine);

		return "prh/prMoney/dailyContractRental2.jsp";
	}

	// 日常租金付款
	@RequestMapping("payForm")
	public String payForm(Integer[] chk, String pay, String ino, Integer payType, Integer accntid,String batch, ModelMap map,
			HttpSession session, HttpServletRequest request) throws InterruptedException {
		
		if (payType == -1 || ino == null || ino == ""||batch==null||batch.length()==0) {
			session.setAttribute("tip", "请填写*必填信息!");
			return "redirect:/balance?chk=" + accntid;
		}

		// 根据 rental 的 id 循环 每一个对象
		Integer flag = renterService.payMoneyAndUpdateAndInsert(chk, ino, pay, payType,batch);
		if (flag == 1) {
			// 修改不成功
			session.setAttribute("tip", "付款失败。");

			return "redirect:/balance?chk=" + accntid;
		} else if (flag == 2) {
			session.setAttribute("tip", "付款失败.");
			return "redirect:/balance?chk=" + accntid;
			// 添加账务付款失败
		} else if (flag == 3) {
			List<PrhRental> paidByMasterId = prhRentalMapper.findPaidByMasterId(accntid);
			Date end = paidByMasterId.get(0).getEdate();
			List<DoorlockUser> inUseByMasterid = doorlockUserService.findInUseByMasterid(accntid);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for(DoorlockUser d: inUseByMasterid){
				if(d.getValidthrough().getTime()<end.getTime()) {
					String validfrom2 = sdf.format(d.getValidfrom()) + " 00:00";
					String validthrough2 = sdf.format(end) + " 23:59";
					Map<String, Object> resultMap = updateValidTimeRequest(d.getDeviceid(),  d.getUsercode(), validfrom2, validthrough2);
					Thread.sleep(1000);
					if ((Integer) resultMap.get("resultcode") == 1) {
						d.setStatus(Global.STATUS_UPDATING_TIME);
						d.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
						d.setReceipt((String) resultMap.get("receipt"));
						try {
							d.setValidfrom(simpledf.parse(validfrom2));
							d.setValidthrough(simpledf.parse(validthrough2));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						doorlockUserService.updateByPrimaryKey(d);
					}
				}
			}
			session.setAttribute("tip", "付款成功.");
			return "redirect:/balance?chk=" + accntid;
			// 两个都成功了
		} else {
			// 未知错误
			session.setAttribute("tip", "已由其他操作员签约，请刷新页面.");
			return "redirect:/balance?chk=" + accntid;
		}

	}
	public Map<String,Object> updateValidTimeRequest(String associatedlock,int usercode,String validfrom,String validthrough){
		String params = "{\"method\": \"thing.service.UpdateUserValidDateTime\",\"async\":true,\"deviceid\": \""+associatedlock+"\",\"nodeid\": 1,\"params\": {\"UserID\": "+usercode+"," +
				"\"ValidBeginDateTime\": \""+validfrom+"\",\"ValidEndDateTime\": \""+validthrough+"\"}}";
		String result = RestfulUtil.postHttps(params,"lock");
		JSONObject resultMap = JSON.parseObject(result);
		int resultcode = resultMap.getIntValue("resultcode");
		String receipt = resultMap.getString("receipt");
		Map<String,Object> map = new HashMap<>();
		map.put("resultcode",resultcode);
		map.put("receipt",receipt);
		return map;
	}
	@RequestMapping("updateRentalstaAndupdatePaymentSta")
	public String updateRentalstaAndupdatePaymentSta(Integer[] chk2, Integer accntid2, ModelMap map,
			HttpSession session, HttpServletRequest request) {

		// 跳转到 修改页面传一些值
		// 查询全部 物业 分类 ，查询 全部
		// 循环 方法
		Integer flag = renterService.updateandinsert(chk2);
		if (flag == 3) {
			session.setAttribute("tip", "作废成功");
			return "redirect:/balance?chk=" + accntid2;
		} else if (flag == 2) {
			session.setAttribute("tip", "作废账务付款成功！");
			return "redirect:/balance?chk=" + accntid2;
		} else if (flag == 1) {
			session.setAttribute("tip", "作废账务失败");
			return "redirect:/balance?chk=" + accntid2;
		} else {
			session.setAttribute("tip", "废作废账务失败");
			return "redirect:/balance?chk=" + accntid2;
		}

	}

	@RequestMapping("batchAuditcenter")
	public String batchAudit(String money, Integer estateId, Integer roomtypeid, String danyuanid, String no,
			ModelMap map, HttpSession session, HttpServletRequest request) {
		// 跳转到 修改页面传一些值
		//

		HouseFileContion condition = new HouseFileContion();
		if (estateId == null) {
			estateId = -1;
		}
		if (roomtypeid == null) {
			roomtypeid = -1;
		}
		if (no == null) {
			no = "";
		}
		if (danyuanid == null) {
			danyuanid = "";
		}
		if (danyuanid.equals("-1")) {
			danyuanid = "";
		}
		condition.setDanyuanid(danyuanid);
		condition.setNo(no);
		condition.setRoomtypeId(roomtypeid);
		condition.setEstateId(estateId);
		List<PrHouse> houses = prHouseService.findByFileCondition(condition);
		Integer count = houses.size();
		// 查询全部 物业 分类 ，查询 全部
		map.put("count", count);
		map.put("estateId", estateId);
		map.put("roomtypeid", roomtypeid);
		map.put("danyuanid", danyuanid);
		map.put("no", no);
		map.put("condition", condition);
		return "prh/prMoney/batchRentAccount.jsp";
	}

	@RequestMapping("leaseSettlement.do")
	public String leaseSettlement(Integer chk, Integer estateId, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 跳转到 修改页面传一些值
		// 查询全部 付款方式
		List<PrhPaycode> pcode = prhPayCodeService.findAllPayCode();
		// 根据 主单id 查询 所有的 rental 中信息

		List<PrhRental> rentals = renterService.findByMasterId(chk);
		// 修改状态

		// 查询已付清
		Double yajin = 0.0;
		Double yifuqing = 0.0;
		Double weifuqing = 0.0;

		BigDecimal tuiqian = new BigDecimal(0.0);
		Double money = 0.0;
		Double desposit = 0.0;
		Date jiaozuriqi = new Date();
		for (PrhRental prhRental : rentals) {
			// 计算账务
			if (prhRental.getInumber() == 0) {
				yajin = prhRental.getRate();
			}
			if (prhRental.getSta().equals("T")) {
				yifuqing = yifuqing + prhRental.getRate();
				jiaozuriqi = prhRental.getEdate();
			}
			if (prhRental.getSta().equals("F")) {
				weifuqing = weifuqing + prhRental.getRate();
			}
			// 计算应退金额

			// 计算金额

			if (prhRental.getSta().equals("T")) {

				Date now = new Date();

				int minus1 = 0;
				int minus2 = 0;

				if (prhRental.getBdate() != null) {
					minus1 = MyDateUtil.getDateNum(now, prhRental.getBdate());
					minus2 = MyDateUtil.getDateNum(now, prhRental.getEdate());
				} else {
					minus1 = 1;// 随机设置大于0的数字
					minus2 = 1;
				}

				if (minus1 > 0) { // 全额退租

					if (prhRental.getInumber() == 0) {
						desposit += prhRental.getRate();
					} else {
						money += prhRental.getRate();
					}

				} else if (minus2 < 0) { // 不退款

				} else if (minus1 < 0 && minus2 > 0) { // 当月需要计算

					int day = MyDateUtil.getDateNum(now, prhRental.getEdate());
					if (day > 0) {
						money += prhRental.getRate() / 30.0 * day;
					}
				}

			}
		}
		PrhMaster prhMaster = prhMasterService.findById(chk);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		tuiqian = BigDecimal.valueOf((double) Math.round(money * 100) / 100);
		// 计算应退金额

		Double hetongmoney = yifuqing + weifuqing;
		// 根据主单id 查询账务表id 查询 付款详情
		map.put("yajin", yajin);
		map.put("yifuqing", yifuqing);
		map.put("weifuqing", weifuqing);
		map.put("hetongmoney", hetongmoney);
		map.put("tuiqian", tuiqian);
		// 保存 此主单用户的信息

		map.put("tip", session.getAttribute("tip"));
		session.removeAttribute("tip");

		map.put("pcode", pcode);
		map.put("prhMaster", prhMaster);
		map.put("rensize", rentals.size());
		map.put("rentals", rentals);

		// 押金为0z则有可能为续租

		if (desposit == 0) {
			while (true) {

				if (prhMaster.getOldMasterId() == null) {
					System.out.println("没有申请");
					desposit = prhMaster.getDeposit().doubleValue();
					break;
				} else {
					System.out.println("申请了");
					PrhMaster oldMaster = prhMasterService.findById(prhMaster.getOldMasterId());
					prhMaster = prhMasterService.findById(oldMaster.getId());
				}

			}
		}
		map.put("desposit", desposit);
		System.out.println("转到结算页面");
		return "prh/prMoney/jiesuan.jsp";

	}

	@RequestMapping("jiesuanForm")
	public String jiesuanForm(Integer masterid, Double tuiMoney, Double desposit,Double collectMoney, ModelMap map, HttpSession session,
			HttpServletRequest request) {
		PrhMaster prhMaster = prhMasterService.findById(masterid);

		Integer count = 0;
		// 修改状态
		prhMaster.setSrc("1");
		if (prhMaster.getSta().equals("4")) {
			session.setAttribute("tip", "该合同已退租完成,无法重复退租！");
			return "redirect:/leaseSettlementClearing";
		}

		List<PrhRental> rentals = renterService.findByAccnt(masterid);

		boolean flag = renterService.updateTui(rentals);
		if (flag == false) {
			session.setAttribute("tip", "修改账单状态时出现错误！");
			return "redirect:/leaseSettlementClearing";
		}
		// 退租金
		prhMaster.setTuidesposit(desposit);
		prhMaster.setTuimoney(tuiMoney);
		prhMaster.setTuidate(new Date());
		prhMaster.setCollectMoney(collectMoney);
		count = prhMasterService.updatePrhMaster(prhMaster);

		if (count == 1) {
			session.setAttribute("tip", "退租核算完成");
			return "redirect:/leaseSettlementClearing";
		} else {
			session.setAttribute("tip", "退租核算失败");
			return "redirect:/leaseSettlementClearing";
		}
	}

	// 租金核算
	@RequestMapping("rentAccounting")
	public String rentAccounting(String src, String danyuanid, Integer accnt, Integer estateId, String sta,
			String buildingId, String name, Integer currpage, ModelMap map, HttpSession session,
			HttpServletRequest request) throws UnsupportedEncodingException {

		if (request.getMethod().equals("GET")) {
			name = request.getParameter("name");
			buildingId = request.getParameter("buildingId");
			if (name != null) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			}
			if (buildingId != null) {
				buildingId = new String(buildingId.getBytes("iso-8859-1"), "utf-8");
			}
		}
		// 查询物业集合

		// 处理当前页
		RenDaliyCondition condition = new RenDaliyCondition();

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小

		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		List<BuildingNo> danyuan = null;
		// 查询所有单元集合
		if (estateId != null) {
			danyuan = buildingNoService.findByEstate(estateId);
		}
		if (accnt == null) {
			accnt = -1;
		}
		condition.setAccnt(accnt);
		if (src == null) {
			src = "";
		}
		condition.setSrc(src);
		if (danyuanid == null) {

			danyuanid = "";
		}
		if (danyuanid.equals("-1")) {
			danyuanid = "";
		}
		condition.setDanyuanid(danyuanid);
		condition.setEstateId(estateId);
		if (name == null) {
			name = "";
		}
		condition.setName(name);
		if (buildingId == null) {
			buildingId = "";
		}
		condition.setNo(buildingId);

		if (sta == null) {
			sta = "";
		}
		condition.setSta(sta);

		// 查询总数

		Integer total = prhMasterService.findCountByRenDaliyConditionHe(condition);

		// 查询所有房型集合

		// 分页查询
		List<PrhMaster> prhMaster = prhMasterService.findByRenDaliyConditionAndPagedHe(condition, currpage, size);
		// 查询总数
		// 分页工具类
		NumPageUtil page = new NumPageUtil("rentAccounting?&accnt=" + accnt + "&estateId=" + estateId + "&sta=" + sta
				+ "&name=" + name + "&buildingId=" + buildingId, total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域

		page.setList(prhMaster);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("tip", session.getAttribute("message"));
		session.removeAttribute("message");
		map.put("page", page);
		map.put("danyuan", danyuan);
		map.put("danyuanId", danyuanid);
		map.put("condition", condition);
		map.put("estateId", condition.getEstateId());
		map.put("estates", estates);
		return "prh/prMoney/rentAccounting.jsp"; // 处理当前页
	}

	@RequestMapping("historyRental")
	public String historyRental(Integer masterId,

			ModelMap map, HttpSession session, HttpServletRequest request) {

		List<PrhPayment> prhpaymentsT = new ArrayList<>();
		// 根据id查询 房子id
		PrhMaster master = prhMasterService.findById(masterId);
		String guestno = master.getGuestNo();
		List<PrhMaster> master2 = prhMasterService.findHistory(guestno);

		if (master2.size() > 0) {

			// 查询 账务信息
			for (PrhMaster prhMaster : master2) {
				List<PrhPayment> payments = prhPayMentService.findByAccntId(prhMaster.getId());

				prhpaymentsT.addAll(payments);
			}
		}
		map.put("rentalsT", prhpaymentsT);
		map.put("rentalsTsize", master2.size());
		return "prh/prMoney/historyRental.jsp";
	}

	@RequestMapping("balance2")
	public String balance2(Integer chk, Integer estateId, ModelMap map, HttpSession session,
			HttpServletRequest request) {
		// 查询全部 付款方式
		List<PrhPaycode> pcode = prhPayCodeService.findAllPayCode();
		// 根据 主单id 查询 所有的 rental 中信息

		List<PrhRental> rentals = renterService.findByAccnt(chk);

		// 查询已付清
		List<PrhRental> rentalsF = new ArrayList<>();
		// 分两个集合
		for (PrhRental prhRental : rentals) {

			if (prhRental.getSta().equals("F")) {
				rentalsF.add(prhRental);
			}
		}
		// 根据主单id 查询账务表id 查询 付款详情

		List<PrhPayment> prhpaymentsT = prhPayMentService.findByAccntId(chk);

		// 保存 此主单用户的信息

		map.put("tip", session.getAttribute("tip"));
		session.removeAttribute("tip");
		PrhMaster prhMaster = prhMasterService.findById(chk);
		map.put("pcode", pcode);
		map.put("prhMaster", prhMaster);
		map.put("rentalsT", prhpaymentsT);
		map.put("rentalsTsize", prhpaymentsT.size());
		map.put("rentalsF", rentalsF);
		map.put("rentalsFsize", rentalsF.size());
		return "prh/prMoney/rentAccounting2.jsp";
	}

	@RequestMapping("accounting")
	public String accounting(Integer[] chk2, Integer accntid2, Integer userId, ModelMap map, HttpSession session,
			HttpServletRequest request) {

		// 跳转到 修改页面传一些值
		// 查询全部 物业 分类 ，查询 全部
		// 修改状态rentals状态

		Integer flag = renterService.accountRental(chk2, userId);
		// 判断 是否 所有 rental 都已经核算完毕
		Integer flag2 = renterService.checkRefer(accntid2);

		if (flag == 2 && flag2 == 1) {
			session.setAttribute("tip", "全部核算成功");
			return "redirect:/balance2?chk=" + accntid2;
		} else if (flag == 1) {
			session.setAttribute("tip", "核算失败！");
			return "redirect:/balance2?chk=" + accntid2;
		} else if (flag == 2) {
			session.setAttribute("tip", "核算成功！");
			return "redirect:/balance2?chk=" + accntid2;
		} else {
			session.setAttribute("tip", "核算失败");
			return "redirect:/balance2?chk=" + accntid2;
		}
	}

	@RequestMapping("plan")
	public void plan(Double rentMod, String reason, String decMan, String decDate, Integer roomtypeId, Integer estateId,
			String no, String danyuanid, ModelMap map, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HouseFileContion condition = new HouseFileContion();
		condition.setDanyuanid(danyuanid);
		condition.setEstateId(estateId);
		condition.setRoomtypeId(roomtypeId);
		condition.setNo(no);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟

		Date date = new Date();
		try {
			date = sdf.parse(decDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 根据这些条件查询出所有房子 然后 修改他们当中的值
		List<PrHouse> houses = prHouseService.findByFileCondition(condition);
		boolean flag = prHouseService.plan(houses, rentMod, reason, decMan, date);
		// 查到之后 改变状态和内容

		if (flag) {
			session.setAttribute("tip", "批量调整租金成功");
			map.put("tip", "批量调整租金成功");
			PrintWriter out = response.getWriter();

			out.flush();
			out.println("<script>");
			out.println("parent.layer.closeAll();");
			out.println("</script>");
		} else {
			map.put("tip", "批量调整租金失败");
			session.setAttribute("tip", "批量调整租金失败");
			PrintWriter out = response.getWriter();

			out.flush();
			out.println("<script>");
			out.println("parent.layer.closeAll();");
			out.println("</script>");
		}

	}

	@RequestMapping("historyMaster")
	public String historyMaster(Integer houseid, ModelMap map, HttpSession session, HttpServletRequest request) {
		List<PrhMaster> masters = prhMasterService.findByHouseId(houseid);
		map.put("masters", masters);
		map.put("size", masters.size());
		return "prh/prMoney/historyMaster.jsp";
	}

	@RequestMapping("ledger")
	public String ledger(String danyuanid, Integer accnt, Integer estateId, String sta, String buildingId, String name,
			Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		if (request.getMethod().equals("GET")) {
			name = request.getParameter("name");
			buildingId = request.getParameter("buildingId");
			if (name != null) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			}
			if (buildingId != null) {
				buildingId = new String(buildingId.getBytes("iso-8859-1"), "utf-8");
			}
		}
		// 处理当前页
		// 处理sta
		if (sta == null) {
			sta = "1";
		}
		if (sta.equals("1")) {// 欠费 分页
			// 查询物业集合
			List<Estate> estates = prHouseService.getAllEstate();
			if (estateId == null) {
				if (estates != null && estates.size() != 0) {
					estateId = estates.get(0).getId();
				}
			}
			RenDaliyCondition condition = new RenDaliyCondition();

			// 查询所有单元集合
			List<BuildingNo> danyuan = null;
			if (estateId != null) {
				danyuan = buildingNoService.findByEstate(estateId);
			}

			if (currpage == null || currpage <= 0) {
				currpage = 1;
			}
			Integer size = 10; // 页大小

			if (accnt == null) {
				accnt = -1;
			}
			condition.setAccnt(accnt);

			if (danyuanid == null) {

				danyuanid = "";
			}
			if (danyuanid.equals("-1")) {
				danyuanid = "";
			}
			condition.setDanyuanid(danyuanid);
			condition.setEstateId(estateId);
			if (name == null) {
				name = "";
			}
			condition.setName(name);
			if (buildingId == null) {
				buildingId = "";
			}
			condition.setNo(buildingId);

			if (sta.equals("2")) {
				condition.setToday(new Date());
			}

			// 查询总数
			Integer total = prhMasterService.findCountByRenDaliyConditionTai(condition);

			// 查询所有房型集合

			// 分页查询
			List<PrhMaster> prhMaster = prhMasterService.findCountByRenDaliyConditionTaiAndPaged(condition, currpage,
					size);
			// 查询总数
			// 分页工具类
			NumPageUtil page = new NumPageUtil("ledger?&accnt=" + accnt + "&estateId=" + estateId + "&sta=" + sta
					+ "&name=" + name + "&buildingId=" + buildingId, total, currpage, size);

			page.setBothnum(3);
			String numpage = page.showNumPage();
			currpage = page.getCurrpage();

			// 存作用域

			page.setList(prhMaster);
			session.setAttribute("currpage", currpage);
			request.setAttribute("numpage", numpage);

			map.put("tip", session.getAttribute("message"));
			session.removeAttribute("message");
			map.put("page", page);
			map.put("sta", sta);
			map.put("danyuan", danyuan);
			map.put("danyuanId", danyuanid);
			map.put("size", prhMaster.size());
			map.put("condition", condition);
			map.put("estateId", condition.getEstateId());
			map.put("estates", estates);
			map.put("flag", "true");
			return "prh/ledger/ledger.jsp";
		} else {
			RenDaliyCondition condition = new RenDaliyCondition();
			condition.setToday(new Date());
			// 查询物业集合
			List<Estate> estates = prHouseService.getAllEstate();
			if (estateId == null) {
				if (estates != null && estates.size() != 0) {
					estateId = estates.get(0).getId();
				}
			}
			// 查询所有单元集合
			List<BuildingNo> danyuan = buildingNoService.findByEstate(estateId);

			if (currpage == null || currpage <= 0) {
				currpage = 1;
			}
			Integer size = 10; // 页大小

			if (accnt == null) {
				accnt = -1;
			}
			condition.setAccnt(accnt);

			if (danyuanid == null) {

				danyuanid = "";
			}
			if (danyuanid.equals("-1")) {
				danyuanid = "";
			}
			condition.setDanyuanid(danyuanid);
			condition.setEstateId(estateId);
			if (name == null) {
				name = "";
			}
			condition.setName(name);
			if (buildingId == null) {
				buildingId = "";
			}
			condition.setNo(buildingId);

			if (sta.equals("2")) {
				condition.setToday(new Date());
			}

			// 查询总数
			Integer total = renterService.findCountByExpiringCondition(condition);
			// 查询所有房型集合

			// 分页查询
			List<PrhRental> renters = renterService.findCountByExpiringConditionAndPaged(condition, currpage, size);

			// 查询总数
			// 分页工具类
			NumPageUtil page = new NumPageUtil("ledger?&accnt=" + accnt + "&estateId=" + estateId + "&sta=" + sta
					+ "&name=" + name + "&buildingId=" + buildingId, total, currpage, size);

			page.setBothnum(3);

			String numpage = page.showNumPage();
			currpage = page.getCurrpage();

			// 存作用域

			page.setList(renters);
			session.setAttribute("currpage", currpage);
			request.setAttribute("numpage", numpage);

			map.put("tip", session.getAttribute("message"));
			session.removeAttribute("message");
			map.put("page", page);
			map.put("sta", sta);
			map.put("danyuan", danyuan);
			map.put("danyuanId", danyuanid);
			map.put("size", renters.size());
			map.put("condition", condition);
			map.put("estateId", condition.getEstateId());
			map.put("estates", estates);
			map.put("flag", "other");
			return "prh/ledger/ledger.jsp";

		}

	}

}
