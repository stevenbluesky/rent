package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.services.CostCodeService;
import com.rent.services.PaymentCodeService;
import com.rent.common.utils.NumPageUtil;

import com.rent.dao.PaymentCodeMapper;
import com.rent.entity.CostCode;
import com.rent.entity.Electric;
import com.rent.entity.PaymentCode;

@Controller("paymentCodeController")
public class PaymentCodeController {
	@Autowired
	private PaymentCodeService paymentCodeService;

	public PaymentCodeService getPaymentCodeService() {
		return paymentCodeService;
	}

	public void setPaymentCodeService(PaymentCodeService paymentCodeService) {
		this.paymentCodeService = paymentCodeService;
	}

	// ��ҳ��ѯ
	@RequestMapping("findAllPaymentCodePaged.do")
	public String findAllPaymentCodePaged(Integer currpage, ModelMap map,
			HttpSession session, HttpServletRequest request) {
		// ����ǰҳ
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // ҳ��С

		// ��ҳ��ѯ
		List<PaymentCode> roomTypes = paymentCodeService.findAllPaged(currpage,
				size);

		// ��ѯ����
		int total = paymentCodeService.getTotalCount();
		
		// ��ҳ������
		NumPageUtil page = new NumPageUtil("findAllPaymentCodePaged.do",
				total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// ��������
		page.setList(roomTypes);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		return "prhCode/paymentCode/paymentCode.jsp";
	}

	// ɾ��
	@RequestMapping("paymentCodeDel.do")
	public String paymentCodeDel(Integer[] chk, ModelMap map,
			HttpSession session) {
		// ȡ��ǰҳ
		Integer currpage = (Integer) session.getAttribute("currpage");
		
		try {
			paymentCodeService.delPaymentCode(chk);	
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		return "findAllPaymentCodePaged.do?currpage=" + currpage;
	}
	
	@RequestMapping("toPaymentCodeAdd.do")
	public String  toCostCodeAdd(){
		System.out.println("jinren");
		return "prhCode/paymentCode/paymentCodeAdd.jsp";
	}
	// ������������
	@RequestMapping("paymentCodeAdd.do")
	public String costCodeAdd(PaymentCode paymentCode, ModelMap map) {
		paymentCodeService.addPaymentCode(paymentCode);
		return "findAllPaymentCodePaged.do";
	}

	// ת���޸�
	@RequestMapping("toPaymentCodeEdit.do")
	public String toCostCodeEdit(Integer id, ModelMap map,
			HttpSession session) {
		PaymentCode paymentCode= paymentCodeService.findById(id);
		map.put("paymentCode", paymentCode);
		return "prhCode/paymentCode/paymentCodeEdit.jsp";
	}

	// �޸�			 
	@RequestMapping("paymentCodeEdit.do")
	public String costCodeEdit(PaymentCode paymentCode, ModelMap map,
			HttpSession session) {
		System.out.println("test2");
		Integer currpage = (Integer) session.getAttribute("currage");
		paymentCodeService.updatePaymentCode(paymentCode);
		
		return "findAllPaymentCodePaged.do?currpage" + currpage;
	}
}
