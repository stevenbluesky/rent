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

import com.rent.services.EquipmentService;
import com.rent.common.utils.NumPageUtil;

@Controller("equipmentController")
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	// ��ҳ��ѯ
	@RequestMapping("findAllEquipmentPaged.do")
	public String findAllEquipmentPaged(Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С

		// ��ҳ��ѯ
		List<Equipment> roomTypes = equipmentService.findAllPaged(currpage,
				size);

		// ��ѯ����
		int total = equipmentService.getTotalCount();
		
		// ��ҳ������
		NumPageUtil page = new NumPageUtil("findAllEquipmentPaged.do",
				total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
		page.setList(roomTypes);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		return "prhCode/equipment/equipment.jsp";
	}

	// ɾ��
	@RequestMapping("equipmentDel.do")
	public String equipmentDel(Integer[] chk, ModelMap map, HttpSession session) {
		// ȡ��ǰҳ
		Integer currpage = (Integer) session.getAttribute("currpage");
		equipmentService.delEquipment(chk);
		return "../findAllEquipmentPaged.do?currpage=" + currpage;
	}

	// ������������
		@RequestMapping("toEquipmentAdd.do")
		public String toEquipmentAdd() {
			
			return "prhCode/equipment/equipmentAdd.jsp";
		}
	
	// ������������
	@RequestMapping("equipmentAdd.do")
	public String equipmentAdd(Equipment equipment, ModelMap map) {
		equipmentService.addEquipment(equipment);
		return "../findAllEquipmentPaged.do";
	}

	// ת���޸�
	@RequestMapping("toEquipmentEdit.do")
	public String toEquipmentEdit(Integer id, ModelMap map,
			HttpSession session) {
		Equipment equipment = equipmentService.findById(id);
		map.put("equipment", equipment);
		return "prhCode/equipment/equipmentEdit.jsp";
	}

	// �޸�
	@RequestMapping("equipmentEdit.do")
	public String equipmentEdit(Equipment equipment, ModelMap map,
			HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currage");
		try {
	equipmentService.updateEquipment(equipment);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		
		return "../findAllEquipmentPaged.do?currpage" + currpage;
	}
	
	

}
