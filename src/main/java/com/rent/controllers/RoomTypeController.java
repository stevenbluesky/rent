package com.rent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rent.entity.RoomType;
import com.rent.services.RoomTypeService;
import com.rent.common.utils.NumPageUtil;

@Controller("roomTypeController")
public class RoomTypeController {
	@Autowired
	private RoomTypeService roomTypeService;



	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}

	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}

	// 分页查询

	@RequestMapping("findAllRoomTypePaged.do")
	public String findAllRoomTypePaged(Integer currpage, ModelMap map,
									   HttpSession session, HttpServletRequest request) {
		System.out.println("分页");
		// 处理当前页
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小

		// 分页查询
		List<RoomType> roomTypes = roomTypeService.findAllPaged(currpage, size);

		// 查询总数
		int total = roomTypeService.getTotalCount();
		// 分页工具类
		NumPageUtil page = new NumPageUtil("findAllRoomTypePaged.do", total,
				currpage, size);

		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域
		page.setList(roomTypes);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);

		return "prhCode/roomType/roomType.jsp";
	}

	@RequestMapping("toRoomTypeAdd")
	public String  toRoomTypeAdd(){
		return "prhCode/roomType/roomTypeAdd.jsp";
	}

	// 新增房屋类型
	/*@RequestMapping(value = {"roomTypeAdd", ""})*/
	@RequestMapping("roomTypeAdd")
	public String roomTypeAdd(RoomType roomType, ModelMap map) {

		if (roomTypeService.findtByName(roomType.getName().trim())!=null ) {
			map.put("roomType",roomType);
			map.put("tip", "房间类型重复！");
			return "prhCode/roomType/roomTypeAdd.jsp";
		}
		roomTypeService.addRoomType(roomType);
		map.put("tip", "新增成功");
		return "../findAllRoomTypePaged.do";
	}

	// 批量删除
	@RequestMapping("roomTypeDel.do")
	public String roomTypeDel(Integer[] chk, ModelMap map, HttpSession session) {
		Integer currpage = (Integer) session.getAttribute("currpage");
		try {

			roomTypeService.delRoomType(chk);
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除");
		}


		return "../findAllRoomTypePaged.do?currpage" + currpage;
	}

	// 转到修改
	@RequestMapping("toRoomTypeEdit.do")
	public String toRoomTypeEdit(Integer id, ModelMap map, HttpSession session) {
		RoomType roomType = roomTypeService.findById(id);
		map.put("roomType", roomType);
		return "prhCode/roomType/roomTypeEdit.jsp";
	}


	// 修改
	@RequestMapping("roomTypeEdit.do")
	public String roomTypeEdit(RoomType roomType, ModelMap map, HttpSession session) {

		RoomType findtByName = roomTypeService.findtByName(roomType.getName().trim());

		if (findtByName!=null&&!findtByName.getId().equals(roomType.getId())) {
			map.put("roomType",roomType);
			map.put("tip", "房间类型重复！");
			return "prhCode/roomType/roomTypeEdit.jsp";
		}
		Integer currpage = (Integer) session.getAttribute("currpage");
		roomTypeService.updateRoomType(roomType);
		return "../findAllRoomTypePaged.do?currpage" + currpage;
	}
}
