package com.rent.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.services.BuildingFloorService;
import com.rent.common.utils.NumPageUtil;

import com.rent.entity.BuildingFloor;

@Controller
public class BuildingFloorController {
	
	@Autowired
	private BuildingFloorService buildingFloorService;
	//��ѯȫ��
	@RequestMapping("findAllBuildingFloor.do")
	public String getAll(ModelMap map){
		List<BuildingFloor> buildingFloors = buildingFloorService.findAll();
		map.put("buildingFloors", buildingFloors);
		
		return "prhCode/buildingFloor/buildingFloor.jsp";
	}

	// ��ҳ��ѯ
	@RequestMapping("findAllBuildingFloorPaged.do")
	public String findAllBuildingFloorPaged( Integer currpage,
			ModelMap map, HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С
		
		// ��ҳ��ѯ
		List<BuildingFloor> buildingFloors = buildingFloorService.findAllPaged(currpage, size);
		
		// ��ѯ����
		int total = buildingFloorService.getTotalCount();
		// ��ҳ������
		NumPageUtil page = new NumPageUtil(
				"findAllBuildingFloorPaged.do", total,
				currpage, size);
		
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
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
	//����
	@RequestMapping("addBuildingFloor.do")
	public String addBuildingFloor(Integer begin,Integer end, ModelMap map,HttpServletResponse response) throws IOException{
		
		int result = buildingFloorService.autoAddBuildingFloors(begin, end);
		if (result==-1) {
			return  "prhCode/buildingFloor/floorBuildingAdd.jsp";
		}
		return "../findAllBuildingFloorPaged.do";
	}
	
	//ת���޸�ҳ��				
	@RequestMapping("toEditBuildingFloor.do")
	public String toEditBuildingFloor(Integer id, ModelMap map) {
		BuildingFloor floor = buildingFloorService.findById(id);
		map.put("buildingFloor", floor);
		return "prhCode/buildingFloor/buildingFloorEdit.jsp";
	}
	//�޸�			  	 
	@RequestMapping("editBuildingFloor.do")
	public String editBuildingFloor(BuildingFloor floor, ModelMap map) {
		int result = buildingFloorService.updateBuildingFloor(floor);
		if (result==-1) {
			return  "prhCode/buildingFloor/floorBuildingEdit.jsp";
		}
		return "../findAllBuildingFloorPaged.do";
	}
	//ɾ��
	@RequestMapping("delBuildingFloor.do")
	public String delFloorBuilding(Integer[] chk,ModelMap map,HttpSession session){
		//ȡ��ǰҳ
		Integer currpage= (Integer)session.getAttribute("currpage");
		try {
			buildingFloorService.delBuildingFloor(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
			
		}
		
		return "../findAllBuildingFloorPaged.do?currpage="+currpage;
	}
		
	public BuildingFloorService getBuildingFloorService() {
		return buildingFloorService;
	}
	public void setBuildingFloorService(BuildingFloorService buildingFloorService) {
		this.buildingFloorService = buildingFloorService;
	}
	
}
