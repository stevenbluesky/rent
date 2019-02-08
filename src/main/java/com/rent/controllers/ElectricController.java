package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.entity.Electric;
import com.rent.entity.Equipment;
import com.rent.entity.RepaireReason;

import com.rent.services.ElectricService;
import com.rent.common.utils.NumPageUtil;

@Controller("electricController")
public class ElectricController {

	@Autowired
	private ElectricService electricService;

	public ElectricService getElectricService() {
		return electricService;
	}

	public void setElectricService(ElectricService electricService) {
		this.electricService = electricService;
	}

	// ��ҳ��ѯ
	@RequestMapping("findAllElectricPaged.do")
	public String findAllElectricPaged(Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С

		// ��ҳ��ѯ
		List<Electric> roomTypes = electricService.findAllPaged(currpage, size);

		// ��ѯ����
		int total = electricService.getTotalCount();
	
		// ��ҳ������
		NumPageUtil page = new NumPageUtil("findAllElectricPaged.do", total,
				currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
		page.setList(roomTypes);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		return "prhCode/electric/electric.jsp";
	}


	// ɾ��
	@RequestMapping("electricDel.do")
	public String electricDel(Integer[] chk, ModelMap map,
			HttpSession session) {
		// ȡ��ǰҳ
		Integer currpage = (Integer) session.getAttribute("currpage");
		electricService.delElectric(chk);
		return "../findAllElectricPaged.do?currpage=" + currpage;
	}

	
	@RequestMapping("toElectricAdd.do")
	public String toElectricAdd( ModelMap map) {
		return "prhCode/electric/electricAdd.jsp";
	}
	// ������������
	@RequestMapping("electricAdd.do")
	public String electricAdd(Electric electric, ModelMap map) {
		electricService.addElectric(electric);
		return "../findAllElectricPaged.do";
	}

	// ת���޸�
	@RequestMapping("toElectricEdit.do")
	public String toRepaireReasonEdit(Integer id, ModelMap map,
			HttpSession session) {
		Electric electric= electricService.findById(id);
		map.put("electric", electric);
		return "prhCode/electric/electricEdit.jsp";
	}

	// �޸�
	@RequestMapping("electricEdit.do")
	public String electricEdit(Electric electric, ModelMap map,
			HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currage");
		try {
	electricService.updateElectric(electric);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		
		return "../findAllElectricPaged.do?currpage" + currpage;
	}
}
