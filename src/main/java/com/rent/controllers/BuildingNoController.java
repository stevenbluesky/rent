package com.rent.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;


import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.common.utils.NumPageUtil;
import com.rent.condition.CompareBuildingNo;

@Controller("buildingNoController")
@Transactional(readOnly=false)
public class BuildingNoController {
	@Autowired
	private BuildingNoService buildingNoService;

	@Autowired 
	private EstateService estateService;
	
	@Autowired
	private BuildingService buildingService;
	
	
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
	
    	//转到新增楼层页面
		@RequestMapping("toBuildingNoAdd.do")
		public String toBuildingNoAdd(Integer estateId, ModelMap map) {
			//List<Estate> estates = buildingNoService.getAllEstate();
			Estate estate= estateService.findById(estateId);
			
			map.put("estate", estate);
			
			return "prhCode/buildingNo/buildingNoAdd.jsp";
		}
		
		//查询全部
		@RequestMapping("findAllBuildingNo.do")
		public String findAllBuildingNo(ModelMap map){
			List<BuildingNo> buildingNos = buildingNoService.findAll();
			map.put("buildingNos", buildingNos);
			return "prhCode/buildingNo/buildingNo.jsp";
		}
	
		//分页查询
		@RequestMapping("findBuildingNoByEstatePaged.do")
		public String findByEstate(Integer estateId,Integer currpage, ModelMap map,HttpSession session,HttpServletRequest request){
			//处理当前页
			if (currpage==null||currpage<=0) {
				currpage=1;
			}
			
			Integer size=10;  //页大小
			
			//查询物业集合
			
			List<Estate> estates =buildingNoService.getAllEstate();
			if (estateId==null) {
				if (estates!=null&&estates.size()!=0) {
					estateId= estates.get(0).getId();
				}
			}
			
			//分页查询
			List<BuildingNo> buildingNos= buildingNoService.findByEstatePaged(estateId, currpage, size);
			
			//查询总数
			int total = buildingNoService.getCountByEstate(estateId);
			
		
			//分页工具类
			NumPageUtil page = new NumPageUtil("findBuildingNoByEstatePaged.do?estateId="+estateId, total,
					currpage, size);
			page.setBothnum(3);
			String numpage = page.showNumPage();
			currpage = page.getCurrpage();
			
			//存作用域
			page.setList(buildingNos);
			session.setAttribute("currpage", currpage);
			request.setAttribute("numpage", numpage);
			
			map.put("page", page);
			map.put("estateId", estateId);
			map.put("estates", estates);
			return "prhCode/buildingNo/buildingNo.jsp";
		}
		
		//新增
		@RequestMapping("BuildingNoAdd.do")
		public String BuildingNoAdd(BuildingNo buildingNo, ModelMap map){
			 int result = buildingNoService.addBuildingNo(buildingNo);
			 if (result==-1) {
				return "toBuildingNoAdd.do";
			}
			 map.put("tip", "新增成功");
			return "../findBuildingNoByEstatePaged.do";
		}
		
		//转到批量新增（传值）
		@RequestMapping("toAutoAddBuildingNo.do")
		public String toAutoAddBuildingNo(Integer estateId,ModelMap map) {
			
			
			map.put("estateId", estateId);
			
			return "prhCode/buildingNo/buildingNoAutoAdd.jsp";
		}
		
		//批量新增
		@RequestMapping("autoAddBuildingNo.do") 
		@Transactional(readOnly=false)
		public String autoAddBuildingNo(Integer estateId, Integer begin,Integer end, ModelMap map,HttpServletRequest request){
			//批量删除此区间
			//批量新增
			for (int i = begin; i <= end; i++) {
				
				String id=estateId+"-"+i;
				String name=i+"单元";
				
				BuildingNo build=new BuildingNo(id, name, null, estateId, null, null);
				buildingNoService.addBuildingNo(build);
				System.out.println("新增"+i);
			}
			 map.put("tip", "新增成功！");
			return "../findBuildingNoByEstatePaged.do"; 
		}
		
		//转到修改页面
		@RequestMapping("toEditBuildingNo.do")
		public String toEditBuildingNo(String id, ModelMap map){
			 BuildingNo buildingNo = buildingNoService.findById(id);
			 map.put("buildingNo", buildingNo);
			 Estate estate= estateService.findById(buildingNo.getEstateId());
			 map.put("estate", estate);
			 
			
			return "prhCode/buildingNo/buildingNoEdit.jsp";
		}
		
		//修改 
		@RequestMapping("editBuildingNo.do")
		public String editBuildingNo(BuildingNo build, ModelMap map){
				buildingNoService.updateBuildingNo(build);
			 map.put("tip", "修改成功！");
			return "../findBuildingNoByEstatePaged.do";
		}
		
		//删除
		@RequestMapping("delBuildingNo.do")
		public String delBuildingNo(String [] chk,ModelMap map,HttpSession session){
			//当前页
			Integer currpage = (Integer)session.getAttribute("currpage");
			//物业id
			Integer estateId=null;
			if (chk!=null&&chk.length!=0) {
				BuildingNo buildingNo = buildingNoService.findById(chk[0]);
				estateId=buildingNo.getEstateId();
			   boolean flag=true;
			   for (String id : chk) {
				   if (buildingNoService.findById(id).getBuildingId()!=null) {
					   flag=false;
					   break;
				   }
			   }
			   
			   if (flag) {
				   buildingNoService.delBuildingNo(chk);
				   map.put("tip", "删除成功！");
			   }else{
				   map.put("tip", "该条数据已被关联，请勿删除！");
			   }
			}
			return "../findBuildingNoByEstatePaged.do?currpage="+currpage+"&estateId="+estateId;
		}

		//根据物业和区间获取楼号集合返回ajax
		@RequestMapping("getBuildNoByRange.do")
		@ResponseBody
		public List<BuildingNo> getNosByEstateRange(Integer estateId,Integer begin,Integer end,Integer buildingId, ModelMap map){
			
			List<BuildingNo> bNos = buildingNoService.getAllByRangeEstate(estateId, begin, end);
			if (buildingId!=null) {
				List<BuildingNo> no2 = buildingNoService.findByBuildingIdAndEstate(buildingId, estateId);
				for (BuildingNo no : no2) {
					no.setRemark("#!%2");
				}
				bNos.addAll(no2);
				System.out.println(no2.size());
			}
			CompareBuildingNo com=new CompareBuildingNo();
			Collections.sort(bNos,com);
			
			return bNos;
			
		}
		
}
