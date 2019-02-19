package com.rent.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.common.config.Global;
import com.rent.common.mapper.JsonMapper;
import com.rent.common.persistence.Page;
import com.rent.common.utils.DateUtils;
import com.rent.common.utils.MyRentBiz;
import com.rent.common.utils.StringUtils;
import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.Users;
import com.rent.modules.sys.entity.Payment;
import com.rent.modules.sys.entity.Rent;
import com.rent.modules.sys.entity.Repaire;
import com.rent.modules.sys.entity.Tenement;
import com.rent.modules.sys.service.SystemService;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;

@Controller
@RequestMapping(value = "${adminPath}/sys/repaire")
public class RepaireController {	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private EstateService estateService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private BuildingNoService buildingNoService;
	
	private Estate defaultSetting(HttpServletRequest request,Model model,String estateID) {
		Estate estate = null;
		List<Estate> estates = estateService.findAll();
		model.addAttribute("tenements",estates);
		
		if(estateID == null) {
			estate = (Estate)request.getSession().getAttribute("currentTen");
			if(estate == null) {
				estate = MyRentBiz.getEstates(estates);
			}
		}else{
			for (ListIterator<Estate> iter = estates.listIterator(); iter.hasNext(); ) {
				Estate ten1 = iter.next();
				if(ten1.getName().equals(estateID) || ten1.getId() == Integer.parseInt(estateID)){
					estate = ten1;
					break;
				}
			}
		}
		if(estate==null){
			return null;
		}
		//单元集合
		List<BuildingNo> buildingNos = buildingNoService.findByEstate(estate.getId());
		model.addAttribute("buildingNos", buildingNos);
				
		//楼号集合
		List<Building> buildings=buildingService.findAllByEstate(estate.getId());
		model.addAttribute("buildings", buildings);
		model.addAttribute("currentTen",estate);
		request.getSession().setAttribute("currentTen",estate);
		return estate;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Repaire repaire,HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("tenements",estateService.findAll());
        model.addAttribute("houseStatus",SystemService.houseStatus);
        Estate estate = defaultSetting(request,model,null);
		if(repaire != null) {
			repaire = new Repaire();
		}
		repaire.setTenement(estate.getName());
		Page<Repaire> page = systemService.findRepaire(new Page<Repaire>(request, response), repaire);
        model.addAttribute("page", page);
		return "views/modules/sys/repaireList.jsp";
	}
	
	@RequestMapping(value = {"manage", ""})
	public String mangage(Repaire repaire,HttpServletRequest request, HttpServletResponse response, Model model) {
        Estate estate = defaultSetting(request,model,null);
        model.addAttribute("statusList", SystemService.statusList);
        if(repaire == null) {
        	repaire = new Repaire();
        }
        repaire.setTenement(estate!=null?estate.getName():"请先创建物业");
        Page<Repaire> page = systemService.findRepaire(new Page<Repaire>(request, response), repaire);
        model.addAttribute("thirdUsers",systemService.getThirdUsers(null));
        model.addAttribute("page", page);
		return "views/modules/sys/repaireMan.jsp";
	}
	
	@RequestMapping(value="check")
	public String check(Repaire repaire,HttpServletRequest request, HttpServletResponse response,Model model){
		Estate estate = defaultSetting(request,model,null);
        model.addAttribute("statusList", SystemService.statusList);
        if(repaire == null) {
        	repaire = new Repaire();
        }
        repaire.setTenement(estate!=null?estate.getName():"请先创建物业");
		Page<Repaire> page = systemService.findRepaire(new Page<Repaire>(request, response), repaire);
		model.addAttribute("page", page);
		return "views/modules/sys/checkRepaire.jsp";
	}
	
	@RequestMapping(value="submit")
	public String submit(Repaire repaire,HttpServletRequest request, HttpServletResponse response,Model model){
		Estate estate = defaultSetting(request,model,null);
        model.addAttribute("statusList", SystemService.statusList);
        if(repaire == null) {
        	repaire = new Repaire();
        }
        repaire.setTenement(estate!=null?estate.getName():"请先创建物业");
        Users user = (Users)request.getSession().getAttribute(Global.USER);
        if(user != null && user.getId().intValue() != 1)
        	repaire.setThirdCom(user.getId().toString());
		Page<Repaire> page = systemService.findSubmit(new Page<Repaire>(request, response), repaire);
		model.addAttribute("page", page);
		return "views/modules/sys/repaireSubmit.jsp";
	}
	
	@RequestMapping(value="search",method = RequestMethod.POST)
	public String search(@Valid @ModelAttribute("repaire") Repaire repaire,
			BindingResult errors,HttpServletRequest request,
			HttpServletResponse response,Model model){
		if(errors.hasErrors()){
			System.out.println(errors.toString());
		}
		String id = repaire.getId();
		String startDate = request.getParameter("startTime");
		String endDate = request.getParameter("endTime");
		if(!StringUtils.isBlank(startDate)) {
			repaire.setStartTime(DateUtils.parseDate(startDate));
		}
		if(!StringUtils.isBlank(endDate)){
			repaire.setEndTime(DateUtils.parseDate(endDate));
		}
		if(!StringUtils.isBlank(request.getParameter("repairerTime"))){
			repaire.setRepairerTime(DateUtils.parseDate(request.getParameter("repairerTime")));
		}
		repaire.setId(null);
		if(repaire.getFloor().equals("-1")){
			repaire.setFloor(null);
		}
		if(repaire.getBuilding().equals("-1")){
			repaire.setBuilding(null);
		}
		if(repaire.getStatus() != null && 
				repaire.getStatus().equals(SystemService.statusList.get(0).getName())){
			repaire.setStatus(null);
		}
		
		Estate ten = defaultSetting(request,model,repaire.getTenement());
		repaire.setTenement(ten.getName());
		if(id.toString().indexOf("submit") >= 0){
			Users user = (Users)request.getSession().getAttribute(Global.USER);
			if(user != null && user.getId().intValue() != 1) {
				repaire.setThirdCom(user.getId().toString());
			}
		}
		Page<Repaire> page = systemService.searchRepaire(new Page<Repaire>(request, response), repaire);
		model.addAttribute("page", page);
		model.addAttribute("statusList", SystemService.statusList);
		model.addAttribute("houseStatus",SystemService.houseStatus);
		// model.addAttribute("repaire",new Repaire());
		if(id == null) {
			return "views/modules/sys/checkRepaire.jsp";
		}else if(id.toString().indexOf("submit") >= 0) {
			return "views/modules/sys/repaireSubmit.jsp";
		}else if(id.toString().indexOf("listRent") >= 0) {
			return "views/modules/sys/rentList.jsp";
		}else if(id.toString().indexOf("listRepaire") >= 0) {
			return "views/modules/sys/repaireList.jsp";
		}else if(id.toString().indexOf("manage") >= 0) {
			model.addAttribute("thirdUsers",systemService.getThirdUsers(null));
			return "views/modules/sys/repaireMan.jsp";
		}else{
			return "views/modules/sys/checkRepaire.jsp";
		}
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestBody String name) {
		name = URLDecoder.decode(name);
		Repaire rep = JsonMapper.getInstance().fromJson(name, Repaire.class);
		systemService.save(rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="update",method = RequestMethod.POST)
	@ResponseBody
	public String update(@RequestBody String repaire){
		repaire = URLDecoder.decode(repaire);
		Repaire rep = JsonMapper.getInstance().fromJson(repaire, Repaire.class);
		systemService.updateRepaire(rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="listRepairesWithTenment/{id}")
	public String getTenementRepaireList(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		Repaire rep = new Repaire();
		Estate ten = estateService.findById(id);
		rep.setTenement(ten.getName());
		Page<Repaire> page = systemService.searchRepaire(new Page<Repaire>(request, response), rep);
		Estate estate = defaultSetting(request,model,id+"");
		model.addAttribute("page", page);
		// model.addAttribute("currentTen",estate);
		model.addAttribute("repaire",new Repaire());
		return "views/modules/sys/repaireList.jsp";
	}
	
	@RequestMapping(value="listSubmitRepairesWithTen/{id}")
	public String listSubmitRepairesWithTen(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		Repaire rep = new Repaire();
		Estate ten = defaultSetting(request,model,id+"");
		rep.setTenement(ten.getName());
		Page<Repaire> page = systemService.searchRepaire(new Page<Repaire>(request, response), rep);
		model.addAttribute("page", page);
		model.addAttribute("repaire",new Repaire());
		return "views/modules/sys/repaireSubmit.jsp";
	}
	
	@RequestMapping(value="listWithTenment/{id}")
	public String getTenementRepaires(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		Repaire rep = new Repaire();
		Estate ten = defaultSetting(request,model,id+"");
		rep.setTenement(ten.getName());
		Page<Repaire> page = systemService.searchRepaire(new Page<Repaire>(request, response), rep);
		model.addAttribute("page", page);
		// model.addAttribute("currentTen",ten);
		model.addAttribute("repaire",new Repaire());
		// model.addAttribute("tenements",estateService.findAll());
		return "views/modules/sys/checkRepaire.jsp";
	}
	
	@RequestMapping(value="manListWithTenment/{id}")
	public String getTenementRepairesMan(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		Repaire rep = new Repaire();
		Estate ten = defaultSetting(request,model,id+"");
		rep.setTenement(ten.getName());
		Page<Repaire> page = systemService.searchRepaire(new Page<Repaire>(request, response), rep);
		model.addAttribute("page", page);
		// model.addAttribute("currentTen",ten);
		model.addAttribute("repaire",new Repaire());
		// model.addAttribute("tenements",estateService.findAll());
		model.addAttribute("thirdUsers",systemService.getThirdUsers(null));
		return "views/modules/sys/repaireMan.jsp";
	}
	
	@RequestMapping(value="getRepaire/{id}")
	@ResponseBody
	public String getRepaire(@PathVariable("id") String id) {
		Repaire rep = systemService.getRepaireByID(id);
		Payment pay = new Payment();
		pay.setRepaireID(id);
		rep.setPayments(systemService.getRepairePayments(pay));
		rep.setHouseStatusList(SystemService.houseStatus);
		
		return JsonMapper.getInstance().toJson(rep);
	}
	
	@RequestMapping(value="delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		Repaire rep = systemService.getRepaireByID(id);
		int houseId = rep.getHouseID();
		systemService.deleteRepaire(id,rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="repaireCheck",method = RequestMethod.POST)
	@ResponseBody
	public String repaireCheck(@RequestBody String name) {
		name = URLDecoder.decode(name);
		Repaire rep = JsonMapper.getInstance().fromJson(name, Repaire.class);
		systemService.repaireCheck(rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="approver",method = RequestMethod.POST)
	@ResponseBody
	public String addApprover(@RequestBody String name) {
		name = URLDecoder.decode(name);
		Repaire rep = JsonMapper.getInstance().fromJson(name, Repaire.class);
		systemService.addApprover(rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="submitThird",method = RequestMethod.POST)
	@ResponseBody
	public String submitThird(@RequestBody String name) {
		name = URLDecoder.decode(name);
		Repaire rep = JsonMapper.getInstance().fromJson(name, Repaire.class);
		systemService.submitThird(rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="validate",method = RequestMethod.POST)
	@ResponseBody
	public String validateUpdate(@RequestBody String name) {
		name = URLDecoder.decode(name);
		Repaire rep = JsonMapper.getInstance().fromJson(name, Repaire.class);
		systemService.validateUpdate(rep);
		return JsonMapper.getInstance().toJson("success");
	}
	
	@RequestMapping(value="getThird",method=RequestMethod.GET)
	@ResponseBody
	public String getThirdCom() {
		return JsonMapper.getInstance().toJson(systemService.getThirdUsers(null));
	}
}
