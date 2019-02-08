package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.entity.RoomFeature;
import com.rent.entity.RoomType;

import com.rent.services.RoomFeatureService;
import com.rent.common.utils.NumPageUtil;

@Controller("roomFeatureController")
public class RoomFeatureController {

	@Autowired
	private RoomFeatureService roomFeatureService;

	public RoomFeatureService getRoomFeatureService() {
		return roomFeatureService;
	}

	public void setRoomFeatureService(RoomFeatureService roomFeatureService) {
		this.roomFeatureService = roomFeatureService;
	}

	// 分页查询
	@RequestMapping("findAllRoomFeaturePaged.do")
	public String findAllRoomFeaturePaged(Integer currpage, ModelMap map,
										  HttpSession session, HttpServletRequest request) {
		// 处理当前页
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小

		// 分页查询
		List<RoomFeature> roomFeatures = roomFeatureService.findAllPaged(
				currpage, size);

		// 查询总数
		int total = roomFeatureService.getTotalCount();
		// 分页工具类
		NumPageUtil page = new NumPageUtil("findAllRoomFeaturePaged.do",
				total, currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域
		page.setList(roomFeatures);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);

		return "prhCode/roomFeature/roomFeature.jsp";
	}

	@RequestMapping("toRoomFeatureAdd")
	public String  toRoomFeatureAdd(){
		return "prhCode/roomFeature/roomFeatureAdd.jsp";
	}

	// 新增房屋类型
	@RequestMapping("roomFeatureAdd.do")
	public String roomFeatureAdd(RoomFeature roomFeature, ModelMap map) {
		roomFeatureService.addRoomFeature(roomFeature);
		return "../findAllRoomFeaturePaged.do";
	}

	// 批量删除
	@RequestMapping("roomFeatureDel.do")
	public String roomFeatureDel(Integer[] chk, ModelMap map,
								 HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currage");

		try {
			roomFeatureService.delRoomFeature(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		return "../findAllRoomFeaturePaged.do?currpage" + currpage;
	}

	// 转到修改
	@RequestMapping("toRoomFeatureEdit.do")
	public String toRoomFeatureEdit(Integer id, ModelMap map, HttpSession session) {
		RoomFeature roomFeature = roomFeatureService.findById(id);
		map.put("roomFeature", roomFeature);
		return "prhCode/roomFeature/roomFeatureEdit.jsp";
	}


	// 修改
	@RequestMapping("roomFeatureEdit.do")
	public String roomFeatureEdit(RoomFeature roomFeature, ModelMap map, HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currage");
		roomFeatureService.updateRoomFeature(roomFeature);
		return "../findAllRoomFeaturePaged.do?currpage" + currpage;
	}
}
