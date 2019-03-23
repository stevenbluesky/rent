package com.rent.controllers;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rent.common.utils.*;
import com.rent.dao.*;
import com.rent.entity.*;
import com.rent.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rent.common.config.Global;
import com.rent.condition.MasterReletCondition;

@Controller("codeController")
public class CodeController {
	
	@Autowired
	private PrhTempLiveManService prhTempLiveManService; 
	@Autowired
	private PrhTempLiveManMapper prhTempLiveManMapper;
	
	@Autowired
	private PrhMasterService prhMasterService;

	@Autowired
	private BuildingNoService buildingNoService;
	@Autowired
	private EstateService estateService;
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private CardService cardService;
	@Autowired
	private ProfileMapper profileMapper;

	@Autowired
	private DoorlockUserService doorlockUserService;
	@Autowired
	private PrhRentalMapper prhRentalMapper;
	public CardService getCardService() {
		return cardService;
	}
	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}
	@Autowired
	private PrhLinkManService prhLinkManService;
	@Autowired
	private DoorlockUserMapper doorlockUserMapper;
	
	public PrhLinkManService getPrhLinkManService() {
		return prhLinkManService;
	}


	public void setPrhLinkManService(PrhLinkManService prhLinkManService) {
		this.prhLinkManService = prhLinkManService;
	}


	public PrhMasterService getPrhMasterService() {
		return prhMasterService;
	}


	public void setPrhMasterService(PrhMasterService prhMasterService) {
		this.prhMasterService = prhMasterService;
	}


	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}


	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}


	public EstateService getEstateService() {
		return estateService;
	}


	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}


	public BuildingService getBuildingService() {
		return buildingService;
	}


	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
	@Autowired
	private PrHouseService prHouseService;
	
	
	public PrHouseService getPrHouseService() {
		return prHouseService;
	}
	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}
	
	public PrhTempLiveManService getPrhTempLiveManService() {
		return prhTempLiveManService;
	}
	public void setPrhTempLiveManService(PrhTempLiveManService prhTempLiveManService) {
		this.prhTempLiveManService = prhTempLiveManService;
	}
	
	public PrhTempLiveManMapper getPrhTempLiveManMapper() {
		return prhTempLiveManMapper;
	}
	public void setPrhTempLiveManMapper(PrhTempLiveManMapper prhTempLiveManMapper) {
		this.prhTempLiveManMapper = prhTempLiveManMapper;
	}
	// 列表
	@RequestMapping("findCode.do")
	public String findCheckInRecord(Integer currpage, Integer estateId, String name, String roomNo,String buildingNoId,
			String buildingId, MasterReletCondition condition, HttpServletRequest request, HttpSession session, ModelMap map)
			throws UnsupportedEncodingException {
		if (request.getMethod().equals("GET")) {
			name= request.getParameter("name");
			roomNo=request.getParameter("roomNo");
			if (name!=null) {
				name= new String(name.getBytes("iso-8859-1"),"utf-8");	
			}
			if (roomNo!=null) {
				roomNo= new String(roomNo.getBytes("iso-8859-1"),"utf-8");
			}
		}
		int size = 10;
		// 条件查询
	
		if (name != null&&name.length()==0) {
			name = null;
		}
		if (roomNo !=null && roomNo.length()==0) {
			roomNo =null;
		}
		
		// 物业集合
		List<Estate> estates = estateService.findAll();
		Estate estate=null;
		if (estateId == null) {
			estate = MyRentBiz.getEstates(estates);
			if (estate != null) {
				estateId = estate.getId();
			}
		}
		estate=estateService.findById(estateId);
		
		if ("-1".equals(buildingId)) {
			buildingId=null;
		}
		if ("-1".equals(buildingNoId)	) {
			buildingNoId=null;
		}
		if (estateId!=null) {
			
		
		//单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
		map.put("buildingNos", buildingNos);
		
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(estateId);
		map.put("buildings", buildings);
		}
		
		condition = new MasterReletCondition(currpage, size, estateId, name, roomNo);
		condition.setBuildingNoId(buildingNoId);
		condition.setBuildingId(buildingId);
		condition.setHasCommonCard(-1);
		List<PrhMaster> masters = prhMasterService.findCanGetCardPaged(condition);
		condition.setEstateId(estateId);
		
		// 分页查询tab1
	
		String url = "findCode.do?name=" + (name!=null?name:"") 
				                        + "&roomNo=" + (roomNo!=null?roomNo:"")
				                        + "&buildingId="+(buildingId!=null?buildingId:"-1")
				                        + "&buildingNoId="+(buildingNoId!=null?buildingNoId:"-1")
				                        + "&estateId=" + estateId;
		NumPageUtil page = new NumPageUtil(url, prhMasterService.getCanGetCardCount(condition), currpage,
				size, masters, session, request);

		// map
		map.put("page", page);
		map.put("estates", estates);
		map.put("estate", estate);
		map.put("estateId", estateId);
		map.put("condition", condition);
		return "renter/code/codeList.jsp";
	}

	//转到卡管理
	@RequestMapping("toCardAdd.do")
	public String toCardAdd(Integer masterId, ModelMap map){
		//主单
		PrhMaster master = prhMasterService.findById(masterId);
		map.put("master", master);
		//同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(masterId);
		map.put("linkMans", linkMans);
		List<PrhTempLiveMan> tempMans = prhTempLiveManService.findByMaster(masterId);
		map.put("tempMans", tempMans);
		
		return "renter/code/codeAdd.jsp";
	}

	@RequestMapping("toModifyValidTime.do")
	public String toModifyValidTime(Integer masterId, ModelMap map){
		PrhMaster master = prhMasterService.findById(masterId);
		map.put("master", master);
		return "renter/code/modifyValidTimePage.jsp";
	}

	@RequestMapping("modifyValidTime.do")
	@ResponseBody
	public String modifyValidTime(Integer masterId,String validfrom,String validthrough,String reason, ModelMap map){
		PrhMaster master = prhMasterService.findById(masterId);
		PrHouse house = prHouseService.findById(master.getHouseId());
		if(StringUtils.isBlank(house.getAssociatedlock())){
			return "-2";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String validfrom2 = validfrom + " 00:00";
		String validthrough2 = validthrough + " 23:59";
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(validfrom2);
			end = sdf.parse(validthrough2);
		} catch (ParseException e) {
			return "-1";
		}
		List<DoorlockUser> inUseByMasterid = doorlockUserService.findInUseByMasterid(masterId);
		for(DoorlockUser d: inUseByMasterid){
			Map<String, Object> resultMap = sendPasswordRequest(d.getDeviceid(), d.getUsertype(), d.getUsercode(), d.getPassword(), validfrom2, validthrough2);
			if((Integer) resultMap.get("resultcode")==1){
				d.setStatus(Global.STATUS_UPDATING_TIME);
				d.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
				d.setValidfrom(begin);
				d.setValidthrough(end);
				d.setReason(reason);
				d.setReceipt((String)resultMap.get("receipt"));
				doorlockUserService.updateByPrimaryKey(d);
			}
		}
		return "1";
	}

	@RequestMapping("toAddLockuserPage.do")
	public String toAddLockuserPage(String idStr,ModelMap map){
		Integer masterId=null;
		Integer linkId=null;
		Integer personType=null;
		String guestNo = "";
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring((idStr.indexOf("-")+1),idStr.indexOf("@")));
		}else{
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("@")));
		}
		personType = Integer.valueOf(idStr.substring(idStr.indexOf("@")+1));
		PrhMaster master = prhMasterService.findById(masterId);
		if(linkId==null){
			guestNo = master.getGuestNo();
		}else if(personType.intValue()==2){
			guestNo = prhLinkManService.findById(linkId).getGuestno();
		}else{
			guestNo = prhTempLiveManMapper.selectByPrimaryKey(linkId).getGuestno();
		}
		Profile profile = profileMapper.findBygusetNo(guestNo);
		map.put("master", master);
		map.put("profile",profile);
		map.put("locklist",findFingerDevice());
		return "renter/code/lockuserAdd.jsp";

	}
	private List<Lock> findFingerDevice(){
		String params = "{\"method\":\"thing.service.GetNodeList\",\"params\":{\"Conditions\":{\"_nodetype\":\"fingerprintreader\",\"_nodeid\":1}}}";
		String result = RestfulUtil.postHttps(params,"app");
		JSONArray pageArray = null;
		List<Lock> pageList = new ArrayList<>();
		int totalCount = 0;
		// 处理数据
		JSONObject resultMap = JSON.parseObject(result);
		int resultcode = resultMap.getIntValue("resultcode");
		if (!RestfulUtil.checkNull(resultMap.getJSONObject("data"))) {
			JSONObject data = resultMap.getJSONObject("data");
			totalCount = data.getIntValue("TotalCount");
			if (totalCount > 0) {
				pageArray = data.getJSONArray("PageList");
				for (int i = 0; i < pageArray.size(); i++) {
					Lock lock = JSON.parseObject(pageArray.get(i).toString(), Lock.class);
					pageList.add(lock);
				}
			}
		}
		return pageList.size()>0?pageList:null;
	}
	@RequestMapping("toDeleteLockUser.do")
	public String toDeleteLockUser(String doorlockuserid,ModelMap map){
		map.put("doorlockuserid", doorlockuserid);
		return "renter/code/deleteLockUserPage.jsp";
	}

	@RequestMapping("deletePassword.do")
	@ResponseBody
	public String deletePassword(String doorlockuserid,String reason,ModelMap map){
		DoorlockUser doorlockUser = doorlockUserService.selectByPrimaryKey(Integer.parseInt(doorlockuserid));
		if(doorlockUser.getStatus()==Global.STATUS_SEND_FAILED){
			doorlockUser.setStatus(Global.STATUA_DELETED);
			doorlockUser.setReason(reason);
			doorlockUserService.updateByPrimaryKey(doorlockUser);
			return "1";
		}
		String params = "{\"method\": \"thing.service.DeleteUser\",\"deviceid\": \""+doorlockUser.getDeviceid()+"\",\"async\":true,\"nodeid\":1,\"params\":" +
				"{\"UserType\":"+doorlockUser.getUsertype()+",\"UserID\":"+doorlockUser.getUsercode()+"}}";
		String result = RestfulUtil.postHttps(params,"lock");
		JSONObject resultMap = JSON.parseObject(result);
		int resultcode = resultMap.getIntValue("resultcode");
		String receipt = resultMap.getString("receipt");
		if(resultcode==1){
			doorlockUser.setStatus(Global.STATUS_DELETEING);
			doorlockUser.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
			doorlockUser.setReceipt(receipt);
			doorlockUser.setReason(reason);
			doorlockUserService.updateByPrimaryKey(doorlockUser);
			return "1";
		}
		return "-1";
	}


	@RequestMapping("toLockUserPage.do")
	public String toLockUserPage(String idStr,ModelMap map){
		Integer masterId=null;
		Integer linkId=null;
		Integer personType=null;
		String guestNo = "";
		int ifTempLiveMan = 0;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring((idStr.indexOf("-")+1),idStr.indexOf("@")));
		}else{
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("@")));
		}
		personType = Integer.valueOf(idStr.substring(idStr.indexOf("@")+1));
		PrhMaster master = prhMasterService.findById(masterId);
		if(linkId==null){
			guestNo = master.getGuestNo();
		}else if(personType.intValue()==2){
			guestNo = prhLinkManService.findById(linkId).getGuestno();
		}else{
			ifTempLiveMan = 1;
			guestNo = prhTempLiveManMapper.selectByPrimaryKey(linkId).getGuestno();
		}
		map.put("master", master);
		map.put("ifTempLiveMan",ifTempLiveMan);
		List<DoorlockUser> lockuserlist = doorlockUserService.findAllByGuestno(guestNo);
		map.put("lockuserlist",lockuserlist);
		return "renter/code/lockUser.jsp";
	}
	@RequestMapping("addPassword.do")
	@ResponseBody
	public String addPassword(Integer masterId,String guestNo, String userName, String mobilePhone, String password, String idcard, String mfcard,String fingerprint) throws InterruptedException {
		int errorResult = 0;
		Date startdate = new Date();
		Date enddate = null;
		List<PrhRental> paidByMasterId = prhRentalMapper.findPaidByMasterId(masterId);
		if(paidByMasterId==null||paidByMasterId.size()==0){
			return "-1";
		}else{
			enddate = paidByMasterId.get(0).getEdate();
		}
		SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd");
		String validfrom = simpledf.format(startdate);
		String validthrough = simpledf.format(enddate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String validfrom2 = validfrom + " 00:00";
		String validthrough2 = validthrough + " 23:59";
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(validfrom2);
			end = sdf.parse(validthrough2);
		} catch (ParseException e) {
			return "-1";
		}
		PrhMaster master = prhMasterService.findById(masterId);
		PrHouse house = prHouseService.findById(master.getHouseId());
		if(StringUtils.isBlank(house.getAssociatedlock())){
			return "-2";
		}
		Set<Integer> usercodeset = new TreeSet<Integer>();
		for(int i=10;i<230;i++){
			usercodeset.add(i);
		}
		List<DoorlockUser> doorlockUserList = doorlockUserService.findAvailableByDeviceid(house.getAssociatedlock());
		for(DoorlockUser d : doorlockUserList){
			if(d.getPassword().equals(password)){
				return "-4";
			}
			if(d.getPassword().equals(mfcard)){
				return "-5";
			}
			if(d.getPassword().equals(idcard)){
				return "-6";
			}
			if(d.getPassword().equals(fingerprint)){
				return "-7";
			}
			if(usercodeset.contains(d.getUsercode())){
				usercodeset.remove(d.getUsercode());
			}
		}
		int usercode = (int)usercodeset.toArray()[0];
		int usercode1 = (int)usercodeset.toArray()[1];
		int usercode2 = (int)usercodeset.toArray()[2];
		int usercode3 = (int)usercodeset.toArray()[3];
		if(StringUtils.isNotBlank(password)){
			Map<String, Object> resultMap = sendPasswordRequest(house.getAssociatedlock(), 1, usercode, password, validfrom2, validthrough2);
			if((Integer) resultMap.get("resultcode")==1){
				DoorlockUser doorlockUser = new DoorlockUser(1, usercode, userName, mobilePhone, password, begin, end, new Date(),
						master.getHouseId(), masterId, house.getAssociatedlock(), Global.STATUS_SENDING, Global.SYN_STATUS_TO_BE_SYNCHRONIZED, (String) resultMap.get("receipt"),guestNo);
				doorlockUserService.insert(doorlockUser);
			}else{
				errorResult += 1;
			}
			Thread.sleep(1000);
		}
		if(StringUtils.isNotBlank(idcard)) {
			Map<String, Object> resultMap = sendPasswordRequest(house.getAssociatedlock(), 4, usercode1, idcard, validfrom2, validthrough2);
			if ((Integer) resultMap.get("resultcode") == 1) {
				DoorlockUser doorlockUser = new DoorlockUser(4, usercode1, userName, mobilePhone, idcard, begin, end, new Date(),
						master.getHouseId(), masterId, house.getAssociatedlock(), Global.STATUS_SENDING, Global.SYN_STATUS_TO_BE_SYNCHRONIZED, (String) resultMap.get("receipt"), guestNo);
				doorlockUserService.insert(doorlockUser);
			} else{
				errorResult += 2;
			}
			Thread.sleep(1000);
		}
		if(StringUtils.isNotBlank(mfcard)){
			Map<String, Object> resultMap = sendPasswordRequest(house.getAssociatedlock(), 3, usercode2, mfcard, validfrom2, validthrough2);
			if((Integer) resultMap.get("resultcode")==1){
				DoorlockUser doorlockUser = new DoorlockUser(3, usercode2, userName, mobilePhone, mfcard, begin, end, new Date(),
						master.getHouseId(), masterId, house.getAssociatedlock(), Global.STATUS_SENDING, Global.SYN_STATUS_TO_BE_SYNCHRONIZED, (String) resultMap.get("receipt"),guestNo);
				doorlockUserService.insert(doorlockUser);
			}else {
				errorResult += 4;
			}
			Thread.sleep(1000);
		}
		if(StringUtils.isNotBlank(fingerprint)){
			Map<String, Object> resultMap = sendPasswordRequest(house.getAssociatedlock(), 2, usercode3, fingerprint, validfrom2, validthrough2);
			if((Integer) resultMap.get("resultcode")==1){
				DoorlockUser doorlockUser = new DoorlockUser(2, usercode3, userName, mobilePhone, fingerprint, begin, end, new Date(),
						master.getHouseId(), masterId, house.getAssociatedlock(), Global.STATUS_SENDING, Global.SYN_STATUS_TO_BE_SYNCHRONIZED, (String) resultMap.get("receipt"),guestNo);
				doorlockUserService.insert(doorlockUser);
			}else {
				errorResult += 8;
			}
		}

		return String.valueOf(errorResult);
	}

	@RequestMapping("sendPasswordAgain.do")
	@ResponseBody
	public String sendPasswordAgain(Integer doorlockuserid)  {
		DoorlockUser lockuser = doorlockUserMapper.selectByPrimaryKey(doorlockuserid);

		PrHouse house = prHouseService.findById(lockuser.getHouseid());
		if(StringUtils.isBlank(house.getAssociatedlock())){
			return "-2";
		}

		Date startdate = lockuser.getValidfrom();
		Date enddate = lockuser.getValidthrough();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String validfrom = sdf.format(startdate);
		String validthrough = sdf.format(enddate);
		int usercode = lockuser.getUsercode();
		Map<String, Object> resultMap = null;
		if(lockuser.getStatus()==Global.STATUS_SEND_FAILED){
			resultMap = sendPasswordRequest(house.getAssociatedlock(), lockuser.getUsertype(), usercode, lockuser.getPassword(), validfrom, validthrough);
			lockuser.setStatus(Global.STATUS_SENDING);
		}else if(lockuser.getStatus()==Global.STATUS_UPDATE_TIME_FAILED){
			resultMap = updateValidTimeRequest(house.getAssociatedlock(), usercode, validfrom, validthrough);
			lockuser.setStatus(Global.STATUS_UPDATING_TIME);
		}

		if((Integer) resultMap.get("resultcode")==1){
			lockuser.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
			lockuser.setReceipt((String) resultMap.get("receipt"));

			doorlockUserService.updateByPrimaryKey(lockuser);
		}

		return "1";
	}

	public Map<String,Object> sendPasswordRequest(String associatedlock,int usertype,int usercode,String password,String validfrom,String validthrough){
		String params = "{\"method\": \"thing.service.AddUser\",\"async\":true,\"deviceid\": \""+associatedlock+"\",\"nodeid\": 1,\"params\": {\"UserType\": "+usertype+",\"UserLevel\": 2,\"UserID\": "+usercode+"," +
				"\"UserPassword\": \""+password+"\",\"ValidBeginDateTime\": \""+validfrom+"\",\"ValidEndDateTime\": \""+validthrough+"\",\"ValidWeekDays\": 127,\"ValidWeekTime\": \"00:00~23:59\"}}";
		String result = RestfulUtil.postHttps(params,"lock");
		JSONObject resultMap = JSON.parseObject(result);
		int resultcode = resultMap.getIntValue("resultcode");
		String receipt = resultMap.getString("receipt");
		Map<String,Object> map = new HashMap<>();
		map.put("resultcode",resultcode);
		map.put("receipt",receipt);
		return map;
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

	//身份证挂失
	@RequestMapping("lossIdentity.do")
	@ResponseBody
	public String lossIdentity(String idStr,Integer cardId,String idenPwd,Integer oldCardId, String op,ModelMap map,HttpSession session){
		System.out.println("进入ajax");
		Users user= (Users)session.getAttribute(Global.USER);
		Integer masterId=null;
		Integer linkId=null;
		//拆分主单编号和同住人编号
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		//查询原卡号
		List<Card> oldCards = cardService.findByMasterAndLink(masterId, linkId,1,1);
		Card oldCard=null;
		if (oldCards!=null&&oldCards.size()!=0) {
			oldCard=oldCards.get(0);
		}else{
			//原卡号不存在
			return "0";
		}
		//修改原卡号状态
		oldCard.setState(0);
		cardService.editCard(oldCard);
		 
		//添加历史记录
		HistoryIdenPwd historyIdenPwd=new HistoryIdenPwd(null, masterId, linkId, oldCard.getIdenPwd(), user.getId(), new Date());
		cardService.addHistoryIdenPwd(historyIdenPwd);
		return "1";
	}
	
	
	//写卡
	@RequestMapping("writeCard.do")
	@ResponseBody
	public String writeCard(String idStr,Integer cardId,String idenPwd,Integer oldCardId, String op,ModelMap map){
		//开卡
		if (op.equals("开卡")) {
			return writeCard(idStr, cardId, 0,1,null,oldCardId);
		//生成身份证对码
		}else if (op.equals("生成身份证对码")) {
			return writeCard(idStr, cardId, 1,1,idenPwd,oldCardId);
		}else if (op.equals("挂失卡")) {
			return writeCard(idStr, cardId, 2, 1, idenPwd, oldCardId);
		}
		return "0";
	}
	
	//写卡
	@RequestMapping("writeTempCard.do")
	@ResponseBody
	public String writeTempCard(String idStr,Integer cardId,String idenPwd,Integer oldCardId, String op,ModelMap map){

		Integer masterId=null;
		Integer linkId=null;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		PrhTempLiveMan temp = prhTempLiveManMapper.selectByPrimaryKey(linkId);
		temp.setIdenPwd(idenPwd);
		prhTempLiveManMapper.updateByPrimaryKey(temp);
		return "1";
	}

	//写卡
	private String writeCard(String idStr,Integer cardId, Integer type,Integer state,String idenPwd,Integer oldCardId){
		
		Integer masterId=null;
		Integer linkId=null;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		
		
		//查询原卡是否存在(挂失卡可以重复写)
		if (type!=2) {
			List<Card> oldCards = cardService.findByMasterAndLink(masterId, linkId,type,1);
			if (oldCards!=null&&oldCards.size()!=0) {
				return "2";
			}	
		}
		
		//判断流水号是否被占用
		if (cardService.getById(cardId)!=null) {
			return "3";
		}
		//新增记录
		Card card=new Card(cardId, masterId, linkId,type,state,idenPwd,oldCardId);
		cardService.addCard(card);
		
		if (type==2) {
			Card oldCard = cardService.getById(oldCardId);
			oldCard.setState(0);
			cardService.editCard(oldCard);
		}
		return "1";
	}
	
	
	
	//ajax获取卡信息
	@RequestMapping("getInfoForCard.do")
	@ResponseBody
	public String[] getInfoForCard(String idStr, ModelMap map) throws ParseException{
		System.out.println("ajax");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		
		String[] results=new String[10];
		Integer masterId=null;
		Integer linkId=null;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		Integer nextId = cardService.getNextId();
		results[0]= nextId.toString();
		System.out.println("当前流水号："+results[0]);
		PrhMaster master = prhMasterService.findById(masterId);
		
		//0.流水号 1.授权码 2.合同起 3.合同止     4.楼号,5.楼层,6.房号,0
		results[1]=master.getPrHouse().getEstate().getAuthorCode();
		
		results[2]=df.format( master.getBdate())+" 00:00:00" ;
		results[3]=df.format( master.getEdate())+" 00:00:00" ;
		
		
		String unitName= master.getPrHouse().getBuildingNo().getName();
		results[4]=unitName.substring(0,unitName.indexOf("单"));
		results[5] = master.getPrHouse().getBuildingFloorId()+"";
		results[6]=master.getPrHouse().getRoomNoId()+"";
	
	
		return results;
		
	}
	
	//ajax获取身份证信息
	@RequestMapping("getInfoForIdentity.do")
	@ResponseBody
	public String[] getInfoForIdentity(String idStr, ModelMap map) throws Exception{
		System.out.println("ajax");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		
		String[] results=new String[10];
		Integer masterId=null;
		Integer linkId=null;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		
		Integer nextId = cardService.getNextId();
		results[0]= nextId.toString();
		System.out.println("当前流水号："+results[0]);
		
		PrhMaster master = prhMasterService.findById(masterId);
		
		//0.流水号 1.授权码 2.合同起 3.合同止     4.楼号,5.楼层,6.房号,0
		
	
		results[1]=df.format(master.getBdate())+" 00:00:00";
		results[2]=df.format( master.getEdate())+" 00:00:00" ;
		
		
		results[3]=master.getPrHouse().getHouseCode();
		
		return results;
		
	}
	
	@RequestMapping("toHistoryIdenPwd.do")
	public String toHistoryIdenPwd(String idStr, ModelMap map){
		System.out.println("cccc"+idStr);
		Integer masterId=null;
		Integer linkId=null;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		System.out.println(masterId+"....."+linkId);
		List<HistoryIdenPwd> historys = cardService.findHistoryIdenPwdByPerson(masterId, linkId);
		map.put("historys", historys);
		
		return "renter/code/history.jsp";
	}
		
	
	//ajax获取挂失白卡信息（id）
	@RequestMapping("lossCard.do")
	@ResponseBody
	public String[] lossCard(String idStr, ModelMap map){
		System.out.println("ajax");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String[] results=new String[10];
		Integer masterId=null;
		Integer linkId=null;
		if (idStr.indexOf("-")!=-1) {
			masterId=Integer.valueOf(idStr.substring(0,idStr.indexOf("-")));
			linkId=Integer.valueOf(idStr.substring(idStr.indexOf("-")+1));
		}else{
			masterId=Integer.valueOf(idStr);
		}
		List<Card> oldCards = cardService.findByMasterAndLink(masterId, linkId, 0, 1);
		if (oldCards==null||(oldCards!=null&&oldCards.size()==0)) {
			return null;
		}
		Integer nextId = cardService.getNextId();
		results[0]= nextId.toString();
		System.out.println("当前流水号："+results[0]);
		PrhMaster master = prhMasterService.findById(masterId);
		//0.流水号 1.授权码 2.合同起 3.合同止     4.楼号,5.楼层,6.房号,0
		results[1]=master.getPrHouse().getEstate().getAuthorCode();
		results[2]=oldCards.get(0).getId().toString();
		return results;
		
	}
	
	
	
	@RequestMapping("getAuthorSeq.do")
	@ResponseBody
	public String[] getAuthorSeq(Integer estateId){
		String[] results=new String[2];
		Estate estate = estateService.findById(estateId);
		results[0]=cardService.getNextId().toString();
		results[1]=estate.getAuthorCode();
		return results;
	}
	
	@RequestMapping("getCardSeq.do")
	@ResponseBody
	public String getCardSeq(){
		
		return cardService.getNextId().toString();
		
		
	}
	
	@RequestMapping("getRoomNumberInfo.do")
	@ResponseBody
	public String [] getRoomNumberInfo(Integer houseId){
		String [] results=new String[6];
		PrHouse house = prHouseService.findById(houseId);
		String unitName= house.getBuildingNo().getName();
		
		results[0]= cardService.getNextId().toString();
		results[1]=house.getEstate().getAuthorCode();
		results[2]=unitName.substring(0,unitName.indexOf("单"));
		results[3] = house.getBuildingFloorId()+"";
		results[4]=house.getRoomNoId()+"";
		results[5]=house.getHouseCode();
		return results;
	}
	
	
	//转到写总卡
	@RequestMapping("toWriteTotalCard.do")
	public String toWriteTotalCard(){
		return "prhCode/estate/writeTotalCard.jsp"; 
	}
	
}
