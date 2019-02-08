package com.rent.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.common.mapper.JsonMapper;
import com.rent.common.persistence.Page;
import com.rent.common.utils.MyRentBiz;
import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.modules.sys.entity.Rent;
import com.rent.modules.sys.entity.Repaire;
import com.rent.modules.sys.service.SystemService;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.services.RenterService;

@Controller
@RequestMapping(value = "${adminPath}/sys/rent")
public class RentController {

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private EstateService estateService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private BuildingNoService buildingNoService;
	
	@RequestMapping(value = {"index", ""})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model,ModelMap map) {
	
		model.addAttribute("parentId","1");
		return "views/modules/sys/sysIndex.jsp";
	}
	
	@RequestMapping(value = {"thirdIndex", ""})
	public String indexThirdPart(HttpServletRequest request, HttpServletResponse response, Model model,ModelMap map) {
	
		model.addAttribute("parentId","1");
		return "views/modules/sys/thirdRepaireIndex.jsp";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Rent rent,HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("tenements",estateService.findAll());
		model.addAttribute("houseStatus",SystemService.houseStatus);
		
		Estate estate = (Estate)request.getSession().getAttribute("currentTen");
		// 物业集合
		List<Estate> estates = estateService.findAll();
		if(estate == null) {
			estate = MyRentBiz.getEstates(estates);
		}

		//单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estate.getId());
		model.addAttribute("buildingNos", buildingNos);
				
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(estate.getId());
		model.addAttribute("buildings", buildings);
		if(rent != null) {
			rent = new Rent();
		}
		rent.setTenement(estate.getName());
		// model.addAttribute("rent",rent);
		Page<Rent> page = systemService.listAllRent(new Page<Rent>(request, response), rent);
		model.addAttribute("page", page);
		model.addAttribute("currentTen",estate);
		request.getSession().setAttribute("currentTen", estate);
		return "views/modules/sys/rentList.jsp";
	}
	
	@RequestMapping(value="getRent/{id}")
	@ResponseBody
	public String getRent(@PathVariable("id") String rentID){
		Rent rent = systemService.getRentByID(rentID);
		return JsonMapper.getInstance().toJson(rent);
	}
	
	@RequestMapping(value="rentSearch",method = RequestMethod.POST)
	public String RentSearch(@Valid @ModelAttribute("rent") Rent repaire,
			BindingResult errors,HttpServletRequest request,
			HttpServletResponse response,Model model) {
		if(errors.hasErrors()){
			System.out.println(errors.toString());
		}
		List<Estate> estateList =estateService.findAll();
		model.addAttribute("tenements",estateList);
		Estate ten = null;
		for (ListIterator<Estate> iter = estateList.listIterator(); iter.hasNext(); ) {
			Estate ten1 = iter.next();
			if(ten1.getId() == Integer.parseInt(repaire.getTenement())){
				ten = ten1;
			}
		}
		model.addAttribute("currentTen",ten);
		request.getSession().setAttribute("currentTen", ten);
		repaire.setTenement(ten.getName());
		//单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(ten.getId());
		model.addAttribute("buildingNos", buildingNos);
				
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(ten.getId());
		model.addAttribute("buildings", buildings);
		if(repaire.getFloor().equals("-1")){
			repaire.setFloor(null);
		}
		if(repaire.getBuilding().equals("-1")){
			repaire.setBuilding(null);
		}
		Page<Rent> page = systemService.searchRent(new Page<Rent>(request, response), repaire);
		model.addAttribute("page", page);
		model.addAttribute("houseStatus",SystemService.houseStatus);
		return "views/modules/sys/rentList.jsp";
	}
	
	@RequestMapping(value="listRentWithTenment/{id}")
	public String getTenementRent(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		Rent rep = new Rent();
		Estate ten = estateService.findById(id);
		rep.setTenement(ten.getName());
		rep.setTenementID(id);
		Page<Rent> page = systemService.getRentByTenement(new Page<Rent>(request, response), rep);
		model.addAttribute("page", page);
		model.addAttribute("currentTen",ten);
		model.addAttribute("rent",new Rent());
		model.addAttribute("tenements",estateService.findAll());
		request.getSession().setAttribute("currentTen", ten);
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(id);
		model.addAttribute("buildingNos", buildingNos);
				
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(id);
		model.addAttribute("buildings", buildings);
		model.addAttribute("houseStatus",SystemService.houseStatus);
		return "views/modules/sys/rentList.jsp";
	}
	
	@RequestMapping(value="check/{id}")
	@ResponseBody
	public String checkRepaire(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		Repaire rep = new Repaire();
		rep.setHouseID(id);
		return JsonMapper.getInstance().toJson(systemService.getRepaireByHouseID(rep));
	}
}
