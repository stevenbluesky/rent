package com.rent.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rent.common.utils.NumPageUtil;
import com.rent.condition.HouseRmdevCondition;
import com.rent.condition.RmdevCondition;
import com.rent.condition.TempCondition;
import com.rent.entity.BuildingNo;
import com.rent.entity.Equipment;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhRmdev;
import com.rent.entity.RmdevTemp;
import com.rent.entity.RoomType;
import com.rent.services.BuildingNoService;
import com.rent.services.EquipmentService;
import com.rent.services.EstateService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhRmdevService;
import com.rent.services.RmdevTempService;
import com.rent.services.RoomTypeService;

@Controller("prhRmdevController")
public class PrhRmdevController {

	@Autowired
	private PrhRmdevService prhRmdevService;
	@Autowired
	private RmdevTempService rmdevTempService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EstateService estateService;
	@Autowired
	private BuildingNoService buildingNoService;
	@Autowired
	private PrHouseService prHouseService;

	@RequestMapping("prhRmdev")
	public String findPrhRdev(Integer houseid, String sta, Integer currpage, ModelMap map, HttpSession session,
			HttpServletRequest request) {
		// 没有数据

		// 处理当前页
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小
		// 查询物业集合

		// 分页查询
		RmdevCondition condition = new RmdevCondition();
		condition.setHouseid(houseid);
		condition.setSta("1");
		List<PrhRmdev> rmdev = prhRmdevService.findByConditionPaged(condition, currpage, size);
		// 查询总数
		int total = prhRmdevService.findById(houseid).size();
		// 分页工具类
		// 分页工具类

		NumPageUtil page = new NumPageUtil("prhRmdev?houseid=" + houseid + "&sta=" + sta, total,

				currpage, size);
		System.out.println(page.getUrl());
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();
		// 存作用域
		page.setList(rmdev);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);
		map.put("houseid", houseid);

		return "prh/prFileManager/prhRmdev.jsp";
	}

	@RequestMapping("prhRmdevCenter")
	public String Center(Integer flag, Integer houseid, ModelMap map, HttpSession session, HttpServletRequest request) {
		System.out.println("houseid" + houseid);
		if (flag == 1) {
			// 查询目前最大编号
			Integer id = prhRmdevService.findMaxID();
			List<Equipment> equipment = equipmentService.findAllEqu();

			map.put("equipment", equipment);
			id=(id!=null?id:1);
			map.put("id", id + 1);
			map.put("houseid", houseid);

			return "prh/prFileManager/addRmdev.jsp";
		}
		if (flag == 2) {
			String tip = session.getAttribute("message") != null ? (String) session.getAttribute("message")
					: new String();
			map.put("tip", tip);
			session.removeAttribute("message");
			session.removeAttribute("rmdevTemps");
			session.removeAttribute("roomname");
		}

		return "redirect:/prHouseFileByCondition";

	}

	@RequestMapping("checkRoomtype")
	public String CheckAndJump(Integer typeId, ModelMap map, HttpSession session, HttpServletRequest request) {

		RoomType roomType = roomTypeService.findById(typeId);
		String s2 = roomType.getName();
		System.out.println(s2);

		// 查询最大id 新增一条类型
		Integer id = roomTypeService.findMaxId();
		RoomType t = new RoomType();
		t.setId(id);
		t.setName(s2);

		// 跳转到页面TempList
		// 存 房间名
		// 取 所有设备表数据
		List<Equipment> equ = equipmentService.findAllEqu();

		map.put("equ", equ);
		map.put("roomname", s2);
		System.out.println("asdasdassd:" + s2);
		return "prh/prFileManager/tempList.jsp";

	}

	@RequestMapping("checkTempList.do")
	public void AddRmdev(String roomname, int[] chk, ModelMap map, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<RmdevTemp> temps = new ArrayList<>();

		// 循环遍历
		for (int i : chk) {
			Integer num = Integer.parseInt(request.getParameter("num" + i));
			RmdevTemp temp = new RmdevTemp();

			String cla = request.getParameter("cla" + i);
			// 根据设备id 查询 设备类型
		
			System.out.println(i);
			temp.setCla(cla);
			BigDecimal nums = new BigDecimal(num);
			temp.setNum(nums);
			BigDecimal beqid = new BigDecimal(i);
			temp.setEqid(beqid);
			temps.add(temp);
		}
		// 取到了id
		// 根据name 查到 房间类型id
		RoomType room = roomTypeService.findtByName(roomname);
		Integer id = room.getId();
		for (RmdevTemp rmdevTemp : temps) {
			rmdevTemp.setRoomtype(id.toString().trim());
		}
		// 存到模板集合中
		System.out.println("id" + id);
		map.put("rmdevTemps", temps);
		for (RmdevTemp rmdevTemp : temps) {
			System.out.println(rmdevTemp.getRoomtype());
			System.out.println(rmdevTemp.getEqid());
			System.out.println(rmdevTemp.getNum());
		}
		session.setAttribute("rmdevTemps", temps);
		session.setAttribute("roomname", roomname);
		map.put("roomname", roomname);
		PrintWriter out = response.getWriter();

		out.flush();
		out.println("<script>");
		out.println("parent.layer.closeAll();");
		out.println("</script>");

	}

	@RequestMapping("prhAddRmdev")
	public String AddRmdev(PrhRmdev prhRmdev, Integer eqid, Integer num, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println(prhRmdev.getId());
		System.out.println(prhRmdev.getRoomno());
		System.out.println(prhRmdev.getDevname());
		System.out.println(prhRmdev.getId());
		// 查询所有设备信息
		Equipment eq = equipmentService.findById(eqid);
		prhRmdev.setDevname(eq.getName());
		prhRmdev.setDevnumb(num);
		prhRmdev.setSta("1");

		BigDecimal a = new BigDecimal(eq.getPrice().toString().trim());
		Double b = a.doubleValue();

		prhRmdev.setPrice(b);
		prhRmdev.setDrate(0.0);
		prhRmdev.setNprice(0.0);
		Integer count = prhRmdevService.insert(prhRmdev);
		if (count == 1) {
			System.out.println("num:" + count);
			map.put("houseid", prhRmdev.getRoomno());

			session.setAttribute("addmes", "添加成功！");

			PrintWriter out = response.getWriter();

			out.flush();
			out.println("<script>");
			out.println("parent.layer.closeAll();");
			out.println("</script>");
		}

		map.put("houseid", prhRmdev.getRoomno());
		map.put("message", "添加失败！");
		return "prh/prFileManager/prhRmdev.jsp";

	}

	@RequestMapping("deleteRmdev")
	public String deleteRmdev(Integer id, Integer housid, ModelMap map, HttpSession session,
			HttpServletRequest request) {
		Integer num = prhRmdevService.updateStateByid(id);
		if (num == 1) {
			System.out.println("num+++++++++++++++++++++++++++++++++++++:" + num);
			map.put("message", "修改成功！");
			return "redirect:/ prhRmdev?houseid=" + housid;
		}

		map.put("message", "修改失败！");
		return "redirect:/ prhRmdev?houseid=" + housid;
	}

	// 根据类型分页查询模板信息
	@RequestMapping("prhRmdevMoBan")
	public String prhRmdevMoBan(Integer houseid, Integer roid, Integer currpage, ModelMap map, HttpSession session,

			HttpServletRequest request) {
		// //查询所有 模板信息 model=1
		// if(roid==null||roid<=0){
		// roid=1;
		// }
		// System.out.println("houseid+"+houseid);
		// System.out.println("roid+"+roid);
		// List<RoomType> roomtypes=roomTypeService.findAll();
		// //根据roid查询模板信息
		// List<RmdevTemp> temp =rmdevTempService.findByRoid(roid);
		// //根据temp的eqid 查询 name 放在一个
		//
		// //查询所有房间类型
		//
		// map.put("rmdevtemp", temp);
		// map.put("roomtypes", roomtypes);
		// map.put("houseid", houseid);
		// map.put("roid", roid);
		// return "prh/prFileManager/rmdevTemp.jsp";

		// 处理当前页

		System.out.println("houseid：" + houseid);
		System.out.println("roid：" + roid);

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 5; // 页大小

		List<RoomType> roomtypes = roomTypeService.findAll();
		if (roid == null || roid <= 0) {
			roid = 1;
		}
		// 根据roid 查询所有信息
		List<RmdevTemp> temp = rmdevTempService.findByRoid(roid);
		int total = temp.size();
		TempCondition condition = new TempCondition();
		condition.setRoid(roid);

		// 分页查询
		List<RmdevTemp> temp1 = rmdevTempService.findByRoidAndPaged(condition, currpage, size);
		// 查询总数
		// 分页工具类

		NumPageUtil page = new NumPageUtil("prhRmdevMoBan?houseid=" + houseid + "&roid=" + roid, total, currpage, size);
		List<Equipment> equipment = new ArrayList<>();
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		for (RmdevTemp rmdevTemp : temp1) {
			System.out.println(rmdevTemp.getRoomtype());
			System.out.println(rmdevTemp.getEqid());

			BigDecimal a = new BigDecimal(rmdevTemp.getEqid().toString());
			int b = a.intValue();

			int id = b;

			// 根据 设备id 查询 设备信息 存到equipment中
			Equipment eq = equipmentService.findById(id);
			equipment.add(eq);

		}
		// 存作用域
		page.setList(temp1);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("page", page);
		map.put("temp", temp);
		map.put("houseid", houseid);
		map.put("equipment", equipment);
		map.put("roid", roid);
		map.put("roomtypes", roomtypes);
		return "prh/prFileManager/rmdevTemp.jsp";

	}

	@RequestMapping("prhAddRmdevList")
	public void prhAddRmdevList(Integer houseid, Integer roid, Integer currpage, ModelMap map, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("roid:" + roid);
		System.out.println("houseid:" + houseid);
		List<RmdevTemp> temp = rmdevTempService.findByRoid(roid);// 根据房间类型id 查询
																	// 一个模板
																	// submitForm

		System.out.println("roid" + roid);
		for (RmdevTemp rmdevTemp : temp) {

			System.out.println("asdasdadasdasdsada:" + rmdevTemp.getRoomtype());
		}
		// 取出模板信息
		boolean flag = rmdevTempService.addTempList(temp, houseid);
		if (flag) {
			map.put("message", "使用模板新增房间设备成功");
			PrintWriter out = response.getWriter();

			out.flush();
			out.println("<script>");
			out.println("parent.layer.closeAll();");
			out.println("</script>");

		}

		PrintWriter out = response.getWriter();

		out.flush();
		out.println("<script>");
		out.println("parent.layer.closeAll();");
		out.println("</script>");

	}

	@RequestMapping("addRmdevbTemp")
	public String addRmdevbTemp(ModelMap map, HttpSession session, HttpServletRequest request) {
		// 查询全部房间类型信息

		List<RoomType> roomtypes = roomTypeService.findAll();
		// 查询全部设备信息
		List<Equipment> equipment = equipmentService.findAllEqu();
		// 存到一个页面 存标识
		int count = 1;
		List<RmdevTemp> temps = session.getAttribute("rmdevTemps") != null
				? (List<RmdevTemp>) session.getAttribute("rmdevTemps") : new ArrayList<>();
		String roomname = session.getAttribute("roomname") != null ? (String) session.getAttribute("roomname")
				: new String();
		if (temps.size() > 0) {

			map.put("temps", temps);
		}
		map.put("count", count);
		map.put("equipment", equipment);
		map.put("roomtypes", roomtypes);
		map.put("roomname", roomname);
		System.out.println("roomname:" + roomname);
		return "prh/prFileManager/addRmdevbTempList.jsp";

	}

	// 最后提交 模板
	@RequestMapping("OverAddTemp")
	public String OverAddTemp(String typename, ModelMap map, HttpSession session, HttpServletRequest request) {

		// 从session 中取到temp集合
		List<RmdevTemp> temps = session.getAttribute("rmdevTemps") != null
				? (List<RmdevTemp>) session.getAttribute("rmdevTemps") : new ArrayList<>();
		// 从中取 房间类型
		String roomname = session.getAttribute("roomname") != null ? (String) session.getAttribute("roomname")
				: new String();
		RoomType room = roomTypeService.findtByName(roomname);
		Integer id = room.getId();
		System.out.println("roomtype" + id);
		System.out.println("daxiao :" + temps.size());

		boolean flag = rmdevTempService.insertRmdevModel(temps, id);
		if (flag) {
			session.setAttribute("message", "添加一条模板成功！");
			session.removeAttribute("roomname");
			session.removeAttribute("rmdevTemps");
			return "redirect:/prHouseFileByCondition";

		} else {
			session.setAttribute("message", "添加一条模板失败！");
			session.removeAttribute("rmdevTemps");
			session.removeAttribute("roomname");
			return "redirect:/prHouseFileByCondition";
		}

	}

	@RequestMapping("moreAddRmdev")
	public String moreAddRmdev(Integer roid,Integer estateId, Integer currpage, String typename, ModelMap map, HttpSession session,
			HttpServletRequest request) {

		List<Estate> estates = estateService.findAll();
		List<RoomType> roomtypes = roomTypeService.findAll();
		List<BuildingNo> danyuan = null;
		if (estateId!=null) {
			 danyuan = buildingNoService.findByEstate(estateId);	
		}
		

		System.out.println("roid：" + roid);

		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 5; // 页大小

		if (roid == null || roid <= 0) {
			roid = 1;
		}
		// 根据roid 查询所有信息
		List<RmdevTemp> temp = rmdevTempService.findByRoid(roid);
		int total = temp.size();
		TempCondition condition = new TempCondition();
		condition.setRoid(roid);

		// 分页查询
		List<RmdevTemp> temp1 = rmdevTempService.findByRoidAndPaged(condition, currpage, size);
		// 查询总数
		// 分页工具类

		NumPageUtil page = new NumPageUtil("moreAddRmdev?roid=" + roid+"&estateId="+estateId, total, currpage, size);
		List<Equipment> equipment = new ArrayList<>();
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		for (RmdevTemp rmdevTemp : temp1) {
			System.out.println(rmdevTemp.getRoomtype());
			System.out.println(rmdevTemp.getEqid());

			BigDecimal a = new BigDecimal(rmdevTemp.getEqid().toString());
			int b = a.intValue();

			int id = b;

			// 根据 设备id 查询 设备信息 存到equipment中
			Equipment eq = equipmentService.findById(id);
			equipment.add(eq);

		}
		// 存作用域
		page.setList(temp1);
		
		Estate estate = estateService.findById(estateId);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		map.put("estate", estate );
		map.put("page", page);
		map.put("temp", temp);
		map.put("equipment", equipment);
		map.put("roid", roid);
		map.put("roomtypes", roomtypes);
		map.put("estates", estates);
		map.put("roomtypes", roomtypes);
		map.put("danyuan", danyuan);
		return "prh/prFileManager/moreAddRmdev.jsp";
	}

	@RequestMapping("moreAddRmdevList")
	public String moreAddRmdevList(String danyuanid, String estateId, String roomtypeid, String roid, Integer currpage,
			String typename, ModelMap map, HttpSession session, HttpServletRequest request) {
		System.out.println("单元id" + danyuanid);
		System.out.println("小区id" + estateId);
		System.out.println("房型id" + roomtypeid);
		System.out.println("模板id" + roid);
		// 查询所有符合条件的房子
		HouseRmdevCondition condition = new HouseRmdevCondition();
		if (danyuanid.equals("-1")) {
			danyuanid = "";
		}
		if (estateId.equals("-1")) {
			estateId = "";

		}
		if (roomtypeid.equals("-1")) {
			roomtypeid = "";
		}

		condition.setDanyuanid(danyuanid);
		condition.setEstateId(Integer.valueOf(estateId));
		condition.setRoomtypeId(Integer.valueOf(roomtypeid));

		List<PrHouse> houses = prHouseService.findByHouseRmdevCondition(condition);

		System.out.println("查出来的符合条件的房子的个数:" + houses.size());
		// 模板信息
		List<RmdevTemp> temp = rmdevTempService.findByRoid(Integer.valueOf(roid));
		// 往房子里添加模板循环添加
		Integer flag = rmdevTempService.moreAdd(temp, houses);
		if (flag == 1) {
			// 添加成功
			session.setAttribute("message", "批量添加成功");
		} else {
			session.setAttribute("message", "批量添加失败");
		}
		return "redirect:/prHouseFileByCondition";
	}

}
