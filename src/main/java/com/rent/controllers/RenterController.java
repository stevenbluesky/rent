package com.rent.controllers;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.common.config.Global;
import com.rent.common.utils.Base64Util;
import com.rent.common.utils.GenerateSequenceUtil;
import com.rent.common.utils.LogUtil;
import com.rent.common.utils.MyConvertUtil;
import com.rent.common.utils.MyDateUtil;
import com.rent.common.utils.MyRentBiz;
import com.rent.common.utils.NumPageUtil;
import com.rent.common.utils.ObjectUtils;
import com.rent.common.utils.WordGenerator;
import com.rent.condition.MasterCondition;
import com.rent.condition.MasterReletCondition;
import com.rent.dao.PrhLinkmanMapper;
import com.rent.dao.PrhMasterMapper;
import com.rent.dao.PrhTempLiveManMapper;
import com.rent.entity.Address;
import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.HouseSpace;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhTempLiveMan;
import com.rent.entity.Profile;
import com.rent.entity.RentPayWay;
import com.rent.entity.RoomType;
import com.rent.entity.Subsidy;
import com.rent.entity.SubsidyType;
import com.rent.entity.Users;
import com.rent.modules.sys.entity.Repaire;
import com.rent.modules.sys.service.SystemService;
import com.rent.services.AttachmentService;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.services.FileManagementService;
import com.rent.services.HouseSpaceService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhLinkManService;
import com.rent.services.PrhMasterService;
import com.rent.services.PrhTempLiveManService;
import com.rent.services.RentPayWayService;
import com.rent.services.RenterService;
import com.rent.services.RepaireService;
import com.rent.services.RoomTypeService;
import com.rent.services.SubsidyService;
import com.rent.services.SubsidyTypeService;
import com.rent.services.impl.SubsidyCal;

/*
 	登记updateuser   updatetime 
 	退租updateUser	updateTime
  
 */
@Controller("renterController")
@Transactional(readOnly = false)
public class RenterController {
	protected LogUtil logUtil = new LogUtil();

	protected Logger logger = Logger.getLogger(this.getClass().getName());// 日志对象
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private PrhMasterMapper prhMasterMapper;

	@Autowired
	private PrhLinkmanMapper prhLinkmanMapper;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private PrhTempLiveManService prhTempLiveManService;

	@Autowired
	private PrhTempLiveManMapper prhTempLiveManMapper;

	public PrhTempLiveManMapper getPrhTempLiveManMapper() {
		return prhTempLiveManMapper;
	}

	public void setPrhTempLiveManMapper(PrhTempLiveManMapper prhTempLiveManMapper) {
		this.prhTempLiveManMapper = prhTempLiveManMapper;
	}

	public PrhTempLiveManService getPrhTempLiveManService() {
		return prhTempLiveManService;
	}

	public void setPrhTempLiveManService(PrhTempLiveManService prhTempLiveManService) {
		this.prhTempLiveManService = prhTempLiveManService;
	}

	public PrhMasterMapper getPrhMasterMapper() {
		return prhMasterMapper;
	}

	public void setPrhMasterMapper(PrhMasterMapper prhMasterMapper) {
		this.prhMasterMapper = prhMasterMapper;
	}

	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}

	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Autowired
	private HouseSpaceService houseSpaceService;

	public HouseSpaceService getHouseSpaceService() {
		return houseSpaceService;
	}

	public void setHouseSpaceService(HouseSpaceService houseSpaceService) {
		this.houseSpaceService = houseSpaceService;
	}

	@Autowired
	private RepaireService repaireService;

	public RepaireService getRepaireService() {
		return repaireService;
	}

	public void setRepaireService(RepaireService repaireService) {
		this.repaireService = repaireService;
	}

	@Autowired
	private PrhMasterService prhMasterService;
	@Autowired
	private PrHouseService prHouseService;
	@Autowired
	private SubsidyTypeService subsidyTypeService;
	@Autowired
	private RentPayWayService rentPayWayService;
	@Autowired
	private FileManagementService fileManagementService;
	@Autowired
	private BuildingNoService buildingNoService;
	@Autowired
	private EstateService estateService;

	@Autowired
	private RenterService renterService;

	@Autowired
	private PrhLinkManService prhLinkManService;

	@Autowired
	private BuildingService buildingService;
	@Autowired
	private SystemService systemService;

	public SystemService getSystemService() {
		return systemService;
	}

	public PrhLinkmanMapper getPrhLinkmanMapper() {
		return prhLinkmanMapper;
	}

	public void setPrhLinkmanMapper(PrhLinkmanMapper prhLinkmanMapper) {
		this.prhLinkmanMapper = prhLinkmanMapper;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public RenterService getRenterService() {
		return renterService;
	}

	public BuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public void setRenterService(RenterService renterService) {
		this.renterService = renterService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public PrhLinkManService getPrhLinkManService() {
		return prhLinkManService;
	}

	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}

	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}

	public void setPrhLinkManService(PrhLinkManService prhLinkManService) {
		this.prhLinkManService = prhLinkManService;
	}

	@Autowired
	private SubsidyService subsidyService;

	public SubsidyService getSubsidyService() {
		return subsidyService;
	}

	public void setSubsidyService(SubsidyService subsidyService) {
		this.subsidyService = subsidyService;
	}

	public PrhMasterService getPrhMasterService() {
		return prhMasterService;
	}

	public void setPrhMasterService(PrhMasterService prhMasterService) {
		this.prhMasterService = prhMasterService;
	}

	public FileManagementService getFileManagementService() {
		return fileManagementService;
	}

	public void setFileManagementService(FileManagementService fileManagementService) {
		this.fileManagementService = fileManagementService;
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

	public PrHouseService getPrHouseService() {
		return prHouseService;
	}

	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}

	// 转到登记
	@RequestMapping("toAddRenter")
	public String toAddRenter(Integer houseId, Integer currMasterId, Integer tabNo, Integer isLiveIn, String tip,
			ModelMap map) throws UnsupportedEncodingException {

		System.out.println("isLiveIn:" + isLiveIn);
		// 默认选项卡
		if (tabNo == null) {
			tabNo = 1;
		}
		// 获取houseId
		if (houseId == null && currMasterId != null) {
			houseId = prhMasterService.findById(currMasterId).getHouseId();
		}
		// 房屋信息
		PrHouse house = prHouseService.findById(houseId);
		map.put("house", house);

		// 补贴标准
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);

		// 租金支付方式
		List<RentPayWay> rentPayWays = rentPayWayService.findAll();
		map.put("rentPayWays", rentPayWays);
		if (tip != null && tip.length() != 0) {
			tip = new String(tip.getBytes("ISO-8859-1"), "UTF-8");
			map.put("tip", tip);
		}
		// 公司集合
		List<Profile> companys = fileManagementService.findAllCompanyByEstate(house.getEstateId());
		map.put("companys", companys);

		// 当前主单
		if (currMasterId != null) {
			// 当前主单
			PrhMaster currMaster = prhMasterService.findById(currMasterId);
			map.put("currMaster", currMaster);
			// 同住人
			List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(currMasterId);
			map.put("linkMans", linkMans);
		}

		map.put("tabNo", tabNo);
		map.put("isLiveIn", isLiveIn);
		// 查询同住人
		return "renter/renterAdd/renterAdd.jsp";
	}

	// 新增同住人
	@RequestMapping("addLinkMan")
	public String qq(PrhLinkman prhLinkman, Profile profile, String linkPhotoPic, Integer isLiveIn,
			HttpServletRequest request, ModelMap map) throws ParseException {

		map.put("isLiveIn", isLiveIn);

		boolean isAdd = prhLinkman.getId() == null;
		
		// 修改
		if (linkPhotoPic != null && linkPhotoPic.length() != 0) {

			// 上传相片
			String path = request.getSession().getServletContext().getRealPath("static/photo");// 获取文件夹路径
			// 新文件名
			String generateNo = GenerateSequenceUtil.generateSequenceNo();
			String newfilename = generateNo + ".jpg";
			String allPath = path + "\\" + newfilename;
			Base64Util.GenerateImage(linkPhotoPic, allPath);
			prhLinkman.setPhoto(newfilename);
			// 上传身份证图片
		}
		if (!isAdd) {
			// 根据身份证判断租客档案是否存在
			List<Profile> oldProfiles = fileManagementService.findByCardId(profile.getIdno());
			Profile oldProfile = (oldProfiles != null && oldProfiles.size() != 0) ? oldProfiles.get(0) : null;

			System.out.println("zzzz" + oldProfile.getName());
			// 主单
			profile.setClass_("G");
			profile.setSta("I");
			profile.setGuestno(oldProfile.getGuestno());
			prhLinkman.setGuestno(profile.getGuestno());

			prhLinkManService.updatePrhLinkman(prhLinkman);
			fileManagementService.updateProFile(profile);
			map.put("tip", "同住人修改成功！");
		} else {
			// 根据身份证判断租客档案是否存在
			Profile oldProfile = getTheSameProfile(profile);
			if (!hasTheSameC) {
				// 新建租户档案
				profile.setClass_("G");
				profile.setSta("I");
				profile.setGuestno(GenerateSequenceUtil.generateSequenceNo());
				fileManagementService.saveProFile(profile);
			} else {
				profile = oldProfile;
			}

			// 新增同住人单子
			prhLinkman.setGuestno(profile.getGuestno());

			prhLinkman.setAddtime(new Date());
			prhLinkManService.addPrhLinkman(prhLinkman);

			map.put("tip", "同住人新增成功！");
		}
		// 主单
		PrhMaster prhMaster = prhMasterService.findById(prhLinkman.getMasterId());
		// 主单人数加一
		if (isAdd) {
			prhMaster.setNumbs((prhMaster.getNumbs() == null ? 0 : prhMaster.getNumbs()) + 1);
			prhMasterService.updatePrhMaster(prhMaster);
		}
		if (prhMaster.getSubsidyTypeId() != null) {
			prhMasterService.updateSubsidyAndRate(prhMaster.getId());
		}

		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(prhLinkman.getMasterId());

		renterService.delByMaster(prhMaster.getId());
		renterService.createRentals(prhMaster.getId());

		map.put("linkMans", linkMans);

		map.put("currMasterId", prhLinkman.getMasterId());
		map.put("houseId", prhMaster.getHouseId());
		map.put("tabNo", 2);

		return "../toAddRenter?currMasterId=" + prhLinkman.getMasterId() + "&houseId=" + prhMaster.getHouseId()
				+ "&tabNo=2";
	}

	// 转到修改同住人
	@RequestMapping("toEditLinkMan.do")
	public String toEditLinkMan(Integer linkId, Integer isLiveIn, HttpServletRequest request, ModelMap map) {

		map.put("isLiveIn", isLiveIn);
		PrhLinkman link = prhLinkManService.findById(linkId);

		if (link.getPhoto() != null) {
			// 上传相片
			String path = request.getSession().getServletContext().getRealPath("static/photo");// 获取文件夹路径
			// 新文件名
			String fileName = link.getPhoto();
			String allPath = path + "\\" + fileName;
			String code = Base64Util.GetImageStr(allPath);
			System.out.println("photo:" + code);
			map.put("linkPhotoCode", code);
		}

		map.put("link", link);
		PrhMaster currMaster = prhMasterService.findById(link.getMasterId());
		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(currMaster.getId());
		map.put("linkMans", linkMans);

		// 补贴标准
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);

		// 租金支付方式
		List<RentPayWay> rentPayWays = rentPayWayService.findAll();
		map.put("rentPayWays", rentPayWays);

		map.put("currMasterId", currMaster.getId());
		map.put("currMaster", currMaster);
		map.put("houseId", currMaster.getHouseId());
		map.put("tabNo", 2);
		return "renter/renterAdd/renterAdd.jsp";
	}

	// 修改同住人
	@RequestMapping("editLinkMan.do")
	public String editLinkMan(PrhLinkman prhLinkman, ModelMap map) {

		return "";
	}

	boolean hasTheSameC = false;
	boolean hasTheSameG = false;

	private Profile getTheSameProfile(Profile profile) {
		hasTheSameC = false;
		// 身份证相同的档案集合
		List<Profile> theSameProfiles = fileManagementService.findByCardId(profile.getIdno());

		if (theSameProfiles == null || theSameProfiles.size() == 0) {
			return null;
		}

		for (Profile p : theSameProfiles) {
			System.out.println("公司：" + p.getCompany());
			if (p.getCompany() != null) {
				this.companyTemp = p.getCompany();
			}
			System.out.println("状态：" + p.getSta());
			// 更新基本信息 （身份证相同的档案）
			p.setName(profile.getName());
			p.setSex(profile.getSex());
			p.setIdcls(profile.getIdcls());
			p.setIdno(profile.getIdno());
			p.setNation(profile.getNation());
			p.setStreet(profile.getStreet());
			p.setPhone(profile.getPhone());
			p.setMobile(profile.getMobile());
			p.setEmail(profile.getEmail());

			// 类型相同（租户档案、单位档案）

			System.out.println("租户：" + profile.getClass_());
			System.out.println("查到租户：" + p.getClass_());

			if ("G".equals(profile.getClass_()) && "G".equals(p.getClass_())) { // 租户
				hasTheSameG = true;
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

		return profile;

	}

	private String companyTemp;
	private Integer estateIdTemp;

	// 登记
	@RequestMapping("addRenter")
	@Transactional(readOnly = false)
	public String ss(Profile profile, PrhMaster prhMaster, Integer currMasterId, String dep1_, String bdate_,
			String edate_, String rentdate_, Integer houseId, Integer subsidyTypeId, String photoPic, String idenPic,
			ModelMap map, HttpSession session, Integer isLiveIn, HttpServletRequest reuqest) throws ParseException {

		// 当前用户
		Users user = (Users) session.getAttribute(Global.USER);

		// 检测合同和准入编号是否重复

		map.put("isLiveIn", isLiveIn);

		PrhMaster byAllowInNo = null;
		PrhMaster byContract = null;

		if (prhMaster.getTag1() != null && prhMaster.getTag1().length() != 0) {
			byAllowInNo = prhMasterService.getByAllowInNo(prhMaster.getTag1());
		}
		if (prhMaster.getTag2() != null && prhMaster.getTag2().length() != 0) {
			byContract = prhMasterService.getByContract(prhMaster.getTag2());
		}

		if (currMasterId == null) { // 新增
			prhMaster.setProfile(profile);
			map.put("currMaster", prhMaster);
			if (byContract != null) {
				map.put("tip", "该合同号已经存在！");
				return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
			}
			if (byAllowInNo != null) {
				map.put("tip", "该准入编号已经存在！");
				return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
			}
		} else {
			if (byContract != null && !byContract.getId().equals(currMasterId)) {
				map.put("tip", "该合同号已经存在！");
				return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
			}
			if (byAllowInNo != null && !byAllowInNo.getId().equals(currMasterId)) {
				map.put("tip", "该准入编号已经存在！");
				return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
			}
		}
		if (currMasterId == null) {
			if (prhMasterService.getLiveInGuestCount(profile.getIdno()) > 0) {
				map.put("tip", "该租户已经登记或入住其他房间,不能再次入住！");
				return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
			}
		} else {
			PrhMaster master = prhMasterService.findById(currMasterId);
			if (master.getProfile().getIdno() != null && !master.getProfile().getIdno().equals(profile.getIdno())
					&& prhMasterService.getLiveInGuestCount(profile.getIdno()) > 0) {
				map.put("tip", "该租户已经登记或入住其他房间,不能再次入住！");
				return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
			}
		}

		// 处理时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		prhMaster.setDep1(df.parse(dep1_));
		prhMaster.setBdate(df.parse(bdate_));
		prhMaster.setEdate(df.parse(edate_));
		prhMaster.setRentDate(df.parse(rentdate_));
		prhMaster.setSubsidyTypeId(subsidyTypeId);
		prhMaster.setSta("7");
		// 根据身份证判断租客档案是否存在
		if (profile.getClass_().equals("G")) {

			Profile oldProfile = getTheSameProfile(profile);

			if ((!hasTheSameG) || oldProfile == null) {
				// profile.setCompany(fileManagementService.findById(profile.getClass_()).getCompany());
				// 新建租户档案
				String guestNo = UUID.randomUUID().toString();
				profile.setGuestno(guestNo);
				profile.setSta("I");
				profile.setCompany(null);
				prhMaster.setGuestNo(guestNo);

				fileManagementService.saveProFile(profile);

			} else {
				if (oldProfile != null) {
					profile = oldProfile;
				}
				profile.setSta("I");
				profile.setCompany(null);
				prhMaster.setGuestNo(profile.getGuestno());
				System.out.println("修改档案前");
				fileManagementService.updateProFile(profile);
				System.out.println("修改档案后");
			}
			// 公司
		} else {
			Profile company = fileManagementService.findById(profile.getCompany());
			String companyName = null;
			String companyId = null;
			if (company != null) {
				companyName = company.getCompany();
				companyId = company.getGuestno();
			}

			Profile oldProfile =  getTheSameProfile(profile);
			String guestNo = UUID.randomUUID().toString();
			profile.setGuestno(guestNo);
			profile.setSta("A"); // 重复的档案
			profile.setClass_("C");
			profile.setCusno(companyId);
			profile.setCompany(companyName);
			profile.setEstateId(estateIdTemp);
			prhMaster.setGuestNo(guestNo);
			fileManagementService.saveProFile(profile);

			/* fileManagementService.updateProFile(profile); */
		}

		// 查询房屋信息
		PrHouse house = prHouseService.findById(houseId);

		// 查询当前补贴信息(保障外和保障内) 一个人的补贴
		SubsidyCal inSubsidyCal = null;
		SubsidyCal outSubsidyCal = null;
		if (subsidyTypeId != -1) {
			inSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), subsidyTypeId, 1, prhMaster.getBdate());
			outSubsidyCal = subsidyService.findSubsidyCal(house.getEstateId(), subsidyTypeId, 2, prhMaster.getBdate());
		}
		// 查询到补贴信息或者不补贴
		if ((inSubsidyCal != null && outSubsidyCal != null) || subsidyTypeId == -1) {

			int numbs = 1;
			if (currMasterId != null) {
				numbs = prhMaster.getNumbs();
			}

			if (subsidyTypeId != -1) {
				inSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
				inSubsidyCal.setPerNum(numbs);
				outSubsidyCal.setArea(Double.valueOf(house.getArea().toString()));
				outSubsidyCal.setPerNum(numbs);
				// 补贴
				Double subPrice = inSubsidyCal.calInSubsidy() + outSubsidyCal.calOutSubsidy();
				prhMaster.setSetrate(BigDecimal.valueOf(subPrice));

				// 实收金额
				BigDecimal realMoney = BigDecimal
						.valueOf(Double.parseDouble(house.getMonthPrice().toString()) - subPrice);
				prhMaster.setRate(realMoney.doubleValue() >= 0 ? realMoney : BigDecimal.valueOf(0));

			} else {
				prhMaster.setSubsidyTypeId(null);
				prhMaster.setSetrate(BigDecimal.valueOf(0.0));
				// 实收金额
				prhMaster.setRate(BigDecimal.valueOf(Double.parseDouble(house.getMonthPrice().toString())));
			}

			// 保存租户主单

			if (prhMaster.getDeposit() == null) {
				prhMaster.setDeposit(BigDecimal.valueOf(0.0));
			}

			// 判断是新增还是修改
			if (currMasterId == null) {
				// 是否已经被别人登记
				if (2 == house.getState()) {
					map.put("tip", "该房源已被别人登记！");
					return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
				}
				// 新增(返回当前主单)
				prhMaster.setNumbs((prhMaster.getNumbs() == null ? 0 : prhMaster.getNumbs()) + 1);// 主单人数加一
				prhMaster.setDepFlag("0");

				if (photoPic != null && photoPic.length() != 0) {

					// 上传相片
					String path = reuqest.getSession().getServletContext().getRealPath("static/photo");// 获取文件夹路径
					// 新文件名
					String generateNo = GenerateSequenceUtil.generateSequenceNo();
					String newfilename = generateNo + ".jpg";
					String allPath = path + "\\" + newfilename;
					Base64Util.GenerateImage(photoPic, allPath);
					prhMaster.setPicPhoto(newfilename);

					// 上传身份证图片
					String generateNo2 = GenerateSequenceUtil.generateSequenceNo();
					String newfilename2 = generateNo2 + ".jpg";
					String allPath2 = path + "\\" + newfilename2;
					Base64Util.GenerateImage(idenPic, allPath2);
					prhMaster.setPicIdentity(newfilename2);
				}

				// 新增
				prhMaster.setCreateuser(user.getId().toString());
				prhMaster.setCreatetime(new Date());

				System.out.println("档案的编号：" + profile.getGuestno());
				System.out.println("租户的编号：" + prhMaster.getGuestNo());
				currMasterId = prhMasterService.addPrhMaster(prhMaster);

				System.out.println("新增的id" + currMasterId);
				house.setState(new Short("2")); // 修改房间状态

				house.setMasterId(currMasterId); // 设置房间当前主单

				System.out.println("房间修改前");
				prHouseService.updatePrHouse(house);
				System.out.println("房间修改后");

				map.put("tip", "主单信息添加成功！");

				renterService.createRentals(currMasterId);

			} else { // 修改

				if (photoPic != null && photoPic.length() != 0) {
					// 上传相片
					String path = reuqest.getSession().getServletContext().getRealPath("static/photo");// 获取文件夹路径
					// 新文件名
					String generateNo = GenerateSequenceUtil.generateSequenceNo();
					String newfilename = generateNo + ".jpg";
					String allPath = path + "\\" + newfilename;
					Base64Util.GenerateImage(photoPic, allPath);
					prhMaster.setPicPhoto(newfilename);
					System.out.println(allPath);
					// 上传身份证图片
					String generateNo2 = GenerateSequenceUtil.generateSequenceNo();
					String newfilename2 = generateNo2 + ".jpg";
					String allPath2 = path + "\\" + newfilename2;
					Base64Util.GenerateImage(idenPic, allPath2);
					prhMaster.setPicIdentity(newfilename2);
				}

				prhMaster.setId(currMasterId);
				prhMaster.setUpdateuser(user.getId().toString());
				prhMaster.setUpdatetime(new Date());

				prhMasterService.updatePrhMaster(prhMaster);

				renterService.delByMaster(currMasterId);
				renterService.createRentals(currMasterId);

				map.put("tip", "主单信息修改成功！");
			}

			// 当前主单id
			map.put("currMasterId", currMasterId);
			map.put("houseId", houseId);
			map.put("tabNo", 2);

			// 当前主单
			PrhMaster currMaster = prhMasterService.findById(currMasterId);
			map.put("currMaster", currMaster);
			// 同住人
			List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(currMasterId);

			map.put("linkMans", linkMans);
			int tabNo = 2;

			if (currMaster.getGuestNo() == null) {
				map.put("tip", "租户信息获取失败,请重试");
				attachmentService.deleteByMaster(currMasterId);
				renterService.delByMaster(currMasterId);
				prhMasterService.delPrhMaster(currMasterId);
				house.setState(Short.valueOf("6"));
				house.setMasterId(null);
				prHouseService.updatePrHouse(house);
				tabNo = 1;
				return "../toAddRenter?currMasterId=&houseId=" + houseId + "&tabNo=" + tabNo + "&tip=登记失败,请稍后再试！";

			}
			return "../toAddRenter?currMasterId=" + currMasterId + "&houseId=" + houseId + "&tabNo=" + tabNo;

		} else {
			map.put("tip", "未检测到符合条件的补贴信息！");
			return "../toAddRenter?houseId=" + houseId + "&tabNo=1";
		}

	}

	// 转到租户信息详情(查找租户的房子)
	@RequestMapping("toRenterInfoByRenter.do")
	public String qq(Integer masterId, HttpSession session, HttpServletResponse response, ModelMap map)
			throws IOException {

		// 主单信息
		PrhMaster master = prhMasterService.findById(masterId);
		map.put("currMaster", master);

		// 房屋信息
		PrHouse house = prHouseService.findById(master.getHouseId());
		map.put("house", house);

		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(masterId);
		map.put("linkMans", linkMans);

		// 维修记录
		List<Repaire> repaires = repaireService.findByMaster(masterId);
		map.put("repaires", repaires);

		return "renter/renterAdd/renterInfo.jsp";
	}

	// 转到租户信息详情(查找房子的租户)
	@RequestMapping("toRenterInfo")
	public String aa(Integer houseId, HttpSession session, HttpServletResponse response, ModelMap map)
			throws IOException {

		// 房屋信息
		PrHouse house = prHouseService.findById(houseId);
		map.put("house", house);
		// 主单信息
		PrhMaster master = prhMasterService.findById(house.getMasterId());
		map.put("currMaster", master);

		// 同住人
		List<PrhLinkman> linkMans = prhLinkManService.findAllByMaster(master.getId());
		map.put("linkMans", linkMans);

		// 维修记录
		List<Repaire> repaires = repaireService.findByMaster(master.getId());
		map.put("repaires", repaires);

		return "renter/renterAdd/renterInfo.jsp";
	}

	// 删除同住人
	@RequestMapping("delLinkMan")
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

			return "../toAddRenter?currMasterId=" + prhMaster.getId() + "&houseId=" + prhMaster.getHouseId()
					+ "&tabNo=2";
		} else {
			return "";
		}

	}

	// 退租显示页面
	@RequestMapping("findMasterByCondition.do")

	public String findMasterByCondition(Integer estateId, String roomNo, String buildingNoId, String buildingId,
			String name, String company, String leaveDate_, String sta, Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
		// 空值处理
		if (buildingNoId != null && (buildingNoId.equals("-1") || buildingNoId.length() == 0)) {
			buildingNoId = null;
		}
		if ("-1".equals(buildingId)) {
			buildingId = null;
		}
		if (request.getMethod().equals("GET")) {
			name = request.getParameter("name");
			roomNo = request.getParameter("roomNo");
			if (name != null) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			}
			if (roomNo != null) {
				roomNo = new String(roomNo.getBytes("iso-8859-1"), "utf-8");
			}
		}

		if (name != null && name.length() == 0) {
			name = null;
		}
		if (roomNo != null && roomNo.length() == 0) {
			roomNo = null;
		}

		if (company != null && company.length() == 0) {
			company = null;
		}
		if (sta != null && sta.length() == 0) {
			sta = null;
		}

		map.put("sta", sta);

		// 分页设置
		Integer size = 10;
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		// 处理时间
		Date leaveDate = null;

		if (leaveDate_ != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			leaveDate = df.parse(leaveDate_);
		}

		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}

		map.put("estateId", estateId); // 当前物业
		map.put("estates", estates); // 所有物业
		if (estateId != null) {
			// 单元集合
			List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
			map.put("buildingNos", buildingNos);

			// 楼号集合
			List<Building> buildings = buildingService.findAllByEstate(estateId);
			map.put("buildings", buildings);
		}

		System.out.println("pppppp" + name);
		// 条件查询
		MasterCondition condition = new MasterCondition(estateId, buildingNoId, name, company, leaveDate);
		condition.setRoomNo(roomNo);
		condition.setBuildingId(buildingId);
		condition.setLeaveDate(null);
		condition.setBegin((currpage - 1) * size + 1);
		condition.setEnd(condition.getBegin() + size - 1);
		condition.setSta(sta);
		condition.setLeaveDate(leaveDate);
		// 主单信息
		List<PrhMaster> masters = prhMasterService.findByCondition(condition);

		// 查询总数
		int total = prhMasterService.getCountByCondition(condition);

		// 分页工具类
		String url = "findMasterByCondition.do?name=" + (name != null ? name : "") + "&roomNo="
				+ (roomNo != null ? roomNo : "") + "&buildingId=" + (buildingId != null ? buildingId : "-1")
				+ "&buildingNoId=" + (buildingNoId != null ? buildingNoId : "-1") + "&estateId=" + estateId;

		NumPageUtil page = new NumPageUtil(url, total, currpage, size);
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域
		page.setList(masters);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);

		map.put("masters", masters);
		// 条件查询存值
		map.put("condition", condition);
		map.put("estateId", estateId);
		map.put("buildingNoId", buildingNoId);
		map.put("name", name);
		map.put("company", company);
		map.put("leaveDate", leaveDate);
		map.put("sta", sta);
		return "renter/renterLeave/renterMaster.jsp";
	}

	// 转到申请退租
	@RequestMapping("toRenterLeave.do")
	public String toRenterLeave(Integer masterId, ModelMap map) {
		map.put("masterId", masterId);
		return "renter/renterLeave/leaveApply.jsp";
	}

	// 申请退租
	@RequestMapping("renterLeave.do")
	@ResponseBody
	public String renterLeave(PrhMaster master, String cotime_, ModelMap map, HttpSession session)
			throws ParseException {

		Users user = (Users) session.getAttribute(Global.USER);
		// 处理时间
		Date cotime = null;
		if (cotime_ != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			cotime = df.parse(cotime_);
		}
		String refer2 = master.getRefer2();
		master = prhMasterService.findById(master.getId());

		master.setCo(master.getProfile().getName());// 退租人
		master.setDepApply(new Date()); // 申请时间
		master.setCotime(cotime); // 退租时间
		master.setSta("2"); // 更改状态
		master.setRefer2(refer2);
		master.setType(null); // 改为不续租
		master.setUpdateUser(user.getId());
		master.setUpdateTime(new Date());

		;

		// 删除续租信息
		if (master.getNewMasterId() != null) {
			attachmentService.deleteByMaster(master.getNewMasterId());
			renterService.delByMaster(master.getNewMasterId());
			prhMasterService.delPrhMaster(master.getNewMasterId());
			master.setNewMasterId(null);
		}

		prhMasterService.updatePrhMaster(master);

		return "";
	}

	// 转到退租修改
	@RequestMapping("toRenterLeaveEdit.do")
	public String toRenterLeaveEdit(Integer masterId, ModelMap map) {
		PrhMaster master = prhMasterService.findById(masterId);
		map.put("master", master);
		return "renter/renterLeave/leaveApplyEdit.jsp";
	}

	// 退租修改
	@RequestMapping("renterLeaveEdit.do")
	@ResponseBody
	public String toRenterLeaveEdit(Integer id, String refer2, String cotime_, ModelMap map, HttpSession session)
			throws ParseException {

		Users user = (Users) session.getAttribute(Global.USER);
		// 处理时间
		Date cotime = null;
		if (cotime_ != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			cotime = df.parse(cotime_);
		}
		PrhMaster master = prhMasterService.findById(id);

		master.setRefer2(refer2);
		master.setCotime(cotime);
		master.setUpdateUser(user.getId());
		master.setUpdateTime(new Date());
		map.put("master", master);
		prhMasterService.updatePrhMaster(master);
		return "1";
	}

	// 删除退租申请
	@RequestMapping("delApply.do")
	@ResponseBody
	public String delApply(Integer masterId, ModelMap map) {
		PrhMaster master = prhMasterService.findById(masterId);
		master.setCotime(null);
		master.setCo(null);
		master.setDepApply(null);
		master.setSta("1");
		master.setRefer2(null);
		prhMasterService.updatePrhMaster(master);
		int stateId = master.getPrHouse().getState();
		return masterId + "-" + MyConvertUtil.getHouseState(stateId);
	}

	// 登记列表
	@RequestMapping("findCheckInRecord.do")
	public String findCheckInRecord(Integer currpage, Integer estateId, String name, String roomNo, String buildingNoId,
			String buildingId, MasterReletCondition condition, HttpServletRequest request, HttpSession session,
			ModelMap map) throws UnsupportedEncodingException {
		if (request.getMethod().equals("GET")) {
			name = request.getParameter("name");
			roomNo = request.getParameter("roomNo");
			if (name != null) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
			}
			if (roomNo != null) {
				roomNo = new String(roomNo.getBytes("iso-8859-1"), "utf-8");
			}
		}
		int size = 10;
		// 条件查询

		if (name != null && name.length() == 0) {
			name = null;
		}

		if (roomNo != null && roomNo.length() == 0) {
			roomNo = null;
		}
		// 物业集合
		List<Estate> estates = estateService.findAll();
		if (estateId == null) {
			Estate estate = MyRentBiz.getEstates(estates);
			if (estate != null) {
				estateId = estate.getId();
			}

		}
		if ("-1".equals(buildingId)) {
			buildingId = null;
		}
		if ("-1".equals(buildingNoId)) {
			buildingNoId = null;
		}
		if (estateId != null) {

			// 单元集合
			List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
			map.put("buildingNos", buildingNos);

			// 楼号集合
			List<Building> buildings = buildingService.findAllByEstate(estateId);
			map.put("buildings", buildings);
		}
		condition = new MasterReletCondition(currpage, size, estateId, name, roomNo);
		condition.setBuildingNoId(buildingNoId);
		condition.setBuildingId(buildingId);

		List<PrhMaster> masters = prhMasterService.findByCheckInRecordPaged(condition);
		condition.setEstateId(estateId);

		// 分页查询tab1

		map.put("condition", condition);

		String url = "findCheckInRecord.do?name=" + (name != null ? name : "") + "&roomNo="
				+ (roomNo != null ? roomNo : "") + "&buildingId=" + (buildingId != null ? buildingId : "-1")
				+ "&buildingNoId=" + (buildingNoId != null ? buildingNoId : "-1") + "&estateId=" + estateId;
		NumPageUtil page = new NumPageUtil(url, prhMasterService.getCountByCheckInRecordPaged(condition), currpage,
				size, masters, session, request);

		// map
		map.put("page", page);

		map.put("estates", estates);
		map.put("estateId", estateId);

		return "renter/checkInRecord/checkInRecord.jsp";
	}

	// 删除登记申请/确认入住
	@RequestMapping("delCheckInRecordForm.do")
	public String delCheckInRecordForm(String op, Integer[] chk, ModelMap map) {

		if (op.equals("删除")) {
			for (Integer id : chk) {
				Integer houseId = prhMasterService.findById(id).getHouseId();
				PrHouse house = prHouseService.findById(houseId);
				house.setState(Short.valueOf("6"));// 修改房间状态
				prHouseService.updatePrHouse(house);
				renterService.delByMaster(id);
			}
			prhMasterService.delPrhMaster(chk);
			map.put("tip", "删除成功");
		} else if (op.equals("入住确认")) {
			int count = 0;
			for (Integer id : chk) {
				PrhMaster master = prhMasterService.findById(id);

				if (master.getSrc() != null && "1".equals(master.getSrc().trim()) && "8".equals(master.getSta())) {
					Integer houseId = master.getHouseId();
					PrHouse house = prHouseService.findById(houseId);
					master.setSta("1");
					master.setSrc(null);
					house.setState(Short.valueOf("5"));

					prhMasterService.updatePrhMaster(master);
					prHouseService.updatePrHouse(house);

					// 更新空闲记录
					HouseSpace houseSpace = houseSpaceService.findLastRecord(houseId);
					if (houseSpace != null) {
						houseSpace.setEndDate(master.getBdate());
						houseSpaceService.updateHouseSpace(houseSpace);
					}

					count++;
				}
			}
			if (count == chk.length) {
				map.put("tip", "入住确认成功！");
			} else if (count == 0) {
				map.put("tip", "入住确认失败,租户尚未缴费！");
			} else {
				map.put("tip", "部分单号入住确认失败，有租户尚未缴费！");
			}
		}

		return "../findCheckInRecord.do";
	}

	// 转到 退租打印
	@RequestMapping("toRenterLeavePrint.do")
	public String toRenterLeavePrint(Integer masterId, ModelMap map) {
		map.put("masterId", masterId);
		return "renter/renterLeave/leaveApplyPrint.jsp";
	}

	// 转到 退租打印
	@RequestMapping("renterLeavePrint.do")
	public String renterLeavePrint(Integer masterId, ModelMap map) {
		map.put("masterId", masterId);
		PrhMaster master = prhMasterService.findById(masterId);
		PrHouse house = master.getPrHouse();

		map.put("tip", "打印成功！成功退房！");

		return "renter/renterLeave/leaveApplyPrint.jsp";
	}

	// ajax动态合同时间等值
	// ajax动态合同时间等值
	@RequestMapping("changeDateList.do")
	@ResponseBody
	public String[] changeDateList(Integer rentCodeId, String contractBeginDate, ModelMap map,
			HttpServletRequest reuqest, HttpSession session) {

		String[] arrays = new String[3];

		try {
			// 支付方式
			RentPayWay rentPayWay = rentPayWayService.findById(rentCodeId);
			Integer payMonth = rentPayWay.getRentPay();
			Integer deposit = rentPayWay.getDepositPay();

			// 合同起始时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date endDate = MyDateUtil.addDate(MyDateUtil.addYear(df.parse(contractBeginDate), 1), -1);
			String endDateStr = df.format(endDate);

			// 结束时间
			arrays[0] = endDateStr;
			Date moneyTo = MyDateUtil.addDate(MyDateUtil.addMonth(df.parse(contractBeginDate), payMonth), -1);
			String moneyToStr = df.format(moneyTo);
			arrays[1] = moneyToStr;

			arrays[2] = deposit.toString();
			return arrays;
		} catch (ParseException e) {
			return null;
		}

	}

	// 退租确认
	@RequestMapping("confirmLeave.do")
	@ResponseBody
	public String confirmLeave(Integer masterId, ModelMap map, HttpSession session) {
		Users user = (Users) session.getAttribute(Global.USER);
		PrhMaster master = prhMasterService.findById(masterId);
		PrHouse house = prHouseService.findById(master.getHouseId());
		// 其他操作员已经退房
		if ("4".equals(master.getSta()) || Short.valueOf("4").equals(house.getState())) {
			master.setSta("4");
			master.setType(null);
			master.setDepApply(null);
			master.setCo(null);
			master.setDepApply(null);
			master.setRefer2(null);
			master.setUpdateUser(user.getId());
			master.setUpdateTime(new Date());

			house.setState(Short.valueOf("4"));// 锁定
			house.setRemark("退租锁定");
			house.setMasterId(-1);
			house.setIsTui(1);
			prHouseService.updatePrHouse(house);
			prhMasterService.updatePrhMaster(master);

			return "2";
		} else {
			// 已结账
			if ("1".equals(master.getSrc())) {
				// 房子状态

				if (house.getState().intValue() == 8) { // 如果是 在租维修
					house.setState(Short.valueOf("7"));// 空置维修
				} else {
					house.setState(Short.valueOf("4"));// 锁定
				}

				house.setIsTui(1);
				house.setRemark("退租锁定");
				house.setMasterId(-1);
				// 有续租申请的，删除
				if (master.getNewMasterId() != null) {
					prhMasterService.delPrhMaster(master.getNewMasterId());
				}

				System.out.println("masterId:" + house.getMasterId());
				prHouseService.updatePrHouse(house);
				// 主单状态
				master.setSta("4");
				master.setType(null);
				master.setSrc(null);
				master.setDepApply(null);
				master.setCo(null);
				master.setDepApply(null);
				master.setRefer2(null);

				prhMasterService.updatePrhMaster(master);

				// 新增空闲记录
				HouseSpace houseSpace = new HouseSpace(null, master.getHouseId(), master.getCotime(), null);
				houseSpaceService.addHouseSpace(houseSpace);
				return "1";
			} else {
				return "0";
			}
		}

	}

	// 打印合同
	@RequestMapping("printHetong.do")
	public void printHetong(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		req.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		PrhMaster master = prhMasterService.findById(103);
		Repaire rep = systemService.getRepaireByID("3");
		/*
		 * String docName = req.getParameter("name"); String type =
		 * req.getParameter("type");
		 */
		Integer id = 103;
		String docName = "xx";
		String type = "accept";

		map = ObjectUtils.objToMap(rep);

		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordGenerator.createDoc(map, type);
			fin = new FileInputStream(file);

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/msword");
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc
			resp.addHeader("Content-Disposition", "attachment;filename=" + docName + ".doc");

			out = resp.getOutputStream();
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
			out.flush();
			resp.flushBuffer();
			// resp.flushBuffer();
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}
	}

	// 转到维修申请
	@RequestMapping("toRepaireApply.do")
	public String toRepaireApply(Integer masterId, ModelMap map) {
		PrhMaster master = prhMasterService.findById(masterId);

		List<Repaire> oldRepaires = repaireService.findByMaster(masterId);
		Repaire oldRepaire = null;
		if (oldRepaires != null && oldRepaires.size() != 0) {
			oldRepaire = oldRepaires.get(0);
		}

		map.put("oldRepaire", oldRepaire);
		map.put("master", master);
		return "renter/renterLeave/repaireApply.jsp";
	}

	// 维修申请 表单提交
	@RequestMapping("repaireApply.do")
	@ResponseBody
	public String repaireApply(Repaire repaire, ModelMap map) {
		Integer masterId = repaire.getMasterID();
		PrHouse house = prHouseService.findById(repaire.getHouseID());
		/*
		 * List<Repaire> oldRepaires = repaireService.findByMaster(masterId);
		 * Repaire oldRepaire = null; if (oldRepaires != null &&
		 * oldRepaires.size() != 0) { oldRepaire = oldRepaires.get(0); }
		 */
		/*
		 * if (oldRepaire != null) {
		 * oldRepaire.setRepairer(repaire.getRepairer());
		 * oldRepaire.setRepairerPhone(repaire.getRepairerPhone());
		 * oldRepaire.setRepairerTime(repaire.getRepairerTime());
		 * oldRepaire.setRepaireType(repaire.getRepaireType());
		 * oldRepaire.setComments(repaire.getComments());
		 * systemService.updateRepaire(oldRepaire); if (house.getMasterId() ==
		 * null) { house.setState(Short.valueOf("7")); } else { return "0"; }
		 * 
		 * } else {
		 */
		
		repaire.setHouseStatus(house.getState().toString());
		systemService.save(repaire);

		if (house.getMasterId() == null) {
			house.setState(Short.valueOf("7"));
		} else {
			return "0";
		}

		/* } */

		return "1";
	}

	@RequestMapping("isGuestInLive.do")
	@ResponseBody
	public String isGuestInLive(String idno) {
		if (prhMasterService.getLiveInGuestCount(idno) > 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 转到租户导出
	@RequestMapping("renterExport.do")
	public String renterExport(Integer estateId, String buildingId, String buildingNoId, Integer typeId,
			Integer subsidyTypeId, Integer masterType, Integer currpage, Double minArea, Double maxArea, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// 处理当前页
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		if ("-1".equals(buildingId)) {
			buildingId = null;
		}
		if ("-1".equals(buildingNoId)) {
			buildingNoId = null;
		}
		if ("-1".equals(typeId)) {
			typeId = null;
		}
		if ("-1".equals(subsidyTypeId)) {
			subsidyTypeId = null;
		}
		if ("-1".equals(masterType)) {
			masterType = null;
		}

		Integer size = 10; // 页大小

		int begin = MyConvertUtil.toPagedBeginEnd(currpage, size)[0];
		int end = MyConvertUtil.toPagedBeginEnd(currpage, size)[1];
		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		map.put("estateId", estateId);
		map.put("estates", estates);
		// 楼号集合
		List<Building> buildings = buildingService.findAllByEstate(estateId);
		map.put("buildings", buildings);

		// 单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
		map.put("buildingNos", buildingNos);

		// 房型
		List<RoomType> roomTypes = roomTypeService.findAll();
		map.put("roomTypes", roomTypes);

		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);

		MasterCondition condition = new MasterCondition(begin, end, estateId, buildingNoId, buildingId, typeId,
				subsidyTypeId, minArea, maxArea);
		condition.setMasterType(masterType);
		int count = prhMasterMapper.getCountRentersExport(condition);
		List<PrhMaster> masters = prhMasterService.findRentersExportPaged(condition);

		map.put("condition", condition);
		String url = "renterExport.do?buildingId=" + (buildingId != null ? buildingId : "-1") + "&buildingNoId="
				+ (buildingNoId != null ? buildingNoId : "-1") + "&typeId=" + (typeId != null ? typeId : "-1")
				+ "&subsidyTypeId=" + (subsidyTypeId != null ? subsidyTypeId : "-1") + "&masterType="
				+ (masterType != null ? masterType : "-1")

				+ "&estateId=" + estateId;

		NumPageUtil page = new NumPageUtil(url, count, currpage, size, masters, session, request);
		// map
		map.put("page", page);

		return "renter/checkInRecord/renterExport.jsp";
	}

	@RequestMapping("toTempMan.do")
	public String toTempMan(Integer masterId, ModelMap map) {
		PrhMaster master = prhMasterService.findById(masterId);
		map.put("currMaster", master);
		List<PrhTempLiveMan> masters = prhTempLiveManService.findByMaster(masterId);
		map.put("mans", masters);
		return "renter/checkInRecord/tempMan.jsp";
	}

	// 新增临时居住人
	@RequestMapping("addTempMan")
	public String addTempMan(PrhTempLiveMan tempman, Profile profile, String linkPhotoPic, Integer isLiveIn,
			HttpServletRequest request, ModelMap map) throws ParseException {

		map.put("isLiveIn", isLiveIn);

		boolean isAdd = tempman.getId() == null;
		// 修改

		/*
		 * if (linkPhotoPic != null && linkPhotoPic.length() != 0) {
		 * 
		 * // 上传相片 String path =
		 * request.getSession().getServletContext().getRealPath("static/photo");
		 * // 获取文件夹路径 // 新文件名 String generateNo =
		 * GenerateSequenceUtil.generateSequenceNo(); String newfilename =
		 * generateNo + ".jpg"; String allPath = path + "\\" + newfilename;
		 * Base64Util.GenerateImage(linkPhotoPic, allPath);
		 * prhLinkman.setPhoto(newfilename); // 上传身份证图片 }
		 */
		if (!isAdd) {
			// 根据身份证判断租客档案是否存在
			List<Profile> oldProfiles = fileManagementService.findByCardId(profile.getIdno());
			Profile oldProfile = (oldProfiles != null && oldProfiles.size() != 0) ? oldProfiles.get(0) : null;

			System.out.println("zzzz" + oldProfile.getName());
			// 主单
			profile.setClass_("G");
			profile.setSta("I");
			profile.setGuestno(oldProfile.getGuestno());
			tempman.setGuestno(profile.getGuestno());

			prhTempLiveManMapper.updateByPrimaryKey(tempman);
			fileManagementService.updateProFile(profile);
			map.put("tip", "临时居住人修改成功！");
		} else {
			// 根据身份证判断租客档案是否存在
			Profile oldProfile = getTheSameProfile(profile);
			if (!hasTheSameC) {
				// 新建租户档案
				profile.setClass_("G");
				profile.setSta("I");
				profile.setGuestno(GenerateSequenceUtil.generateSequenceNo());
				fileManagementService.saveProFile(profile);
			} else {
				profile = oldProfile;
			}
			// 新增同住人单子
			tempman.setGuestno(profile.getGuestno());
			prhTempLiveManMapper.insert(tempman);
			map.put("tip", "临时居住人新增成功！");
		}

		map.put("currMasterId", tempman.getMasterId());

		return "../toTempMan.do?masterId=" + tempman.getMasterId();
	}
	// 删除同住人
		@RequestMapping("delTempMan")
		public String delTempMan(Integer[] chk, ModelMap map) throws ParseException {

			if (chk != null && chk.length != 0) {
				// 主单
				PrhTempLiveMan linkMan = prhTempLiveManMapper.selectByPrimaryKey(chk[0]) ;
				for (Integer id : chk) {
					prhTempLiveManMapper.deleteByPrimaryKey(id);
				}

				return "../toTempMan.do?masterId=" + linkMan.getMasterId();
			} else {
				return "";
			}

		}
}
