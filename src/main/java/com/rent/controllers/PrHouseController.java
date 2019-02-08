package com.rent.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rent.common.*;
import com.rent.common.config.Global;
import com.rent.entity.*;

import com.rent.services.BuildingFloorService;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.services.PrHouseService;
import com.rent.services.RoomTypeService;
import com.rent.services.UserService;
import com.rent.common.utils.ExcelOperate;
import com.rent.common.utils.MyConvertUtil;
import com.rent.common.utils.NumPageUtil;
import com.rent.condition.HouseCondition;

@Controller("prHouseController")
public class PrHouseController {

	public BuildingFloorService getBuildingFloorService() {
		return buildingFloorService;
	}

	public void setBuildingFloorService(BuildingFloorService buildingFloorService) {
		this.buildingFloorService = buildingFloorService;
	}

	public BuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}

	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}

	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}

	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}

	public PrHouseService getPrHouseService() {
		return prHouseService;
	}

	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}

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
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private EstateService estateService;

	@RequestMapping("findHouseByConditionPaged.do")
	public String findHouseByCondition(Integer estateId, Integer buildingId, Integer buildingNoId, Integer floorId,
									   Integer typeId, Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request) {

		// 处理当前页
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
		// 楼号集合
		List<Building> buildings = buildingService.findAllByEstate(estateId);
		map.put("buildings", buildings);

		// 单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
		map.put("buildingNos", buildingNos);

		// 房型
		List<RoomType> roomTypes = roomTypeService.findAll();
		map.put("roomTypes", roomTypes);
		// 楼层
		List<BuildingFloor> floors = buildingFloorService.findAll();
		map.put("floors", floors);

		return "";
	}

	// 分页查询
	@RequestMapping("findPrHouseByEstatePaged.do")
	public String findPrHouseByEstatePaged(Integer estateId, String buildingId, String buildingNoId, Integer floorId,
										   Integer typeId,Integer houseNature, Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request) {
		// 处理数据
		if ("-1".equals(buildingId)) {
			buildingId = null;
		}
		if ("-1".equals(buildingNoId)) {
			buildingNoId = null;
		}
		if ("-1".equals(houseNature)) {
			houseNature = null;
		}

		if (floorId != null && floorId == -1) {
			floorId = null;
		}
		if (typeId != null && -1 == typeId) {
			typeId = null;
		}
		// 处理当前页
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
		if (estateId != null) {

			// 楼号集合
			List<Building> buildings = buildingService.findAllByEstate(estateId);
			map.put("buildings", buildings);

			// 单元集合
			List<BuildingNo> buildingNos = buildingNoService.findByEstate(estateId);
			map.put("buildingNos", buildingNos);
		}
		// 房型
		List<RoomType> roomTypes = roomTypeService.findAll();
		map.put("roomTypes", roomTypes);
		// 楼层
		List<BuildingFloor> floors = buildingFloorService.findAll();
		map.put("floors", floors);
		int begin = MyConvertUtil.toPagedBeginEnd(currpage, size)[0];
		int end = MyConvertUtil.toPagedBeginEnd(currpage, size)[1];

		HouseCondition condition = new HouseCondition(estateId, buildingId, buildingNoId, floorId, typeId, begin, end);
		condition.setHouseNature(houseNature);
		// 分页查询
		List<PrHouse> prHouses = prHouseService.findHouseByConditionPaged(condition);
		// 查询总数
		int total = prHouseService.getHouseCountByConditionPaged(condition);
		// 分页工具类
		String url = "findPrHouseByEstatePaged.do?estateId=" + (estateId != null ? estateId.toString() : "-1")
				+ "&buildingId=" + (buildingId != null ? buildingId.toString() : "-1") + "&buildingNoId="
				+ (buildingNoId != null ? buildingNoId.toString() : "-1") + "&floorId="
				+ (floorId != null ? floorId.toString() : "-1") + "&typeId="
				+ (typeId != null ? typeId.toString() : "-1");

		NumPageUtil page = new NumPageUtil(url, total, currpage, size);
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域
		page.setList(prHouses);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);
		map.put("condition", condition);
		map.put("estateId", estateId);
		map.put("buildingNoId", buildingNoId);
		map.put("buildingId", buildingId);
		map.put("floorId", floorId);
		map.put("typeId", typeId);
		map.put("houseNature", houseNature);
		map.put("estates", estates);
		return "prh/prHouse/prHouse.jsp";
	}

	// 转到新增
	@RequestMapping("toPrHouseAdd.do")
	public String toPrHouseAdd(Integer estateId, ModelMap map) {

		List<BuildingFloor> buildingFloors = prHouseService.getAllBuildingFloor();
		List<BuildingNo> buildingNos = prHouseService.getAllBuildingNo(estateId);
		List<Estate> estates = prHouseService.getAllEstate();
		List<RoomType> roomTypes = prHouseService.getAllRoomType();

		map.put("buildingFloors", buildingFloors);
		map.put("buildingNos", buildingNos);
		map.put("estates", estates);
		map.put("roomTypes", roomTypes);
		return "prh/prHouse/prHouseAdd.jsp";
	}

	// 新增
	@RequestMapping("prHouseAdd.do")
	@ResponseBody
	public String prHouseAdd(PrHouse prHouse, ModelMap map, HttpSession session) {
		Users user = (Users) session.getAttribute(Global.USER);

		Integer roomNoId = Integer
				.parseInt(prHouse.getRoomNo().substring(prHouse.getBuildingFloorId().toString().length()));
		prHouse.setRoomNoId(roomNoId);
		prHouse.setNo(prHouse.getBuildingNoId() + "-" + prHouse.getRoomNo());

		PrHouse findByNo = prHouseService.findByNo(prHouse.getNo());
		if (findByNo != null) {
			return "0"; // 房号相同
		}

		prHouse.setUpdateuser(user.getId().toString());
		prHouse.setUpdatetime(new Date());

		prHouseService.addPrHouse(prHouse);
		return "";
	}

	// 删除
	@RequestMapping("prHouseDel.do")
	public String prHouseDel(Integer[] chk, Integer estateId, ModelMap map, HttpSession session) {
		try {
			prHouseService.delPrHouse(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}

		Integer currpage = (Integer) session.getAttribute("currpage");
		return "../findPrHouseByEstatePaged.do?currpage=" + currpage + "&estateId=" + estateId;
	}

	// 转到修改
	@RequestMapping("toPrHouseEdit.do")
	public String toPrHouseEdit(Integer id, ModelMap map) {
		PrHouse prHouse = prHouseService.findById(id);
		int estateId = prHouse.getEstateId();

		map.put("prHouse", prHouse);
		map.put("estateId", estateId);
		// 三个复选框
		List<BuildingFloor> buildingFloors = prHouseService.getAllBuildingFloor();
		List<BuildingNo> buildingNos = prHouseService.getAllBuildingNo(estateId);
		List<RoomType> roomTypes = prHouseService.getAllRoomType();

		map.put("buildingFloors", buildingFloors);
		map.put("buildingNos", buildingNos);
		map.put("roomTypes", roomTypes);

		return "prh/prHouse/prHouseEdit.jsp";
	}

	// 修 改
	@RequestMapping("prHouseEdit.do")
	@ResponseBody
	public String prHouseEdit(PrHouse prHouse, ModelMap map, HttpSession session) {
		Users user = (Users) session.getAttribute(Global.USER);
		PrHouse oldHouse = prHouseService.findById(prHouse.getId());
		Integer roomNoId = Integer
				.parseInt(prHouse.getRoomNo().substring(prHouse.getBuildingFloorId().toString().length()));

		prHouse.setRoomNoId(roomNoId);
		prHouse.setNo(prHouse.getBuildingNoId() + "-" + prHouse.getRoomNo());
		prHouse.setHouseCode(oldHouse.getHouseCode());
		prHouse.setState(oldHouse.getState());
		PrHouse findByNo = prHouseService.findByNo(prHouse.getNo());

		prHouse.setUpdateuser(user.getId().toString());
		prHouse.setUpdatetime(new Date());
		if (findByNo != null && !findByNo.getNo().equals(oldHouse.getNo())) {
			return "input";
		}
		prHouseService.updatePrHouse(prHouse);

		return "success";
	}

	// 转到自动新增
	@RequestMapping("toPrHouseAutoAdd.do")
	public String toPrHouseAutoAdd() {
		return "prh/prHouse/prHouseAutoAdd.jsp";
	}

	// 自动新增
	@RequestMapping("prHouseAutoAdd.do")
	public String prHouseAutoAdd(@RequestParam(value = "file", required = false) MultipartFile file,
								 HttpServletRequest request, ModelMap map, HttpSession session) throws FileNotFoundException, IOException {
		try {
			Users user = (Users) session.getAttribute(Global.USER);
			// 如果有上传文件
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				// 获取文件名
				String fileName = file.getOriginalFilename();
				int z = fileName.indexOf(".");
				// 获取后缀
				String houzui = fileName.substring(z + 1);

				if (houzui.equals("xls") || houzui.equals("xlsx")) {

					// 新文件名
					String newfilename = "xxx" + "." + houzui;
					// 获取文件夹路径
					String path = request.getSession().getServletContext().getRealPath("upload/excel");
					// 创建目标文件
					File targetFile = new File(path, newfilename);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						file.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// 向数据库添加数据
					String[][] result = ExcelOperate.getData(targetFile, 1);
					// 房屋集合
					List<PrHouse> prHouses = prHouseService.readByExcelStr(result, user);

					if (prHouses == null) {
						map.remove("autoSuccess");
						map.put("tip", "表格信息有误！请检查数据！");
						return "prh/prHouse/prHouseAutoAdd.jsp";
					}
					map.remove("error");
					map.put("autoSuccess", "");
					prHouseService.addAuto(prHouses);
				} else { // 格式不正确

					map.put("tip", "文件不是excel格式！");
					return "prh/prHouse/prHouseAutoAdd.jsp";
				}

			} else { // 没有导入文件
				map.put("tip", "请导入excel文件！");
				return "prh/prHouse/prHouseAutoAdd.jsp";
			}
			map.put("tip", "导入成功！");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("tip", "导入失败，请使用Excel 2007以上版本！");
			return "prh/prHouse/prHouseAutoAdd.jsp";

		}
		return "prh/prHouse/prHouseAutoAdd.jsp";
	}

	// 房态图
	@RequestMapping("prHouseDrawing")
	public String prHouseDrawing(Integer estateId, String buildingId, String buildingNoId, Integer floor,
								 Integer typeId, Double minArea, Double maxArea, Double minPrice, Double maxPrice, ModelMap map) {

		if (floor != null && floor == -1) {
			floor = null;
		}
		if (typeId != null && typeId == -1) {
			typeId = null;
		}
		// 查询物业集合
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		Estate estate = estateService.findById(estateId);
		map.put("estate", estate);
		map.put("estateId", estateId); // 当前物业
		map.put("estates", estates); // 所有物业

		// 选中的楼号集合
		List<Building> checkBuildings = buildingService.findByEstatePositioned(estateId);

		map.put("checkBuildings", checkBuildings);

		// 楼幢集合
		List<Building> buildings = buildingService.findAllByEstate(estateId);
		if (buildingId == null) {
			if (buildings != null && buildings.size() != 0) {
				buildingId = buildings.get(0).getId();
			}
		}
		map.put("buildings", buildings);
		map.put("buildingId", buildingId);
		// 当前楼幢对象
		Building building = buildingService.findById(buildingId);
		// 楼号集合
		List<BuildingNo> buildingNos = new ArrayList<BuildingNo>();
		// 截取楼号集合
		if (building != null) {
			String buildingnocode = building.getBuildingnocode();
			String[] split = buildingnocode.split("、");
			for (String s : split) {
				s=s.substring(0, 1);
				BuildingNo buildingNo = buildingNoService.findById(estateId + "-" + building.getName()+"-"+s);
				buildingNos.add(buildingNo);
			}
		}

		if (buildingNoId == null) {
			if (buildingNos != null && buildingNos.size() != 0 && buildings != null && buildings.size() != 0
					&& building != null) {

				buildingNoId = buildingNos.get(0) != null ? buildingNos.get(0).getUnitName().substring(0, 1): null;
			}
		}

		List<PrHouse> houses = null;
		System.out.println(buildingNoId);
		// 房源统计
		List<Integer[]> eachCount = new ArrayList<Integer[]>();
		String buildingName = building != null ? building.getName() : null;
		for (int i = 0; i < 3; i++) {
			Integer one = prHouseService.getCountByEach(6, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null);
			Integer two = prHouseService.getCountByEach(5, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null)
					+ prHouseService.getCountByEach(2, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null)
					+ prHouseService.getCountByEach(1, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null)
					+ prHouseService.getCountByEach(3, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null);
			Integer three = prHouseService.getCountByEach(4, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null);
			Integer four = prHouseService.getCountByEach(7, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null)
					+ prHouseService.getCountByEach(8, estateId, i == 2 ? buildingNoId : null,
					i != 0 ? buildingName : null);
			Integer[] line = new Integer[] { one, two, three, four };
			eachCount.add(line);
		}

		map.put("eachCount", eachCount);

		// 条件查询
		HouseCondition condition = new HouseCondition(buildingNoId, floor, typeId, minArea, maxArea,
				minPrice,maxPrice);
		condition.setBuildingId(building!=null? building.getName():null);
		condition.setEstateId(estateId);
		map.put("condition", condition);
		// 条件查询房屋信息
		houses = prHouseService.findByCondition(condition);
		Integer maxRoomNoId = 0;
		for (PrHouse h : houses) {
			if (h.getRoomNoId() > maxRoomNoId) {
				maxRoomNoId = h.getRoomNoId();
			}
		}
		map.put("maxRoomNoId", maxRoomNoId);

		// 楼层集合
		List<BuildingFloor> floors = buildingFloorService.findAll();
		map.put("floors", floors);
		// 房间类型集合
		List<RoomType> typeIds = roomTypeService.findAll();
		map.put("types", typeIds);

		map.put("houses", houses);
		map.put("buildingNos", buildingNos);
		map.put("buildingNoId", buildingNoId);

		// 查询房屋
		return "prh/prHouse/prHouseDrawing.jsp";
	}

	// 转到解锁上锁页面
	@RequestMapping("toLockHouse.do")
	public String toLockHouse(Integer isLock, Integer[] houseIds, ModelMap map) {

		map.put("isLock", isLock);
		map.put("houseIds", houseIds);
		return "prh/prHouse/prHouseLock.jsp";
	}

	// 解锁&&上锁
	@RequestMapping("lockHouse.do")
	@ResponseBody
	public String lockHouse(Integer[] houseIds, Integer isLock, String remark, ModelMap map, HttpSession session) {
		Users user = (Users) session.getAttribute(Global.USER);
		List<PrHouse> houses = new ArrayList<PrHouse>();
		if (houseIds != null && houseIds.length != 0) {
			for (Integer houseId : houseIds) {
				houses.add(prHouseService.findById(houseId));
			}
		}
		// 加锁
		if (isLock == 1) {
			for (PrHouse prHouse : houses) {
				if (prHouse.getState() != 6 || prHouse.getState() == 4) {
					return "3"; // 锁定失败
				}
			}

			for (PrHouse prHouse : houses) {
				prHouse.setState(Short.valueOf("4"));
				prHouse.setRemark(remark);
				prHouse.setUpdateuser(user.getId().toString());
				prHouse.setUpdatetime(new Date());
				prHouseService.updatePrHouse(prHouse);

			}
			return "1";// 锁定成功
			// 解锁
		} else {
			for (PrHouse prHouse : houses) {
				if (prHouse.getState() != 4 || prHouse.getState() == 6) {
					return "4"; // 解锁定失败
				}
			}
			for (PrHouse prHouse : houses) {
				prHouse.setState(Short.valueOf("6"));
				prHouse.setRemark(remark);
				prHouse.setUpdateuser(user.getId().toString());
				prHouse.setUpdatetime(new Date());
				if (prHouse.getIsTui()!=null&&prHouse.getIsTui()==1) {
					prHouse.setIsTui(null);
				}
				prHouseService.updatePrHouse(prHouse);
			}
			return "2";// 解锁成功
		}

	}

	@RequestMapping("updateCode")
	public String updateCode() {

		/*
		 * List<PrHouse> houses = prHouseService.findByCondition(new
		 * HouseCondition()); for (PrHouse house: houses) { String id =
		 * house.getId().toString(); int length=10-id.length(); String
		 * before=""; for (int i = 0; i < length; i++) { before+="0"; }
		 * house.setHouseCode(before+id); prHouseService.updatePrHouse(house);
		 */

		List<Estate> all = estateService.findAll();
		for (Estate estate : all) {
			String id = estate.getId().toString();
			int length = 16 - id.length();
			String before = "";
			for (int i = 0; i < length; i++) {
				before += "0";
			}
			estate.setAuthorCode(before + id);
			estateService.updateEstate(estate);
			System.out.println("更新成功");
		}

		return "";
	}

	@RequestMapping("getLockedInfo.do")
	@ResponseBody
	public String[] getLockedInfo(Integer houseId, ModelMap map) {
		String[] houseArray = new String[10];
		PrHouse house = prHouseService.findById(houseId);

		houseArray[0] = house.getRemark() != null ? house.getRemark() : "尚无记录";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Users user = null;
		if (house.getUpdateuser() != null) {
			user = userService.findById(Integer.valueOf(house.getUpdateuser()));
		}
		houseArray[1] = user != null ? user.getName() : "尚无记录";
		if (house.getUpdatetime() != null) {
			houseArray[2] = df.format(house.getUpdatetime());
		} else {
			houseArray[2] = "尚无记录";
		}

		return houseArray;
	}
}
