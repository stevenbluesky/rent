package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.entity.CostCode;
import com.rent.entity.Electric;

import com.rent.services.CostCodeService;
import com.rent.common.utils.NumPageUtil;

@Controller("costCodeController")
public class CostCodeController {
	@Autowired
	private CostCodeService costCodeService;

	public CostCodeService getCostCodeService() {
		return costCodeService;
	}

	public void setCostCodeService(CostCodeService costCodeService) {
		this.costCodeService = costCodeService;
	}
	
	// ��ҳ��ѯ	     
	@RequestMapping("findAllCostCodePaged.do")
	public String findAllCostCodePaged(Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С

		// ��ҳ��ѯ
		List<CostCode> roomTypes = costCodeService.findAllPaged(currpage, size);
		
		// ��ѯ����
		int total = costCodeService.getTotalCount();
		
		// ��ҳ������
		NumPageUtil page = new NumPageUtil("findAllCostCodePaged.do", total,
				currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
		page.setList(roomTypes);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		return "prhCode/costCode/costCode.jsp";
	}


	// ɾ��
	@RequestMapping("costCodeDel.do")
	public String costCodeDel(Integer[] chk, ModelMap map,
			HttpSession session) {
		// ȡ��ǰҳ
		Integer currpage = (Integer) session.getAttribute("currpage");
		
		try {
			costCodeService.delCostCode(chk);	
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		return "../findAllCostCodePaged.do?currpage=" + currpage;
		
	}
	
	@RequestMapping("toCostCodeAdd.do")
	public String toCostCodeAdd(ModelMap map) {
		return "prhCode/costCode/costCodeAdd.jsp";
	}
	// ������������
		@RequestMapping("costCodeAdd.do")
		public String costCodeAdd(CostCode costCode, ModelMap map) {
			costCodeService.addCostCode(costCode);
			return "../findAllCostCodePaged.do";
		}

		// ת���޸�
		@RequestMapping("toCostCodeEdit.do")
		public String toCostCodeEdit(Integer id, ModelMap map,
				HttpSession session) {
			CostCode costCode= costCodeService.findById(id);
			map.put("costCode", costCode);
			return "prhCode/costCode/costCodeEdit.jsp";
		}

		// �޸�
		@RequestMapping("costCodeEdit.do")
		public String costCodeEdit(CostCode	costCode, ModelMap map,
				HttpSession session) {
			Integer currpage = (Integer) session.getAttribute("currage");
			costCodeService.updateCostCode(costCode);
			return "../findAllCostCodePaged.do?currpage" + currpage;
		}
}
