package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.entity.SubsidyType;

import com.rent.services.SubsidyTypeService;
import com.rent.common.utils.NumPageUtil;

@Controller("subsidyTypeController")
public class SubsidyTypeController {
	
	@Autowired
	private SubsidyTypeService subsidyTypeService;
	

	public SubsidyTypeService getSubsidyTypeService() {
		return subsidyTypeService;
	}

	public void setSubsidyTypeService(SubsidyTypeService subsidyTypeService) {
		this.subsidyTypeService = subsidyTypeService;
	}
	


	// ��ҳ��ѯ
	@RequestMapping("findAllSubsidyTypePaged.do")
	public String findAllSubsidyTypePaged(Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С

		// ��ҳ��ѯ
		List<SubsidyType> subsidyType = subsidyTypeService.findAllPaged(currpage, size);

		// ��ѯ����
		int total = subsidyTypeService.getTotalCount();
		// ��ҳ������
		NumPageUtil page = new NumPageUtil("findAllSubsidyTypePaged.do", total,
				currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
		page.setList(subsidyType);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);
		return "prhCode/subsidyType/subsidyType.jsp";
	}
	
	@RequestMapping("toSubsidyTypeAdd")
	public String  toCostCodeAdd(){
		return "prhCode/subsidyType/subsidyTypeAdd.jsp";
	}
	
	// ������������
	@RequestMapping("subsidyTypeAdd.do")
	public String subsidyTypeAdd(SubsidyType subsidyType, ModelMap map) {
		subsidyTypeService.addSubsidyType(subsidyType);
		return "../findAllSubsidyTypePaged.do";
	}

	// ����ɾ��
	@RequestMapping("subsidyTypeDel.do")
	public String subsidyTypeDel(Integer[] chk, ModelMap map, HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currage");
		try {
			subsidyTypeService.delSubsidyType(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		

		return "../findAllSubsidyTypePaged.do?currpage" + currpage;
	}

	// ת���޸�		 
	@RequestMapping("toSubsidyTypeEdit.do")
	public String toSubsidyTypeEdit(Integer id, ModelMap map, HttpSession session) {
		SubsidyType subsidyType = subsidyTypeService.findById(id);
		map.put("subsidyType", subsidyType);
		return "prhCode/subsidyType/subsidyTypeEdit.jsp";
	}
	
	
	// �޸�		 
	@RequestMapping("subsidyTypeEdit.do")
	public String subsidyTypeEdit(SubsidyType subsidyType, ModelMap map, HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currpage");
		subsidyTypeService.updateSubsidyType(subsidyType);
		return "../findAllSubsidyTypePaged.do?currpage" + currpage;
	}
}
