package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.entity.RepaireReason;
import com.rent.entity.RoomType;

import com.rent.services.RepaireReasonService;
import com.rent.common.utils.NumPageUtil;

@Controller("repaireReasonController")
public class RepaireReasonController {

	@Autowired
	private RepaireReasonService repaireReasonService;

	public RepaireReasonService getRepaireReasonService() {
		return repaireReasonService;
	}

	public void setRepaireReasonService(
			RepaireReasonService repaireReasonService) {
		this.repaireReasonService = repaireReasonService;
	}

	// ��ҳ��ѯ
	@RequestMapping("findAllRepairReasonPaged.do")
	public String findAllRepairReasonPaged(Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С

		// ��ҳ��ѯ
		List<RepaireReason> roomTypes = repaireReasonService.findAllPaged(
				currpage, size);

		// ��ѯ����
		int total = repaireReasonService.getTotalCount();

		
		// ��ҳ������
		NumPageUtil page = new NumPageUtil("findAllRepairReasonPaged.do",
				total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
		page.setList(roomTypes);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);

		return "prhCode/repaireReason/repaireReason.jsp";
	}

	// ɾ��
	@RequestMapping("repaireReasonDel.do")
	public String repaireReasonDel(Integer[] chk, ModelMap map,
			HttpSession session) {
		// ȡ��ǰҳ
		Integer currpage = (Integer) session.getAttribute("currpage");
		try {
			repaireReasonService.delRepaireReason(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		
		return "../findAllRepairReasonPaged.do?currpage=" + currpage;
	}
	
	@RequestMapping("toRepaireReasonAdd")
	public String  toRepaireReasonAdd(){
		return "prhCode/repaireReason/repaireReasonAdd.jsp";
	}

	// ������������
	@RequestMapping("repaireReasonAdd.do")
	public String repaireReasonAdd(RepaireReason repaireReason, ModelMap map) {
		repaireReasonService.addRepaireReason(repaireReason);
		return "../findAllRepairReasonPaged.do";
	}

	// ת���޸�
	@RequestMapping("toRepaireReasonEdit.do")
	public String toRepaireReasonEdit(Integer id, ModelMap map,
			HttpSession session) {
		RepaireReason repaireReason = repaireReasonService.findById(id);
		map.put("repaireReason", repaireReason);
		return "prhCode/repaireReason/repaireReasonEdit.jsp";
	}

	// �޸�
	@RequestMapping("repaireReasonEdit.do")
	public String repaireReasonEdit(RepaireReason repaireReason, ModelMap map,
			HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currage");
		repaireReasonService.updateRepaireReason(repaireReason);
		return "../findAllRepairReasonPaged.do?currpage" + currpage;
	}

}
