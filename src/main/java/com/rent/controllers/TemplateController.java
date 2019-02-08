package com.rent.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.common.utils.NumPageUtil;
import com.rent.condition.TempCondition;
import com.rent.entity.BuildingNo;
import com.rent.entity.Equipment;
import com.rent.entity.Estate;
import com.rent.entity.RmdevTemp;
import com.rent.entity.RoomType;
import com.rent.services.BuildingNoService;
import com.rent.services.EquipmentService;
import com.rent.services.EstateService;
import com.rent.services.PrhRmdevService;
import com.rent.services.RmdevTempService;
import com.rent.services.RoomTypeService;

@Controller
public class TemplateController {
	@Autowired
	private RmdevTempService rmdevTempService;
	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private RoomTypeService roomTypeService;

	@Autowired
	private EstateService estateService;

	@Autowired
	private BuildingNoService buildingNoService;

	@Autowired
	private PrhRmdevService prhRmdevService;

	public PrhRmdevService getPrhRmdevService() {
		return prhRmdevService;
	}

	public void setPrhRmdevService(PrhRmdevService prhRmdevService) {
		this.prhRmdevService = prhRmdevService;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}

	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
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

	public RmdevTempService getRmdevTempService() {
		return rmdevTempService;
	}

	public void setRmdevTempService(RmdevTempService rmdevTempService) {
		this.rmdevTempService = rmdevTempService;
	}

	// 列表
	@RequestMapping("templateList.do")
	public String templateList(Integer roid, Integer currpage, String typename, ModelMap map, HttpSession session,
			HttpServletRequest request) {

		// 查询所有模板信息
		// 查询所有物业
		// 查询所有单元
		// 查询所有房屋类型

		List<Estate> estates = estateService.findAll();
		List<RoomType> roomtypes = roomTypeService.findAll();
		List<BuildingNo> danyuan = buildingNoService.findAll();

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小

		if (roid == null || roid <= 0) {
			roid = 1;
		}
		// 根据roid 查询所有信息
		List<RmdevTemp> temp = rmdevTempService.findByRoid(roid);
		int total = temp.size();
		TempCondition condition = new TempCondition();
		condition.setRoid(roid);

		// 分页查询
		List<RmdevTemp> temp1 = rmdevTempService.findByRoidAndPaged(condition, currpage, size);
		// 查询总数
		// 分页工具类

		NumPageUtil page = new NumPageUtil("templateList.do?roid=" + roid, total, currpage, size);
		List<Equipment> equipment = new ArrayList<>();
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		for (RmdevTemp rmdevTemp : temp1) {
			System.out.println(rmdevTemp.getRoomtype());
			System.out.println(rmdevTemp.getEqid());

			BigDecimal a = new BigDecimal(rmdevTemp.getEqid().toString());
			int b = a.intValue();

			int id = b;

			// 根据 设备id 查询 设备信息 存到equipment中
			Equipment eq = equipmentService.findById(id);
			equipment.add(eq);

		}
		// 存作用域
		page.setList(temp1);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		map.put("temp", temp);
		map.put("equipment", equipment);
		map.put("roid", roid);
		map.put("roomtypes", roomtypes);
		map.put("estates", estates);
		map.put("roomtypes", roomtypes);
		map.put("danyuan", danyuan);
		return "prhCode/template/tempList.jsp";
	}

	// 删除
	@RequestMapping("delTemplateItem.do")
	public String rentPayWayDel(Integer[] chk, ModelMap map, HttpSession session) {
		System.out.println("a"+chk[0]);
		Integer currpage =null;
		try {
			
			currpage = (Integer) session.getAttribute("currpage");
			rmdevTempService.findById(chk[0]).getRoomtype();
			rmdevTempService.delRmdevTemp(chk);
			map.put("tip", "删除成功！");
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		return "../templateList.do?currpage=" + currpage;
	}

	// 转到编辑设备模板
	@RequestMapping("toTemplateItem.do")
	public String toTemplateItem(Integer typeId, ModelMap map, HttpSession session) {
		RoomType roomType = roomTypeService.findById(typeId);
		List<Equipment> equipments = equipmentService.findAllEqu();
		List<RmdevTemp> checkedTemplates = rmdevTempService.findByRoid(typeId);
		Map<Integer, Integer> numsMap = new HashMap<Integer, Integer>();
		for (RmdevTemp temp : checkedTemplates) {
			Integer key = Integer.valueOf(temp.getEqid().toString());
			Integer value = Integer.valueOf(temp.getNum().toString());

			if (numsMap.containsKey(key)) {
				numsMap.put(key, numsMap.get(key) + value);
			}
			numsMap.put(key, value);
		}

		map.put("numsMap", numsMap);
		map.put("equipments", equipments);
		map.put("roomType", roomType);
		map.put("typeId", typeId);

		return "prhCode/template/templateItem.jsp";
	}

	// 编辑设备模板提交
	@RequestMapping("templateAdd.do")
	public String templateAdd(Integer typeId,Integer [] chk, ModelMap map, HttpSession session,HttpServletRequest request) {
		//取出数量
		Integer [] nums=new Integer[chk.length];
		
		for (int i = 0; i < nums.length; i++) {
			nums[i]=Integer.parseInt(request.getParameter("num"+chk[i]));
			System.out.println(chk[i]+"-"+nums[i]);
		}
		
		rmdevTempService.editTemplate(chk, nums, typeId);
		
		
		Integer currpage = (Integer) session.getAttribute("currpage");
		
		return "../templateList.do?roid="+typeId+"&currpage="+currpage;
	}
}
