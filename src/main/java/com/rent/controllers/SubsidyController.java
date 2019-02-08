package com.rent.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.dao.SubsidyWithTypeMapper;

import com.rent.entity.Building;
import com.rent.entity.Estate;
import com.rent.entity.Subsidy;
import com.rent.entity.SubsidyPercent;
import com.rent.entity.SubsidyType;
import com.rent.entity.SubsidyWithType;

import com.rent.services.BuildingNoService;
import com.rent.services.EstateService;
import com.rent.services.RoomTypeService;
import com.rent.services.SubsidyService;
import com.rent.services.SubsidyTypeService;
import com.rent.services.SubsidyWithTypeService;
import com.rent.common.utils.NumPageUtil;

@Controller("subsidyController")
public class SubsidyController {
	@Autowired
	private SubsidyService subsidyService;
	@Autowired
	private SubsidyTypeService subsidyTypeService;
	@Autowired
	private SubsidyWithTypeService subsidyWithTypeService;

	@Autowired
	private EstateService estateService;

	public SubsidyWithTypeService getSubsidyWithTypeService() {
		return subsidyWithTypeService;
	}

	public void setSubsidyWithTypeService(
			SubsidyWithTypeService subsidyWithTypeService) {
		this.subsidyWithTypeService = subsidyWithTypeService;
	}

	public SubsidyTypeService getSubsidyTypeService() {
		return subsidyTypeService;
	}

	public void setSubsidyTypeService(SubsidyTypeService subsidyTypeService) {
		this.subsidyTypeService = subsidyTypeService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public SubsidyService getSubsidyService() {
		return subsidyService;
	}

	public void setSubsidyService(SubsidyService subsidyService) {
		this.subsidyService = subsidyService;
	}

	// 分页查询
	@RequestMapping("findSubsidyByEstatePaged.do")
	public String findSubsidyByEstatePaged(Integer estateId, Integer currpage,
										   ModelMap map, HttpSession session, HttpServletRequest request) {
		// 处理当前页
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小
		// 查询物业集合
		List<Estate> estates = estateService.findAll();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		// 分页查询
		List<Subsidy> subsidys = subsidyService.findByEstatePaged(estateId,
				currpage, size);
		// 查询总数
		int total =0;
		if (estateId!=null) {
			total=subsidyService.getCountByEstate(estateId);
		}

		// 补贴人类型
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);
		// 分页工具类
		NumPageUtil page = new NumPageUtil(
				"findSubsidyByEstatePaged.do?estateId=" + estateId, total,
				currpage, size);
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域
		page.setList(subsidys);

		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);
		map.put("estateId", estateId);
		map.put("estates", estates);
		return "prhCode/subsidy/subsidy.jsp";
	}

	@RequestMapping("toSubsidyAdd.do")
	public String toSubsidyAdd(Integer estateId, ModelMap map) {

		// 补贴人类型
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);
		map.put("estateId", estateId);
		return "prhCode/subsidy/subsidyAdd.jsp";
	}

	// 新增
	@RequestMapping("subsidyAdd.do")
	public String subsidyAdd(Subsidy subsidy, Integer estateId, String begin,
							 String end, Double[] ins, Double[] outs, ModelMap map)
			throws ParseException {
		// 处理时间

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (begin!=null&&begin.length()!=0) {
			Date beginDate = df.parse(begin);
			subsidy.setBeginDate(beginDate);
		}
		if (end!=null&&end.length()!=0) {
			Date endDate = df.parse(end);
			subsidy.setEndDate(endDate);
		}

		subsidy.setEstateId(estateId);

		// 新增补贴
		subsidyService.addSubsidy(subsidy);
		Integer sId = subsidyService.getCurrId();
		// 处理百分比集合
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll(); // 所有类型
		// 关联的集合（用于批量新增）
		List<SubsidyWithType> subsidyWithTypes = new ArrayList<SubsidyWithType>();
		for (int i = 0; i < subsidyTypes.size(); i++) {
			SubsidyWithType in = new SubsidyWithType(null, sId, subsidyTypes
					.get(i).getId(), 1, ins[i] != null ? ins[i] / 100.0 : null);
			SubsidyWithType out = new SubsidyWithType(null, sId, subsidyTypes
					.get(i).getId(), 2, outs[i] != null ? outs[i]/ 100.0: null);

			if (in.getPercent()!=null) {
				subsidyWithTypes.add(in);
			}
			if (out.getPercent()!=null) {
				subsidyWithTypes.add(out);
			}

		}
		// 批量新增关联表数据
		subsidyWithTypeService.addAuto(subsidyWithTypes);

		return "../findSubsidyByEstatePaged.do";
	}

	@RequestMapping("subsidyEdit.do")
	public String subsidyEdit(Subsidy subsidy, Integer sId, String begin,
							  String end, Double[] ins, Double[] outs, ModelMap map)
			throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (begin!=null&&begin.length()!=0) {
			Date beginDate = df.parse(begin);
			subsidy.setBeginDate(beginDate);
		}
		if (end!=null&&end.length()!=0) {
			Date endDate = df.parse(end);
			subsidy.setEndDate(endDate);
		}

		subsidy.setId(sId); // 设置id
		// 修改补贴
		subsidyService.updateSubsidy(subsidy);
		// 获取当前新增的id

		// 处理百分比集合
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll(); // 所有类型
		// 关联的集合（用于批量新增）
		List<SubsidyWithType> subsidyWithTypes = new ArrayList<SubsidyWithType>();
		for (int i = 0; i < subsidyTypes.size(); i++) {
			SubsidyWithType in = new SubsidyWithType(null, sId, subsidyTypes
					.get(i).getId(), 1, ins[i] != null ? ins[i] / 100.0 : null);
			SubsidyWithType out = new SubsidyWithType(null, sId, subsidyTypes
					.get(i).getId(), 2, outs[i] != null ? outs[i]/ 100.0 : null);

			if (in.getPercent()!=null) {
				subsidyWithTypes.add(in);
			}
			if (out.getPercent()!=null) {
				subsidyWithTypes.add(out);
			}
		}

		// 批量删除相关关联表数据
		int delete = subsidyWithTypeService.deleteBySubsidy(sId);
		System.out.println("删除"+delete);
		System.out.println();
		// 批量新增关联表数据
		subsidyWithTypeService.addAuto(subsidyWithTypes);
		return "../findSubsidyByEstatePaged.do";
	}

	// 转到修改页面
	@RequestMapping("toSubsidyEdit.do")
	public String toSubsidyEdit(Integer id, ModelMap map) {

		Subsidy subsidy = subsidyService.findById(id);


		// 楼号集合
		map.put("estateId", subsidy.getEstateId());
		map.put("subsidy", subsidy);
		// 补贴人类型
		List<SubsidyType> subsidyTypes = subsidyTypeService.findAll();
		map.put("subsidyTypes", subsidyTypes);

		Estate estate = estateService.findById(subsidy.getEstateId());
		map.put("estate", estate);

		return "prhCode/subsidy/subsidyEdit.jsp";
	}

	@RequestMapping("subsidyDel.do")
	public String delBuilding(Integer[] chk, ModelMap map, HttpSession session) {
		// 当前页
		Integer currpage = (Integer) session.getAttribute("currpage");
		// 物业id
		Integer estateId = null;
		if (chk != null && chk.length != 0) {
			estateId = subsidyService.findById(chk[0]).getEstateId();

			try {

				subsidyWithTypeService.deleteBySubsidy(chk);
				subsidyService.delSubsidy(chk);

			} catch (Exception e) {
				e.printStackTrace();
				map.put("tip", "该条数据已被关联，请勿删除！");
			}

		}

		return "../findSubsidyByEstatePaged.do?currpage=" + currpage
				+ "&estateId=" + estateId;
	}

}
