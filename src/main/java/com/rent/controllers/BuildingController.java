package com.rent.controllers;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.rent.common.utils.NumPageUtil;
import com.rent.entity.Building;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.services.BuildingNoService;
import com.rent.services.BuildingService;
import com.rent.services.EstateService;
import com.rent.services.PrHouseService;

@Controller
@Transactional(readOnly=false)
public class BuildingController {
	@Autowired
	private PrHouseService prHouseService;

	public PrHouseService getPrHouseService() {
		return prHouseService;
	}

	public void setPrHouseService(PrHouseService prHouseService) {
		this.prHouseService = prHouseService;
	}

	@Autowired
	private BuildingService buildingService;
	@Autowired
	private EstateService estateService;

	@Autowired
	private BuildingNoService buildingNoService;

	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}

	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public BuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	// 分页查询
	@RequestMapping("findBuildingByEstatePaged.do")
	public String findByEstate(Integer estateId, Integer currpage, ModelMap map, HttpSession session,
							   HttpServletRequest request) {
		// 处理当前页
		if (currpage == null || currpage <= 0) {
			currpage = 1;
		}
		Integer size = 10; // 页大小
		// 查询物业集合
		List<Estate> estates = estateService.findAll();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		// 分页查询
		List<Building> buildings = buildingService.findByEstatePaged(estateId, currpage, size);
		// 查询总数
		int total = buildingService.getCountByEstate(estateId);
		// 分页工具类
		NumPageUtil page = new NumPageUtil("findBuildingByEstatePaged.do?estateId=" + estateId, total, currpage, size);
		page.setBothnum(3);
		String numpage = page.showNumPage();
		currpage = page.getCurrpage();

		// 存作用域
		page.setList(buildings);
		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);

		map.put("page", page);
		map.put("estateId", estateId);
		map.put("estates", estates);
		return "prhCode/building/building.jsp";
	}

	// 转到新增
	@RequestMapping("toBuildingAdd")
	public String toBuildingAdd(Integer estateId, ModelMap map) {
		map.put("estateId", estateId);

		List<BuildingNo> bNos = buildingNoService.getAllByRangeEstate(estateId, 0, 100000000);
		map.put("bNos", bNos);
		return "prhCode/building/buildingAdd.jsp";
	}

	// 新增
	@RequestMapping("buildingAdd")
	@Transactional(readOnly=false)
	public String buildingAdd(Integer estateId, String name,  String[] buildNo,
							  ModelMap map,HttpSession session) {
		// 当前页
		Integer currpage = (Integer) session.getAttribute("currpage");
		List<Building> findByName = buildingService.findByNameAndEstate(name, estateId);

		if (findByName!=null&& findByName.size() != 0) {
			map.put("tip", "楼号重复！");

			return "prhCode/building/buildingAdd.jsp";
		} else {
			String buildingnocode = "";

			for (int i = 1; i <=8; i++) {
				buildingnocode += i + "单元、";
				BuildingNo buildingNo=new BuildingNo(estateId+"-"+name+"-"+i, name+""+i+"单元", null, estateId, null, null);
				buildingNo.setBuildingId(name);
				buildingNoService.addBuildingNo(buildingNo);
			}
			buildingnocode = buildingnocode.substring(0, buildingnocode.length() - 1);
			Building building = new Building(null, name, buildingnocode, estateId, null);
			buildingService.addBuilding(building);
			return "../findBuildingByEstatePaged.do?currpage="+currpage;
		}

	}


	// 转到修改页面
	@RequestMapping("toEditBuilding.do")
	public String toEditBuilding(String id, ModelMap map) {
		Building building = buildingService.findById(id);
		// 楼号集合
		map.put("name", building.getName());
		map.put("estateId", building.getEstateId());
		map.put("building", building);
		Estate estate = estateService.findById(building.getEstateId());
		map.put("estate", estate);
		// 单元数组
		String[] array = building.getBuildingnocode().split("#");
		map.put("array", array);
		return "prhCode/building/buildingEdit.jsp";
	}

	// 转到开单元卡
	@RequestMapping("toOpenBuildingNoCard.do")
	public String toOpenBuildingNoCard(Integer estateId, String id, ModelMap map) {
		Building building = buildingService.findById(id);
		List<BuildingNo> buildingNos = buildingNoService.findByBuildingIdAndEstate(Integer.parseInt(building.getName()), estateId);
		map.put("buildingNos", buildingNos);
		// 楼号集合
		map.put("estateId", building.getEstateId());
		map.put("building", building);
		Estate estate = estateService.findById(building.getEstateId());
		map.put("estate", estate);



		return "prhCode/building/buildingNoCard.jsp";
	}



	//楼号修改
	@RequestMapping("buildingEdit.do")
	@Transactional(readOnly = false)
	public String buildingEdit(String id, String name, Integer beginNo, Integer endNo, String[] buildNo,
							   String checkedNos, ModelMap map, HttpSession session) {
		// 当前页
		Integer currpage = (Integer) session.getAttribute("currpage");
		Building building = buildingService.findById(id);
		// 删除原来的绑定
		String[] noId = checkedNos.split("-");
		for (String noid : noId) {
			BuildingNo no = buildingNoService.findByName(building.getEstateId(), noid + "单元");
			if (no != null) {
				no.setBuildingId(null);
			}

			buildingNoService.updateBuildingNo(no);
		}

		building.setName(name);
		String buildingnocode = "";

		if (buildNo != null && buildNo.length != 0) {
			for (String no : buildNo) {
				buildingnocode += (name + "-" + no) + "#";

			}
			buildingnocode = buildingnocode.substring(0, buildingnocode.length() - 1);
		}
		building.setBuildingnocode(buildingnocode);
		buildingService.updateBuilding(building);

		return "../findBuildingByEstatePaged.do?currpage="+currpage;
	}
	//删除楼号
	@RequestMapping("delBuilding")
	@Transactional(readOnly = false)
	public String delBuilding(String[] chk, ModelMap map, HttpSession session) {

		// 当前页
		Integer currpage = (Integer) session.getAttribute("currpage");
		// 物业id
		Integer estateId = null;
		if (chk != null && chk.length != 0) {
			estateId = buildingService.findById(chk[0]).getEstateId();
			for (String id : chk) {
				try {
					Building building = buildingService.findById(id);

					String name = building.getName();

					// 判断是否被关联
					boolean isMatched = delBuildingNoFalse(Integer.valueOf(building.getName()),building.getEstateId());

					if (isMatched) {
						throw new Exception();
					}



					buildingService.delBuilding(id);
					buildingNoService.clearBuildingIdBy(Integer.valueOf(name), estateId);

					map.put("tip", "删除成功");
				} catch (Exception e) {
					e.printStackTrace();
					map.put("tip", "该条数据已被关联，请勿删除");
				}

			}
		}
		return "../findBuildingByEstatePaged.do?currpage=" + currpage + "&estateId=" + estateId;
	}

	//判断是否可以删除
	private boolean delBuildingNoFalse(Integer buildingId,Integer estateId) throws Exception {

		//楼号被关联
		List<BuildingNo> findByBuildingIdAndEstate = buildingNoService.findByBuildingIdAndEstate(buildingId, estateId);
		for (BuildingNo buildingNo : findByBuildingIdAndEstate) {
			List<PrHouse> temp = prHouseService.findByBuildingNo(buildingNo.getId());
			if (temp!=null&&temp.size()!=0) {
				return true;
			}
		}

		return false;
		//楼号没被关联


	}

	// 新增
	@RequestMapping("toPosition.do")
	public String toPosition(Integer estateId, ModelMap map) {
		// 查询物业集合
		List<Estate> estates = estateService.findAll();
		if (estateId == null) {
			if (estates != null && estates.size() != 0) {
				estateId = estates.get(0).getId();
			}
		}
		if (estateId != null) {
			Estate estate = estateService.findById(estateId);
			map.put("estate", estate);
		}
		map.put("estates", estates);
		map.put("estateId", estateId);
		// 楼号集合
		List<Building> buildings = buildingService.findAllByEstate(estateId);
		map.put("buildings", buildings);

		// 选中的楼号集合
		List<Building> checkBuildings = buildingService.findByEstatePositioned(estateId);

		map.put("checkBuildings", checkBuildings);

		return "prhCode/building/position.jsp";
	}
	//定位确定
	@RequestMapping("positionBuilding.do")
	@Transactional(readOnly = false)
	public String position(Integer estateId, String[] buildingIds, String[] positionXs, String[] positionYs,
						   @RequestParam(value = "file", required = false) MultipartFile file, ModelMap map,
						   HttpServletRequest request) {
		// 先清空原坐标
		List<Building> allBuilding = buildingService.findByEstatePositioned(estateId);
		for (Building building : allBuilding) {
			building.setPositionX(null);
			building.setPositionY(null);
			buildingService.updateBuilding(building);
		}

		// 当前物业
		Estate estate = estateService.findById(estateId);

		// 上传文件
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			// 获取文件名
			String fileName = file.getOriginalFilename();
			int z = fileName.indexOf(".");
			// 获取后缀
			String houzui = fileName.substring(z + 1);
			// 新文件名

			String str = System.currentTimeMillis() + "" + new Random().nextInt();
			String newfilename = "map" + str + "." + houzui;
			estate.setMapPic(newfilename);
			String path = request.getSession().getServletContext().getRealPath("static/image/building");

			// 创建目标文件
			File targetFile = new File(path, newfilename);
			if (!targetFile.exists()) {
				targetFile.mkdirs();

			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 更新小区信息（图片）
		estateService.updateEstate(estate);
		if (buildingIds != null) {
			for (int i = 0; i < buildingIds.length; i++) {
				String buildingId = buildingIds[i];
				Building building = buildingService.findById(buildingId);
				if (building!=null&& positionXs[i] != null && positionYs[i] != null && positionXs[i].length() != 0
						&& positionYs[i].length() != 0) {
					building.setPositionX(Integer.valueOf(positionXs[i]));
					building.setPositionY(Integer.valueOf(positionYs[i]));
					buildingService.updateBuilding(building);
				}

			}

		}
		map.put("tip", "更新成功");
		return "../toPosition.do?estateId=" + estateId;
	}

	// 清空房态图楼号坐标信息
	@RequestMapping("clearBuildingInDraw.do")
	@Transactional(readOnly = false)
	public String clearBuildingInDraw(Integer estateId, ModelMap map) {
		List<Building> estates = buildingService.findAllByEstate(estateId);
		for (Building building : estates) {
			building.setPositionX(null);
			building.setPositionY(null);
			buildingService.updateBuilding(building);
		}
		return "../toPosition.do?estateId=" + estateId;
	}

}
