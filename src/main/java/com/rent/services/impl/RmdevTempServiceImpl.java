package com.rent.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.condition.TempCondition;
import com.rent.dao.RmdevTempMapper;
import com.rent.entity.Equipment;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhRmdev;
import com.rent.entity.Profile;
import com.rent.entity.RmdevTemp;
import com.rent.services.EquipmentService;
import com.rent.services.PrhRmdevService;
import com.rent.services.RmdevTempService;
import com.rent.services.RoomTypeService;

@Service
@Transactional(readOnly = true)
public class RmdevTempServiceImpl implements RmdevTempService {

	@Autowired
	private RmdevTempMapper rmdevTempMapper;

	@Autowired
	private PrhRmdevService prhRmdevService;

	@Autowired
	private EquipmentService equipmentService;

	@Override
	public List<RmdevTemp> findByRoid(Integer roid) {
		return rmdevTempMapper.findByRoid(roid);
	}



	@Override
	public List<RmdevTemp> findByRoidAndPaged(TempCondition condition, Integer currage, Integer size) {
		int begin = (currage - 1) * size + 1;
		int end = begin + size - 1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<RmdevTemp> rmdevTemp = rmdevTempMapper.findByRoidAndPaged(condition);

		return rmdevTemp;
	}

	@Override
	public boolean addTempList(List<RmdevTemp> temp, Integer houseid) {
		try {
			for (RmdevTemp rmdevTemp : temp) {
				// 根据模板里设备的id 查询 设备名字
				BigDecimal a = new BigDecimal(rmdevTemp.getEqid().toString());

				// 查询最大房间设备表最大id
				int rmdevid = prhRmdevService.findMaxID();
				System.out.println("houseid:" + houseid);
				int b = a.intValue();
				int id = b;
				// 根据 设备id 查询 设备信息 存到equipment中
				Equipment eq = equipmentService.findById(id);
				String name = eq.getName();
				BigDecimal c = new BigDecimal(eq.getPrice().toString());
				double price = c.doubleValue();
				// 声明房间设备实体类
				PrhRmdev rmdev = new PrhRmdev();
				rmdev.setId(rmdevid + 1);
				rmdev.setRoomno(houseid.toString());
				rmdev.setDevclass(rmdevTemp.getCla().toString().trim());

				rmdev.setDevname(name);
				rmdev.setSta("1");
				BigDecimal d = new BigDecimal(rmdevTemp.getNum().toString());
				rmdev.setDevnumb(d.intValue());
				rmdev.setPrice(price);
				// 折旧率
				Double drate = 100.0;
				// nprice成本价

				rmdev.setDrate(drate);
				Double nprice = 100.0;
				rmdev.setNprice(nprice);
				prhRmdevService.insert(rmdev);

				// 新增 进 房间 设备表

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean insertRmdevModel(List<RmdevTemp> temp, Integer houseid) {
		try {
			for (RmdevTemp rmdevTemp : temp) {
				// 查询最大模板表id
				Integer tempId = rmdevTempMapper.findMaxId();
				BigDecimal id =null;
				if (tempId==null) {
					id=new BigDecimal(1);
				}else{
					id = new BigDecimal(tempId + 1);
				}
				// 新增一条数据
				RmdevTemp record = new RmdevTemp();
				record.setCla(rmdevTemp.getCla());
				record.setEqid(rmdevTemp.getEqid());
				record.setId(id);
				record.setNum(rmdevTemp.getNum());
				record.setRoomtype(rmdevTemp.getRoomtype());
				rmdevTempMapper.insert(record);
				// 新增 进 房间 设备表
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Integer findMaxId() {

		return rmdevTempMapper.findMaxId();
	}

	@Override
	public Integer moreAdd(List<RmdevTemp> temp, List<PrHouse> houses) {
		for (PrHouse prHouse : houses) {
			Integer houseid = prHouse.getId();
			try {
				for (RmdevTemp rmdevTemp : temp) {
					// 根据模板里设备的id 查询 设备名字
					BigDecimal a = new BigDecimal(rmdevTemp.getEqid().toString());

					// 查询最大房间设备表最大id
					int rmdevid = prhRmdevService.findMaxID();
					System.out.println("houseid:" + houseid);
					int b = a.intValue();
					int id = b;
					// 根据 设备id 查询 设备信息 存到equipment中
					Equipment eq = equipmentService.findById(id);
					String name = eq.getName();
					BigDecimal c = new BigDecimal(eq.getPrice().toString());
					double price = c.doubleValue();
					// 声明房间设备实体类
					PrhRmdev rmdev = new PrhRmdev();
					rmdev.setId(rmdevid + 1);
					rmdev.setRoomno(houseid.toString());
					rmdev.setDevclass(rmdevTemp.getCla().toString().trim());

					rmdev.setDevname(name);
					rmdev.setSta("1");
					BigDecimal d = new BigDecimal(rmdevTemp.getNum().toString());
					rmdev.setDevnumb(d.intValue());
					rmdev.setPrice(price);
					// 折旧率
					Double drate = 100.0;
					// nprice成本价

					rmdev.setDrate(drate);
					Double nprice = 100.0;
					rmdev.setNprice(nprice);
					prhRmdevService.insert(rmdev);

					// 新增 进 房间 设备表

				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}

		}
		return 1;
	}


	public int delRmdevTemp(Integer[] ids){
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= rmdevTempMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
	}

	public RmdevTemp findById(Integer id){
		return rmdevTempMapper.selectByPrimaryKey(id);
	}



	@Override
	@Transactional(readOnly=false)
	public void editTemplate(Integer[] pId, Integer[] nums, Integer roomId) {
		//删除该房型的设备
		Integer delByRoomType = delByRoomType(roomId);
		System.out.println("删除"+delByRoomType+"条数据成功");
		// 查询最大模板表id
		Integer tempId = rmdevTempMapper.findMaxId();
		BigDecimal id =null;
		if (tempId==null) {
			id=new BigDecimal(1);
		}else{
			id = new BigDecimal(tempId + 1);
		}
		//新增设备
		for (int i = 0; i < nums.length; i++) {

			Integer num=Integer.valueOf(nums[i]);
			Integer pid=Integer.valueOf(pId[i]);
			String typeId = equipmentService.findById(pid).getType().toString();
			RmdevTemp temp=new RmdevTemp(id,roomId.toString(),typeId,new BigDecimal(pid),new BigDecimal(num));
			rmdevTempMapper.insert(temp);
			System.out.println("插入成功");
			id = new BigDecimal(Integer.parseInt(id.toString()) + 1);
		}


	}


	@Override
	public Integer delByRoomType(Integer typeId) {
		return  rmdevTempMapper.delByRoomType(typeId);
	}
}
