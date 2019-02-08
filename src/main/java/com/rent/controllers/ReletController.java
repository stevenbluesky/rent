package com.rent.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.common.config.Global;
import com.rent.common.utils.GenerateSequenceUtil;
import com.rent.common.utils.MyConvertUtil;
import com.rent.common.utils.MyDateUtil;
import com.rent.common.utils.MyRentBiz;
import com.rent.common.utils.NumPageUtil;
import com.rent.condition.MasterCondition;
import com.rent.condition.MasterReletCondition;
import com.rent.dao.PrhLinkmanMapper;
import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;
import com.rent.entity.Profile;
import com.rent.entity.RentPayWay;
import com.rent.entity.SubsidyType;
import com.rent.entity.Users;
import com.rent.modules.sys.entity.Repaire;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.services.FileManagementService;
import com.rent.services.PrhLinkManService;
import com.rent.services.PrhMasterService;
import com.rent.services.RentPayWayService;
import com.rent.services.RenterService;
import com.rent.services.RepaireService;
import com.rent.services.SubsidyService;
import com.rent.services.SubsidyTypeService;
import com.rent.services.impl.SubsidyCal;

@Controller("reletController")
public class ReletController {
	@Autowired
	private PrhLinkmanMapper prhLinkmanMapper;
	
	
	@Autowired

	private PrhMasterService prhMasterService;
	@Autowired
	private EstateService estateService;

	@Autowired
	private RentPayWayService rentPayWayService;

	@Autowired
	private SubsidyTypeService subsidyTypeService;
	@Autowired
	private PrhLinkManService prhLinkManService;
	@Autowired
	private SubsidyService subsidyService;

	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private BuildingNoService buildingNoService;
	
	public BuildingService getBuildingService() {
		return buildingService;
	}

	public PrhLinkmanMapper getPrhLinkmanMapper() {
		return prhLinkmanMapper;
	}

	public void setPrhLinkmanMapper(PrhLinkmanMapper prhLinkmanMapper) {
		this.prhLinkmanMapper = prhLinkmanMapper;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}

	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}

	@Autowired
	private RenterService renterService;

	public RenterService getRenterService() {
		return renterService;
	}

	public void setRenterService(RenterService renterService) {
		this.renterService = renterService;
	}

	@Autowired
	private FileManagementService fileManagementService;

	public FileManagementService getFileManagementService() {
		return fileManagementService;
	}

	public void setFileManagementService(FileManagementService fileManagementService) {
		this.fileManagementService = fileManagementService;
	}

	public SubsidyService getSubsidyService() {
		return subsidyService;
	}

	public void setSubsidyService(SubsidyService subsidyService) {
		this.subsidyService = subsidyService;
	}

	public PrhLinkManService getPrhLinkManService() {
		return prhLinkManService;
	}

	public void setPrhLinkManService(PrhLinkManService prhLinkManService) {
		this.prhLinkManService = prhLinkManService;
	}

	public SubsidyTypeService getSubsidyTypeService() {
		return subsidyTypeService;
	}

	public void setSubsidyTypeService(SubsidyTypeService subsidyTypeService) {
		this.subsidyTypeService = subsidyTypeService;
	}

	public RentPayWayService getRentPayWayService() {
		return rentPayWayService;
	}

	public void setRentPayWayService(RentPayWayService rentPayWayService) {
		this.rentPayWayService = rentPayWayService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public PrhMasterService getPrhMasterService() {
		return prhMasterService;
	}

	public void setPrhMasterService(PrhMasterService prhMasterService) {
		this.prhMasterService = prhMasterService;
	}
	
	@Autowired
	private RepaireService repaireService;
	
	
	public RepaireService getRepaireService() {
		return repaireService;
	}

	public void setRepaireService(RepaireService repaireService) {
		this.repaireService = repaireService;
	}

	// 续租查询页面
	@RequestMapping("findReletPagedTab2.do")
	public String findReletPagedTab2(Integer currpage, Integer estateId,String buildingId,String buildingNoId, String name, String roomNo, Integer tabNo,
			MasterReletCondition condition, HttpServletRequest request, HttpSession session, ModelMap map)
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
		int size=10;
		if (name != null&&name.length()==0) {
			name = null;
		}
		if (roomNo !=null&&roomNo.length()==0) {
			roomNo =null;
		}
		
		if ("-1".equals(buildingId)) {
			buildingId=null;
		}
		if ("-1".equals(buildingNoId)	) {
			buildingNoId=null;
		}
		tabNo=2;
		
		
		// 物业集合
		List<Estate> estates = estateService.findAll();
		if (estateId == null) {
			estateId = MyRentBiz.getEstates(estates).getId();
		}
		//单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
		map.put("buildingNos", buildingNos);
		
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(estateId);
		map.put("buildings", buildings);
		System.out.println(roomNo);
		condition = new MasterReletCondition(currpage, size, 1, name, roomNo);
		condition.setBuildingId(buildingId);
		condition.setEstateId(estateId);
		condition.setBuildingNoId(buildingNoId);
		// 分页查询tab1
		List<PrhMaster> applyMasters = prhMasterService.findByReletApplyPaged(condition);
		String url = "findReletPagedTab2.do?name=" + (name!=null?name:"") 
                + "&roomNo=" + (roomNo!=null?roomNo:"")
                + "&buildingId="+(buildingId!=null?buildingId:"-1")
                + "&buildingNoId="+(buildingNoId!=null?buildingNoId:"-1")
                + "&estateId=" + estateId;
		NumPageUtil page = new NumPageUtil(url, prhMasterService.getCountByReletApplyPaged(condition), currpage, size,
				applyMasters, session, request);
		map.put("page2", page);
		// map
		map.put("estates", estates);
		map.put("estateId", estateId);
		map.put("tabNo", tabNo);
		
		map.put("condition", condition);
		return "renter/relet/renterMasterRelet.jsp";
	}

	// 续租查询页面
	@RequestMapping("findReletPaged.do")
	public String findReletPaged(Integer currpage, Integer estateId,String buildingId,String buildingNoId, String name, String roomNo, Integer tabNo,
			MasterReletCondition condition, HttpServletRequest request, HttpSession session, ModelMap map)
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
		
		int size=10;
		tabNo=1;
		if (name != null&&name.length()==0) {
			name = null;
		}
		if (roomNo !=null&&roomNo.length()==0) {
			roomNo =null;
		}
		if (request.getMethod().equals("GET")) {
			name= request.getParameter("name");
			roomNo=request.getParameter("roomNo");
			if (name!=null) {
				name= new String(name.getBytes("iso-8859-1"),"utf-8");	
			}
			if (roomNo!=null&&request.getMethod().equals("GET")) {
				roomNo= new String(roomNo.getBytes("iso-8859-1"),"utf-8");
			}
		}
		if ("-1".equals(buildingId)) {
			buildingId=null;
		}
		if ("-1".equals(buildingNoId)	) {
			buildingNoId=null;
		}
		// 物业集合
				List<Estate> estates = estateService.findAll();
				if (estateId == null) {
					Estate estate = MyRentBiz.getEstates(estates);
					if (estate != null) {
						estateId = estate.getId();
					}
				}
		condition = new MasterReletCondition(currpage, size, 1, name, roomNo);
		condition.setEstateId(estateId);
		condition.setBuildingId(buildingId);
		condition.setBuildingNoId(buildingNoId);
		List<PrhMaster> masters = prhMasterService.findByReletCondition(condition);

		if (estateId!=null) {
		
		//单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
		map.put("buildingNos", buildingNos);
		
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(estateId);
		map.put("buildings", buildings);
		// 分页查询tab1
		}
		
		String url = "findReletPaged.do?name=" + (name!=null?name:"") 
                + "&roomNo=" + (roomNo!=null?roomNo:"")
                + "&buildingId="+(buildingId!=null?buildingId:"-1")
                + "&buildingNoId="+(buildingNoId!=null?buildingNoId:"-1")
                + "&estateId=" + estateId;
		NumPageUtil page = new NumPageUtil(url, prhMasterService.getCountByReletCondition(condition), currpage, size,
				masters, session, request);

		// map
		map.put("page", page);
		map.put("estates", estates);
		map.put("estateId", estateId);
		map.put("tabNo", tabNo);
		map.put("condition", condition);
		return "renter/relet/renterMasterRelet.jsp";
	}

	@RequestMapping("justRentalOfRelet.do")
	@ResponseBody
	public String justRentalOfRelet(int masterId) {
		//判断是否交钱完毕
		List<PrhRental> rentals = renterService.findByAccnt(masterId);
		for (PrhRental rental : rentals) {
			if ("F".equals(rental.getSta())) {
				return "0";
			}
		}
		return "1";
	}

	// 转到续租申请
	@RequestMapping("toReletApplyAdd.do")
	public String toReletApplyAdd(Integer masterId, Integer tabNo, Integer linkId, ModelMap map) throws ParseException {
		
		//维修记录
		Integer oldId=null;
		int currId=masterId;
		List<Repaire> repaires = repaireService.findByMaster(masterId);
		while (true) {
			PrhMaster curr=prhMasterService.findById(currId);
			if ((oldId=curr.getOldMasterId())!=null) {
				List<Repaire> old = repaireService.findByMaster(oldId);
				repaires.addAll(old);
				currId=oldId;
			}else{
				break;
			}
		}
		
		
		map.put("repaires", repaires);
		
		//当前操作是修改同住人
		PrhLinkman link =null;
		if(linkId!=null){
			 link = prhLinkManService.findById(linkId);
			 map.put("link", link);
		}
		// 原主单编号
		PrhMaster oldMaster = prhMasterService.findById(masterId);
		
		// 租金支付方式
		int payMonth = 0;
		List<RentPayWay> rentPayWays = rentPayWayService.findAll();
		if (rentPayWays != null && rentPayWays.size() != 0) {
			payMonth = rentPayWays.get(0).getRentPay();
		}

		map.put("rentPayWays", rentPayWays);

		// 补贴标准
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);

		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(masterId);
		map.put("linkMans", linkMans);
		map.put("oldMaster", oldMaster);

		// 新单号起始时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(oldMaster.getEdate());
		Date newMasterBDate = MyDateUtil.addDate(df.parse(dateStr), 1);
		map.put("newMasterBDate", newMasterBDate);
		Date newMasterEDate = MyDateUtil.addYear(MyDateUtil.addDate(df.parse(dateStr), 1), 1);
		map.put("newMasterEDate", newMasterEDate);
		Date toMasterDate = MyDateUtil.addMonth(MyDateUtil.addDate(df.parse(dateStr), 1), payMonth);
		map.put("toMasterDate", toMasterDate);
		
		if (tabNo == null) {
			if (linkId!=null) {
				//当前操作是修改同住人
				tabNo=2;	
			}else{
				tabNo=1;
			}
		}
		
		
		map.put("tabNo", tabNo);

		// 判断是否是修改

		if (oldMaster.getOldMasterId() != null) { // 进入新主单，修改
			map.put("prhMaster", oldMaster);
			map.put("currMaster", oldMaster);
		} else {
			oldMaster.setBdate(null);
			oldMaster.setEdate(null);
		}
		
		return "renter/relet/reletApplyAdd.jsp";
	}

	// 续租申请
	@RequestMapping("reletApplyAdd.do")
	public String reletApplyAdd(PrhMaster master, Integer oldMasterId, Integer currMasterId, Integer tabNo, 
			Integer subsidyTypeId, String dep1_, String bdate_, String edate_, String rentdate_, ModelMap map,HttpSession session) throws ParseException {
		
		 Users user= (Users)session.getAttribute(Global.USER);
		
		// 原主单
		PrhMaster oldMaster = prhMasterService.findById(oldMasterId);
		PrHouse house = oldMaster.getPrHouse();
		PrhMaster currMaster = null;
		if (currMasterId == null) {
			currMaster = oldMaster;
			currMaster.setId(null);
		} else {
			currMaster = prhMasterService.findById(currMasterId);
		}

		// 处理时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		currMaster.setDep1(df.parse(dep1_));
		currMaster.setBdate(df.parse(bdate_));
		currMaster.setEdate(df.parse(edate_));
		currMaster.setRentDate(df.parse(rentdate_));

		
		// 查询当前补贴信息(保障外和保障内) 一个人的补贴
		SubsidyCal inSubsidyCal =null;
		SubsidyCal outSubsidyCal=null;
		if (subsidyTypeId!=-1) {
			 inSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), master.getSubsidyTypeId(), 1,
					currMaster.getBdate());
			outSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), master.getSubsidyTypeId(), 2,
					currMaster.getBdate());
		}
		//检测到补贴标准
		if ((inSubsidyCal != null && outSubsidyCal != null)||subsidyTypeId==-1) {
			//有无补贴
			if (subsidyTypeId!=-1) {
				inSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
				inSubsidyCal.setPerNum(1);
				outSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
				outSubsidyCal.setPerNum(1);
				// 补贴
				Double subPrice = inSubsidyCal.calInSubsidy() + outSubsidyCal.calOutSubsidy();
				currMaster.setSetrate(BigDecimal.valueOf(subPrice));
				currMaster.setRate(BigDecimal.valueOf(Double.parseDouble(house.getMonthPrice().toString()) - subPrice));
				
			}else{
				currMaster.setSubsidyType(null);
				
				currMaster.setSetrate(BigDecimal.valueOf(0.0));
				// 实收金额
				currMaster.setRate(BigDecimal.valueOf(Double.parseDouble(house.getMonthPrice().toString())));
			}
			

			
			// 克隆并修改原主单信息，作为新主单
			currMaster.setSta("5"); // 状态：申请
			currMaster.setRentCode(master.getRentCode()); // 支付方式
			currMaster.setSubsidyTypeId(master.getSubsidyTypeId()!=-1?master.getSubsidyTypeId():null); // 补贴标准
			currMaster.setDeposit(master.getDeposit()); // 押金支付
			currMaster.setBank(master.getBank());
			currMaster.setTag3(master.getTag3());
			currMaster.setTag4(master.getTag4());

			currMaster.setTag1(null);
			currMaster.setTag2(null);
			if (currMaster.getDeposit() == null) {
				currMaster.setDeposit(BigDecimal.valueOf(0.0));
			}
			if (currMaster.getId() == null) {

				currMaster.setCreateuser(user.getId().toString());
				currMaster.setCreatetime(new Date());
				currMasterId= prhMasterService.addPrhMaster(currMaster);// 新增主单申请
				map.put("tip", "续租申请成功");

				// 生成账单
				renterService.createRentals(currMasterId);

				PrhMaster old = prhMasterService.findById(oldMasterId);
				// 同住人
				List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(oldMasterId);
				for (PrhLinkman p : linkMans) {
					p.setMasterId(currMasterId);
				}
				// 批量复制同住人
				prhLinkManService.addPrhLinkman(linkMans);

				old.setType("1");//
				if (old.getSta() != null && old.getSta().equals("2")) {
					old.setSta("1");
				}

				old.setNewMasterId(currMasterId);
				prhMasterService.updatePrhMaster(old);

				// 原主单标识为被申请续租
				currMaster.setId(currMasterId);
				currMaster.setOldMasterId(oldMasterId);
				prhMasterService.updatePrhMaster(oldMaster);

			} else {
				map.put("tip", "续租申请修改成功");
				currMaster.setUpdateuser(user.getId().toString());
				currMaster.setUpdatetime(new Date());
				prhMasterService.updatePrhMaster(currMaster);
				renterService.delByMaster(currMasterId);
				renterService.createRentals(currMasterId);
			}

		} else {

			map.put("oldMaster", oldMaster);
			map.put("tip", "未检测到符合条件的补贴信息！");
			return "../toReletApplyAdd.do?masterId=" + oldMasterId + "&tabNo=1";
		}
		map.put("currMaster", currMaster);

		return "../toReletApplyAdd.do?masterId=" + currMasterId + "&tabNo=1";

	}
	boolean hasTheSameC=false;
	boolean hasTheSameG=false;
	private String companyTemp;
	private Integer estateIdTemp;
	private Profile getTheSameProfile(Profile profile){
		hasTheSameC=false;
		//身份证相同的档案集合
		List<Profile> theSameProfiles = fileManagementService.findByCardId(profile.getIdno());
		
		if (theSameProfiles==null||theSameProfiles.size()==0) {
			return null;
		}
		
		for (Profile p : theSameProfiles) {
			System.out.println("公司："+p.getCompany());
			if (p.getCompany()!=null) {
				this.companyTemp=p.getCompany();
			}
			System.out.println("状态："+p.getSta());
			// 更新基本信息 （身份证相同的档案）
			p.setName(profile.getName());
			p.setSex(profile.getSex());
			p.setIdcls(profile.getIdcls());
			p.setIdno(profile.getIdno());
			p.setNation(profile.getNation());
			p.setStreet(profile.getStreet());
			p.setPhone(profile.getPhone());
			p.setEmail(profile.getEmail());
			// 类型相同（租户档案、单位档案）
			if ("G".equals(profile.getClass_()) && "G".equals(p.getClass_())) { // 租户
				hasTheSameG=true;
				profile = p;
				

			} else if ("C".equals(profile.getClass_()) && "C".equals(p.getClass_())) {// 单位

				if ("A".equals(p.getSta())) { // 单位租户 可改
					hasTheSameC = true;
					profile = p;
				}
				if ("I".equals(profile.getSta())) {
					estateIdTemp = p.getEstateId();
				}
				
			}
		}

		
		
		return  profile;
		
		
	}

	// 新增同住人
	@RequestMapping("addLinkManRelet.do")
	public String addLinkManRelet(PrhLinkman prhLinkman, Profile profile,Integer masterId, Integer id, ModelMap map) throws ParseException {
		if (prhLinkman.getMasterId()==null) {
			prhLinkman.setMasterId(masterId);
		}
		// 根据身份证判断租客档案是否存在
		List<Profile> oldProfiles =fileManagementService.findByCardId(profile.getIdno());
		Profile oldProfile=(oldProfiles!=null&&oldProfiles.size()!=0)?oldProfiles.get(0):null;
		if (oldProfile == null) {
			//新建租户档案
			profile.setClass_("G");
			profile.setSta("I");
			profile.setGuestno(GenerateSequenceUtil.generateSequenceNo());
			fileManagementService.saveProFile(profile);
		} else {
			profile.setGuestno(oldProfile.getGuestno());
			profile.setClass_("G");
			profile.setSta("I");
			profile.setCompany(null);
			fileManagementService.updateProFile(profile);
			profile = oldProfile;
		}
		
		
		
		//新增同住人单子
		prhLinkman.setGuestno(profile.getGuestno());
		
		if (id!=null) {
			prhLinkman.setId(id);
			System.out.println(prhLinkman.getId()+"--"+prhLinkman.getMasterId()+"--"+prhLinkman.getGuestno());
			
			prhLinkManService.updatePrhLinkman(prhLinkman);
			renterService.delByMaster(prhLinkman.getMasterId());
			renterService.createRentals(prhLinkman.getMasterId());
			map.put("tip", "同住人修改成功！");
		}else{
			prhLinkman.setAddtime(new Date());
			prhLinkManService.addPrhLinkman(prhLinkman);
			map.put("tip", "同住人新增成功！");
		}
		// 主单
		PrhMaster prhMaster = prhMasterService.findById(masterId);
		// 主单人数加一
		prhMaster.setNumbs((prhMaster.getNumbs() == null ? 0 : prhMaster.getNumbs()) + 1);
		prhMasterService.updatePrhMaster(prhMaster);
		if (prhMaster.getSubsidyTypeId()!=null) {
			prhMasterService.updateSubsidyAndRate(prhMaster.getId());	
		}
		
		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(masterId);

		map.put("linkMans", linkMans);

		map.put("currMasterId", prhLinkman.getMasterId());
		map.put("houseId", prhMaster.getHouseId());
		map.put("tabNo", 2);
		
		return "../toReletApplyAdd.do?masterId=" + prhLinkman.getMasterId() + "&tabNo=2";
	}

	// 转到续租申请确认
	@RequestMapping("toReletApplyConfirm.do")
	public String toReletApplyConfirm(Integer masterId, ModelMap map) {

		map.put("masterId", masterId);
		return "renter/relet/reletApplyConfirm.jsp";

	}
	
	// 续租确认
	@RequestMapping("reletApplyConfirm.do")
	@ResponseBody
	public String reletApplyConfirm(Integer masterId, String hetong, String inId, ModelMap map,HttpSession session) {
		
		 Users user= (Users)session.getAttribute(Global.USER);
		PrhMaster master = prhMasterService.findById(masterId);
		if ("6".equals(master.getSta())) {
			return "4";// 其他操作员已经操作
		} else {
			// 账务已结清
			if (master.getSrc() != null && "1".equals(master.getSrc().trim())) {
				if (prhMasterService.getByContract(hetong) != null) {
					return "2";
				}
				if (prhMasterService.getByAllowInNo(inId) != null) {
					return "3";
				}

				master.setTag1(inId); // 准入编号
				master.setTag2(hetong); // 合同编号
				master.setType(null);
				master.setSrc(null);
				master.setSta("6"); // 状态改为续租
				PrhMaster oldMaster = prhMasterService.findById(master.getOldMasterId());
				oldMaster.setType(null);
				oldMaster.setSta("9");
				
				master.setUpdateuser(user.getId().toString());
				master.setUpdatetime(new Date());
				
				prhMasterService.updatePrhMaster(oldMaster);// 修改原主单
				prhMasterService.updatePrhMaster(master);

				return "1";
			} else {
				return "0";
			}
		}

	}

	// 转到申请修改
	@RequestMapping("toReletApplyApplyEdit.do")
	public String toReletApplyApplyEdit(String hetong, String inId) {

		return "renter/relet/reletApplyEdit.jsp";
	}

	// 删除续租申请
	@RequestMapping("delReletApply.do")
	public String delReletApply(Integer[] chk, ModelMap map) {

		for (Integer i : chk) {
			Integer oldMasterId = prhMasterService.findById(i).getOldMasterId();
			
			if (oldMasterId!=null) {
				PrhMaster oldMaster = prhMasterService.findById(oldMasterId);

				oldMaster.setType(null);
				oldMaster.setNewMasterId(null);
				oldMaster.setSrc(null);
				prhMasterService.updatePrhMaster(oldMaster);

			}
			
			// 删除申请
			renterService.delByMaster(i);
			prhMasterService.delPrhMaster(i);
		}

		return "../findReletPagedTab2.do";
	}

	//删除同住人
	@RequestMapping("delLinkManRelet")
	public String aa1(Integer[] chk, ModelMap map) throws ParseException {


		if (chk != null && chk.length != 0) {
			// 主单
			PrhLinkman linkMan = prhLinkManService.findById(chk[0]);
			for (Integer id : chk) {
				PrhLinkman man = new PrhLinkman();
				man.setId(id);
				man.setDel(1);
				man.setDeltime(new Date());
				prhLinkmanMapper.updateByPrimaryKeySelective(man);
			}

			PrhMaster prhMaster = prhMasterService.findById(linkMan.getMasterId());
			// 同住人
			List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(prhMaster.getId());
			// 主单人数加一
			prhMaster.setNumbs((prhMaster.getNumbs() == null ? 0 : prhMaster.getNumbs()) - chk.length);
			prhMasterService.updatePrhMaster(prhMaster);
			if (prhMaster.getSubsidyTypeId() != null) {
				prhMasterService.updateSubsidyAndRate(prhMaster.getId());
			}
			renterService.delByMaster(prhMaster.getId());
			renterService.createRentals(prhMaster.getId());
			map.put("linkMans", linkMans);
			map.put("currMasterId", prhMaster.getId());
			map.put("houseId", prhMaster.getHouseId());
			map.put("tabNo", 2);
			map.put("tip", "删除成功！");

			return "../toReletApplyAdd.do?masterId=" + prhMaster.getId() + "&tabNo=2";
		} else {
			return "";
		}

	}	

	@RequestMapping("toEditReletLinkMan.do")
	public String toEditReletLinkMan(Integer linkId, ModelMap map) {
		PrhLinkman link = prhLinkManService.findById(linkId);
		map.put("link", link);
		 
		
		PrhMaster currMaster = prhMasterService.findById(link.getMasterId());
		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(currMaster.getId());
		map.put("linkMans", linkMans);

		// 租金支付方式
		List<RentPayWay> rentPayWays = rentPayWayService.findAll();

		map.put("rentPayWays", rentPayWays);

		// 补贴标准
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);
		
		map.put("currMasterId", currMaster.getId());
		map.put("currMaster", currMaster);
		map.put("prhMaster", currMaster);
		map.put("oldMaster", currMaster);
		map.put("tabNo", 2);
		return "renter/relet/reletApplyAdd.jsp";
	}

}
