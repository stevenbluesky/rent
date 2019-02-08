package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.entity.Electric;
import com.rent.entity.RentPayWay;

import com.rent.services.RentPayWayService;
import com.rent.common.utils.NumPageUtil;

@Controller("rentPayWayController")
public class RentPayWayController {
	@Autowired
	private RentPayWayService rentPayWayService ;
	public RentPayWayService getRentPayWayService() {
		return rentPayWayService;
	}
	public void setRentPayWayService(RentPayWayService rentPayWayService) {
		this.rentPayWayService = rentPayWayService;
	}
	    // ��ҳ��ѯ
		@RequestMapping("findAllRentPayWayPaged.do")
		public String findAllRentPayWayPaged(Integer currpage, ModelMap map,
				HttpSession session, HttpServletRequest request) {
			// ����ǰҳ
			if (currpage == null || currpage <= 0) {
				currpage = 1;
			}
			Integer size = 10; // ҳ��С

			// ��ҳ��ѯ
			List<RentPayWay> roomTypes = rentPayWayService.findAllPaged(currpage, size);

			// ��ѯ����
			int total = rentPayWayService.getTotalCount();
			
			// ��ҳ������
			NumPageUtil page = new NumPageUtil("findAllRentPayWayPaged.do", total,
					currpage, size);
			page.setBothnum(3);
			String numpage = page.showNumPage();
			currpage = page.getCurrpage();

			// ��������
			page.setList(roomTypes);
			session.setAttribute("currpage", currpage);
			request.setAttribute("numpage", numpage);
			map.put("page", page);
			return "prhCode/rentPayWay/rentPayWay.jsp";
		}
		//ɾ��
		@RequestMapping("rentPayWayDel.do")
		public String rentPayWayDel(Integer[] chk,ModelMap map,HttpSession session){
			//ȡ��ǰҳ
			Integer currpage= (Integer)session.getAttribute("currpage");
			
			try {
				rentPayWayService.delRentPayWay(chk);	
			} catch (Exception e) {
				map.put("tip", "该条数据已被关联，请勿删除！");
			}
			return "../findAllRentPayWayPaged.do?currpage="+currpage;
		}

		@RequestMapping("toRentPayWayAdd")
		public String  toCostCodeAdd(){
			return "prhCode/rentPayWay/rentPayWayAdd.jsp";
		}
		
		//������������
		@RequestMapping("rentPayWayAdd.do")
		public String rentPayWayAdd(RentPayWay rentPayWay, ModelMap map) {
			rentPayWayService.addRentPayWay(rentPayWay);
			return "../findAllRentPayWayPaged.do";
		}
		//ת���޸�
		@RequestMapping("toRentPayWayEdit.do")
		public String toRentPayWayEdit(Integer id, ModelMap map,
				HttpSession session) {
			RentPayWay rentPayWay= rentPayWayService.findById(id);
			map.put("rentPayWay", rentPayWay);
			return "prhCode/rentPayWay/rentPayWayEdit.jsp";
		}
		// �޸�
		@RequestMapping("rentPayWayEdit.do")
		public String rentPayWayEdit(RentPayWay rentPayWay, ModelMap map,
				HttpSession session) {
			Integer currpage = (Integer) session.getAttribute("currage");
			rentPayWayService.updateRentPayWay(rentPayWay);
			return "../findAllRentPayWayPaged.do?currpage" + currpage;
		}

}
