package com.rent.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.If;
import com.rent.common.mapper.JsonMapper;
import com.rent.common.utils.GenerateSequenceUtil;
import com.rent.common.utils.MyConvertUtil;
import com.rent.common.utils.NumPageUtil;
import com.rent.condition.FileCondition;
import com.rent.condition.HouseFileContion;
import com.rent.condition.MasterCondition;
import com.rent.dao.PrhMasterMapper;
import com.rent.dao.ProfileMapper;
import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.NameAndId;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhMaster;
import com.rent.entity.Profile;
import com.rent.entity.RoomType;
import com.rent.modules.sys.entity.Repaire;
import com.rent.services.BuildingFloorService;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.services.FileManagementService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhMasterService;
import com.rent.services.RoomTypeService;




@Controller("prFileManagementController")
public class PrFileManagementController {
	
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
	private ProfileMapper profileMapper;
	
	@Autowired
	private EstateService estateService;
	@Autowired
	private PrhMasterService prhMasterService;

	@Autowired
	private PrhMasterMapper prhMasterMapper;
	

	public PrhMasterMapper getPrhMasterMapper() {
		return prhMasterMapper;
	}

	public void setPrhMasterMapper(PrhMasterMapper prhMasterMapper) {
		this.prhMasterMapper = prhMasterMapper;
	}

	public ProfileMapper getProfileMapper() {
		return profileMapper;
	}

	public void setProfileMapper(ProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}

	@RequestMapping("prUtilFile")
	public String findProFileByClass(Integer estateId,String sta, String name, Integer currpage,
			ModelMap map, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException{
		if (request.getMethod().equals("GET")) {
			name= request.getParameter("name");
			
			if (name!=null) {
				name= new String(name.getBytes("iso-8859-1"),"utf-8");	
			}
			
		}
		if (name!=null&&name.length()==0) {
			name=null;
		}
		if (name!=null) {
			/*System.out.println(name);
			
			System.out.println("utf-8\t"+name);*/
			
		}
		
		
		map.put("name", name);
		String cla="C";
		NumPageUtil page;
		
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		
		Integer size = 10; 
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		
		FileCondition condition = new FileCondition();
		condition.setCla(cla);	
		condition.setCompany(name);
		condition.setSta(estateId!=null? estateId.toString().trim():null);
		
		
		int total = fileManagementService.findCompanyByEstateCount(estateId, name);
		
		
		List<Profile> proUtilFile2 = fileManagementService.findCompanyByEstatePaged(estateId, name, currpage, size);
	
		
		if(sta==null){
			sta="";
		}
		if (name == null) {
			name = "";
		}
		
		
		page = new NumPageUtil(

				"prUtilFile?cla=" + cla + "&estateId=" + estateId + "&name=" + name, total, currpage, size);
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		page.setList(proUtilFile2);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		map.put("estateId", estateId);
		map.put("estates", estates);

		return "prh/prFileManager/prUtilFile.jsp";
	}
	
	@RequestMapping("prUtilFileDetail")
	public String findProFileDetail(String guestno,ModelMap map, HttpSession session, HttpServletRequest request){
		
		//根据文档编号查询所有租户信息
		List<PrhMaster> masters = prhMasterService.findByCusNo(guestno);
		map.put("masters", masters);
		return "prh/prFileManager/prUtilFileDetail.jsp";
	}
	
	@RequestMapping("prMasterFile")
	public String findProFileByCondition(String no,String buildingNoId,Integer estateId, String name, Integer currpage,
			ModelMap map, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException{
		if (request.getMethod().equals("GET")) {
			name= request.getParameter("name");
			if (name!=null) {
				name= new String(name.getBytes("iso-8859-1"),"utf-8");	
			}
		}
		String cla="G";
		NumPageUtil page;
		
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; 
		List<Estate> estates = prHouseService.getAllEstate();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		
		MasterCondition condition = new MasterCondition();
		if(no==null){
			no="";
		}
		if(buildingNoId==null){
			buildingNoId="";
		}
		
		
		
		if(name==null){
			name="";
		}
		
		condition.setName(name);
		condition.setSta(estateId!=null?estateId.toString():null);
		condition.setEstateId(estateId);
		condition.setBuildingNoId(buildingNoId);
		condition.setNo(no);
		System.out.println(name);
		//条件查询主单信息
		
		Integer total = prhMasterService.findAllCountByCondition(condition);
		
		
		
		//条件  查询所有 主单信息 并分页
		
		List<PrhMaster> prhMasters =prhMasterService.findAllByConditionAndPaged(condition, currpage, size);
		
	
		
			 page = new NumPageUtil(
					
				"prMasterFile?cla="+cla+"&name="+name+"&estateId="+estateId, total,
				currpage, size);
				page.setBothnum(3);
				String numpage = page.showNumPage();
				currpage = page.getCurrpage();
				map.put("estateId", estateId);
				map.put("estates", estates);
				map.put("condition", condition);
				page.setList(prhMasters);
				session.setAttribute("currpage", currpage);
				request.setAttribute("numpage", numpage);
				map.put("page", page);
				
			
		
		return "prh/prFileManager/prhMasterFile.jsp";
		
	}

		

	

	@RequestMapping("addUtilFile")
	public String addUtilFile(Profile record,ModelMap map){
		
		Boolean flag = fileManagementService.saveProFile(record);
		if(flag){
			 return ("alert('新增单位成功!');location.href='prUtilFile'");
		}else{
			return "prUtilFile.jsp";
		}
	
	}
	@RequestMapping("updateUtilFile")
	public String updateUtilFile(Profile record,ModelMap map){
		
		Boolean flag = fileManagementService.saveProFile(record);
		if(flag){
			 return ("alert('修改成功！');location.href='prUtilFile'");
		}else{
			return "prUtilFile.jsp";
		}
	
	}
	@RequestMapping("deleteUtilFile")
	public String deleteUtilFile(String guestno,ModelMap map){
		
		Boolean flag = fileManagementService.deleteProFile(guestno);
		if(flag){
			 return ("alert('单位档案删除成功!!');location.href='prUtilFile'");
		}else{
			return "prUtilFile.jsp";
		}
	
	}
	
	

		
	
		//房源档案管理模块
		@RequestMapping("prHouseFileByCondition")
		public String findPrHouseByEstatePaged(String  danyuanid,String
				no,Integer estateId,Integer roomtypeid,String state, Integer currpage, ModelMap map, HttpSession session,
				
				HttpServletRequest request) throws UnsupportedEncodingException {
			
			if (request.getMethod().equals("GET")) {
				no= request.getParameter("no");
				
				if (no!=null) {
					no= new String(no.getBytes("iso-8859-1"),"utf-8");	
				}
				
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
			List<BuildingNo> danyuan =null;
			if (estateId!=null) {
				 danyuan = buildingNoService.findByEstate(estateId);
			}
			if(danyuanid==null){
				danyuanid="";	
			}
			if(danyuanid!=null){
				if(danyuanid.equals("-1")){
					danyuanid="";
				}
			}
			if(roomtypeid==null){
				roomtypeid=-1;
			}
			
			
			
			if(no==null){
				no="";
			}
			if(state!=null){
				if(state.equals("-1")){
					state="";
				}
			}
			HouseFileContion condition = new HouseFileContion();
			condition.setDanyuanid(danyuanid.toString());
			condition.setRoomtypeId(roomtypeid);
			condition.setSta(state);
			condition.setNo(no);
			condition.setEstateId(estateId);		
			List<PrHouse> house = prHouseService.findByFileCondition2(condition);
			Integer total =house.size();
			//查询所有房型集合
			List<RoomType> roomtype=	roomTypeService.findAll();
			
			// 分页查询
		
			List<PrHouse> prHouses = prHouseService.findByFileCondition2Paged(condition, currpage, size);
			
			// 查询总数
			

			// 分页工具类
			if(state==null){
				state="";
			}
			
			
			NumPageUtil page = new NumPageUtil("prHouseFileByCondition?estateId=" + estateId+"&danyuanid="+danyuanid+"&state="+state+"&roomtypeid="+roomtypeid+"&no="+no, total, currpage, size);
			
			page.setBothnum(3);
			String numpage = page.showNumPage();
			currpage = page.getCurrpage();
			//根据房源id 查询租户信息姓名 的方法
			
			List<NameAndId> names =  new ArrayList<>();
			for (PrHouse prHouse : prHouses) {
				Integer houseid = prHouse.getId();
				//根据 房间id  查询 租户 档案id
				String sta ="1";
				NameAndId na = new NameAndId();
				String name =fileManagementService.findNameByHouseId(houseid);
			
			
				
				//根据guestNo 查询 姓名
				na.setId(houseid);
				na.setName(name);
				
				names.add(na);
			}
			
		
			// 存作用域
			page.setList(prHouses);
			session.setAttribute("currpage", currpage);
			request.setAttribute("numpage", numpage);
			map.put("danyuan", danyuan);
			map.put("tip", session.getAttribute("message"));
			session.removeAttribute("message");
			map.put("condition", condition);
			map.put("state", state);
			map.put("page", page);
			map.put("estateId", estateId);
			map.put("names", names);
			map.put("danyuanId",danyuanid);
			map.put("roomtype", roomtype);
			map.put("roomtypeid", roomtypeid);
			map.put("estates", estates);
			
			return "prh/prFileManager/prHouse.jsp";			
		}
		
		
		@RequestMapping("findAllCompany")
		public String findAllCompany(Integer estateId,String sta, String name, Integer currpage,
				ModelMap map, HttpSession session, HttpServletRequest request){
			String cla="C";
			NumPageUtil page;
			
			if (currpage == null || currpage <= 0) {
				currpage = 1;
			}
			Integer size = 10; 
			List<Estate> estates = prHouseService.getAllEstate();
			if (estateId == null) {
				if (estates != null && estates.size() != 0) {
					estateId = estates.get(0).getId();
				}
			}
			
			FileCondition condition = new FileCondition();
			condition.setCla(cla);	
			condition.setCompany(name);
			condition.setSta(estateId!=null? estateId.toString().trim():null);
			int total=fileManagementService.findCompanyByEstateCount(estateId,null);
			List<Profile> proUtilFile2 = fileManagementService.findCompanyByEstatePaged(estateId,null, currpage, size);
		
			
		
			
			if(sta==null){
				sta="";
			}
			if(name==null){
				name="";
			}
				 page = new NumPageUtil(
						
						"findAllCompany?estateId="+estateId, total,
						currpage, size);
					page.setBothnum(3);
					String numpage = page.showNumPage();
					currpage = page.getCurrpage();

					
					page.setList(proUtilFile2);
					session.setAttribute("currpage", currpage);
					request.setAttribute("numpage", numpage);
					map.put("tip", session.getAttribute("tip"));
					session.removeAttribute("tip");
					map.put("page", page);
					map.put("estateId", estateId);
					map.put("estates", estates);
			
			return "prhCode/company/allCompany.jsp";
		}

		@RequestMapping("addCom")
		public String addCom(Integer estateId,String sta, String name, Integer currpage,
				ModelMap map, HttpSession session, HttpServletRequest request){
			map.put("estateId", estateId);
			return "prhCode/company/addCom.jsp";
			
		}
		@RequestMapping("addCompany")
	
		public void addCompany(String company,String liason,String liason1,String  phone,Integer estateId,String remark,
				ModelMap map, HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException{			
			
				System.out.println(liason+"yyyy"+liason1);
			
				Profile profile = new Profile();
				profile.setCompany(company);
				profile.setLiason(liason);
				profile.setLiason1(liason1);
				profile.setPhone(phone);
				profile.setSta("I");
				profile.setClass_("C");
				profile.setEstateId(estateId);
				profile.setGuestno(GenerateSequenceUtil.generateSequenceNo());
				
				profile.setRemark(remark);
				Boolean flag= fileManagementService.saveProFile(profile);
				if(flag){
					PrintWriter out = response.getWriter();	 
				    out.flush();
				    out.println("<script>");	
				    out.println("alert('新增成功！')");
				    out.println("parent.layer.closeAll();");
				    out.println("</script>");
				}else{
					PrintWriter out = response.getWriter();	 
				    out.flush();
				    out.println("<script>");	
				    out.println("alert('新增失败！')");
				    out.println("parent.layer.closeAll();");
				    out.println("</script>");
				}
				
		}
		
		@RequestMapping("updateCom")
		public String updateCom(Integer estateId, String guestno,ModelMap map, 
				HttpSession session,
				HttpServletRequest request,HttpServletResponse response){	
				//根据档案编号查询档案
			Profile profile = fileManagementService.findById(guestno);
			map.put("profile", profile);
			
			map.put("estateId", estateId);
			return "prhCode/company/updateCompany.jsp";
		}
		
		
		
		@RequestMapping("updateCompany")
		public void updateCompany(Profile profile,ModelMap map, 
				HttpSession session,
				HttpServletRequest request,HttpServletResponse response) throws IOException{
			Profile needProfile = fileManagementService.findById(profile.getGuestno());
			needProfile.setCompany(profile.getCompany());
			needProfile.setLiason(profile.getLiason());
			needProfile.setLiason1(profile.getLiason1());
			needProfile.setPhone(profile.getPhone());
			needProfile.setEstateId(profile.getEstateId());
			needProfile.setRemark(profile.getRemark());
			boolean flag = fileManagementService.updateProFile(needProfile);
			
			if(flag){
				PrintWriter out = response.getWriter();	 
			    out.flush();
			    out.println("<script>");	
			    out.println("alert('修改成功！')");
			    out.println("parent.layer.closeAll();");
			    out.println("</script>");
			}else{
				PrintWriter out = response.getWriter();	 
			    out.flush();
			    out.println("<script>");	
			    out.println("alert('修改失败！')");
			    out.println("parent.layer.closeAll();");
			    out.println("</script>");
			}
		}
		
		
		@RequestMapping("delCompany")
		public String delCompany(String guestno,ModelMap map, 
				HttpSession session,
				HttpServletRequest request){
			//判断是否有数据在主单表
			List<PrhMaster> master = prhMasterService.findByGuestNo(guestno);
			if(master.size()==0){
				//查询这个档案
				Profile needProfile = fileManagementService.findById(guestno);
				needProfile.setSta("N");
				boolean flag = fileManagementService.updateProFile(needProfile);
				if(flag){
					session.setAttribute("tip", "删除成功！");
					return  "redirect:/ findAllCompany";	
				}else{
					session.setAttribute("tip", "删除失败！");
					return  "redirect:/ findAllCompany";
				}
			}else{
				session.setAttribute("tip", "此单位存在租房记录无法删除！");
				return  "redirect:/ findAllCompany";
			}
			
		}
		
		
		
		@RequestMapping("updateFileNo.do")
		public String updateFileNo(String []fileNos,String[] masterIds, Integer estateId, HttpSession session){
			for (int i = 0; i < fileNos.length; i++) {
				System.out.println(masterIds[i]+":"+fileNos[i]);
				if (fileNos[i]!=null&&fileNos[i].length()!=0) {
					PrhMaster master=new PrhMaster();
					master.setId(Integer.valueOf(masterIds[i]));
					master.setFileNo(fileNos[i]);
					prhMasterMapper.updateByPrimaryKeySelective(master);
				}
			}
			
			Integer currpage = MyConvertUtil.getCurrpageBySession(session);
			return "../prMasterFile?estateId="+estateId+"&currpage="+currpage;
		}
		
		
}

	

