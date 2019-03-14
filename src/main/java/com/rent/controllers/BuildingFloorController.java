package com.rent.controllers;

import com.rent.common.utils.NumPageUtil;
import com.rent.entity.BuildingFloor;
import com.rent.services.BuildingFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author landwind
 */
@Controller
public class BuildingFloorController {
	
	@Autowired
	private BuildingFloorService buildingFloorService;

	@RequestMapping("findAllBuildingFloor.do")
	public String getAll(ModelMap map){
		List<BuildingFloor> buildingFloors = buildingFloorService.findAll();
		map.put("buildingFloors", buildingFloors);
		
		return "prhCode/buildingFloor/buildingFloor.jsp";
	}


	@RequestMapping("findAllBuildingFloorPaged.do")
	public String findAllBuildingFloorPaged( Integer currpage, ModelMap map, HttpSession session, HttpServletRequest request) {
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10;

		List<BuildingFloor> buildingFloors = buildingFloorService.findAllPaged(currpage, size);

		int total = buildingFloorService.getTotalCount();

		NumPageUtil page = new NumPageUtil("findAllBuildingFloorPaged.do", total, currpage, size);
		
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		page.setList(buildingFloors);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);
		
		return "prhCode/buildingFloor/buildingFloor.jsp";
	}
	
	@RequestMapping("toBuildingFloorAdd")
	public String  toBuildingFloorAdd(){
		return "prhCode/buildingFloor/buildingFloorAdd.jsp";
	}

	@RequestMapping("addBuildingFloor.do")
	public String addBuildingFloor(Integer begin,Integer end){
		
		int result = buildingFloorService.autoAddBuildingFloors(begin, end);

		if (result==-1) {
			return  "prhCode/buildingFloor/floorBuildingAdd.jsp";
		}
		return "../findAllBuildingFloorPaged.do";
	}
	

	@RequestMapping("toEditBuildingFloor.do")
	public String toEditBuildingFloor(Integer id, ModelMap map) {
		BuildingFloor floor = buildingFloorService.findById(id);
		map.put("buildingFloor", floor);
		return "prhCode/buildingFloor/buildingFloorEdit.jsp";
	}

	@RequestMapping("editBuildingFloor.do")
	public String editBuildingFloor(BuildingFloor floor) {
		int result = buildingFloorService.updateBuildingFloor(floor);
		if (result==-1) {
			return  "prhCode/buildingFloor/buildingFloorEdit.jsp";
		}
		return "../findAllBuildingFloorPaged.do";
	}

	@RequestMapping("delBuildingFloor.do")
	public String delFloorBuilding(Integer[] chk,ModelMap map,HttpSession session){
		Integer currpage= (Integer)session.getAttribute("currpage");
		try {
			buildingFloorService.delBuildingFloor(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
			
		}
		return "../findAllBuildingFloorPaged.do?currpage="+currpage;
	}

}
