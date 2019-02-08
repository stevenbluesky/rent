package com.rent.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;
import com.rent.condition.HouseCondition;
import com.rent.condition.HouseFileContion;
import com.rent.condition.HouseRmdevCondition;
import com.rent.dao.PrHouseMapper;
import com.rent.dao.PrhMasterMapper;
import com.rent.dao.ProfileMapper;
import com.rent.entity.BuildingFloor;
import com.rent.entity.BuildingNo;
import com.rent.entity.Estate;
import com.rent.entity.PrHouse;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhMaster;
import com.rent.entity.PrhRental;
import com.rent.entity.Profile;
import com.rent.entity.RoomType;
import com.rent.entity.Subsidy;
import com.rent.entity.Users;
import com.rent.services.BuildingFloorService;
import com.rent.services.BuildingNoService;
import com.rent.services.EstateService;
import com.rent.services.PrHouseService;
import com.rent.services.PrhLinkManService;
import com.rent.services.PrhMasterService;
import com.rent.services.RoomTypeService;
@Transactional(readOnly = true)
@Service
public class PrHouseServiceImpl implements PrHouseService{
	@Autowired
	private PrHouseMapper prHouseMapper;
	@Autowired
	private BuildingFloorService buildingFloorService;
	@Autowired
	private EstateService estateService;
	@Autowired
	private BuildingNoService buildingNoService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private PrhMasterService prhMasterService;
	@Autowired
	private PrhMasterMapper prhMasterMapper;
	
	@Autowired
	private ProfileMapper profileMapper;
	
	@Autowired
	private PrhLinkManService prhLinkManService;
	
	
	public PrhLinkManService getPrhLinkManService() {
		return prhLinkManService;
	}

	public void setPrhLinkManService(PrhLinkManService prhLinkManService) {
		this.prhLinkManService = prhLinkManService;
	}

	public ProfileMapper getProfileMapper() {
		return profileMapper;
	}

	public void setProfileMapper(ProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}

	public BuildingFloorService getBuildingFloorService() {
		return buildingFloorService;
	}

	public void setBuildingFloorService(BuildingFloorService buildingFloorService) {
		this.buildingFloorService = buildingFloorService;
	}

	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}

	public BuildingNoService getBuildingNoService() {
		return buildingNoService;
	}

	public void setBuildingNoService(BuildingNoService buildingNoService) {
		this.buildingNoService = buildingNoService;
	}

	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}

	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}

	public PrHouseMapper getPrHouseMapper() {
		return prHouseMapper;
	}

	public void setPrHouseMapper(PrHouseMapper prHouseMapper) {
		this.prHouseMapper = prHouseMapper;
	}
	private void setGuideProperty(PrHouse p){
	
		p.setBuildingFloor(buildingFloorService.findById(p.getBuildingFloorId()));
		p.setBuildingNo(buildingNoService.findById(p.getBuildingNoId()));
		p.setEstate(estateService.findById(p.getEstateId()));
		p.setRoomType(roomTypeService.findById(p.getRoomTypeId()));
		if (p.getMasterId()!=null) {
			p.setPrhMaster(prhMasterMapper.selectByPrimaryKey(p.getMasterId()));	
		}
		
	}
	private void setGuideProperty(List<PrHouse> prHouses){
		for (PrHouse p : prHouses) {
			p.setBuildingFloor(buildingFloorService.findById(p.getBuildingFloorId()));
			p.setBuildingNo(buildingNoService.findById(p.getBuildingNoId()));
			p.setEstate(estateService.findById(p.getEstateId()));
			p.setRoomType(roomTypeService.findById(p.getRoomTypeId()));	
			if (p.getMasterId()!=null) {
				p.setPrhMaster(prhMasterMapper.selectByPrimaryKey(p.getMasterId()));
				if (p.getPrhMaster()!=null&&p.getPrhMaster().getGuestNo()!=null) {
					p.getPrhMaster().setProfile(profileMapper.selectByPrimaryKey( p.getPrhMaster().getGuestNo()));
					String linkNames="";
					List<PrhLinkman> links = prhLinkManService.findAllByMaster(p.getMasterId());
					for (PrhLinkman link : links) {
						if (linkNames.length()==0) {
							
							linkNames+=link.getProfile().getName();
						}else{
							if (linkNames!=null&&link.getProfile()!=null&&link.getProfile().getName()!=null) {
								linkNames+=","+link.getProfile().getName();
							}
							
						}
					}
					if (linkNames.length()==0) {
						linkNames="（无）";
					}
					p.getPrhMaster().setLinkManNames(linkNames);
				}
				
				
				
				

			}
		}
		
	}
	//閿熸枻鎷烽敓鏂ゆ嫹
	public int addPrHouse(PrHouse prHouse) {
		Integer currId = prHouseMapper.getCurrId();
		String houseCode = getHouseCode(currId);
		prHouse.setHouseCode(houseCode);
		prHouse.setId(currId);
		return prHouseMapper.insert(prHouse);
	}
	//閿熺潾闈╂嫹
	
	@Transactional(readOnly=false)
	public int updatePrHouse(PrHouse prHouse) {
		PrHouse findByNo = prHouseMapper.selectByPrimaryKey(prHouse.getId());
		prHouse.setId(findByNo.getId());
		Integer roomNoId= Integer.parseInt(prHouse.getRoomNo().substring(prHouse.getBuildingFloorId().toString().length()));
		prHouse.setRoomNoId(roomNoId);
		//取出原masterId
		if (prHouse.getMasterId()==null) {
			prHouse.setMasterId(findByNo.getMasterId());
		}
		//把master设为null
		if (prHouse.getMasterId()!=null&& prHouse.getMasterId()==-1) {
			prHouse.setMasterId(null);
		}
		if (prHouse.getHouseCode()==null) {
			String houseCode = getHouseCode(findByNo.getId());
			prHouse.setHouseCode(houseCode);
		}
		
		
		return prHouseMapper.updateByPrimaryKey(prHouse);
	}
	//閫氶敓鏂ゆ嫹id閿熸枻鎷烽敓鏂ゆ嫹
	public PrHouse findById(Integer id) {
		PrHouse prHouse = prHouseMapper.selectByPrimaryKey(id);
		setGuideProperty(prHouse);
		return prHouse;
	}

	public int delPrHouse(Integer id) {
		return prHouseMapper.deleteByPrimaryKey(id);
		
	}
	//閿熸枻鎷烽敓鏂ゆ嫹鍒犻敓鏂ゆ嫹
	public int delPrHouse(Integer[] ids) {
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result = prHouseMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
		
	}
	
	//閿熸枻鎷疯閿熸枻鎷蜂笟閿熷彨鎲嬫嫹
	public List<Estate> getAllEstate() {
		return estateService.findAll();
	}
	//閿熸枻鎷疯妤奸敓鏂ゆ嫹閿熷彨鎲嬫嫹	
	public List<BuildingNo> getAllBuildingNo(int estateId){
		return buildingNoService.findByEstate(estateId);
	}
	//閿熸枻鎷疯妤奸敓鏂ゆ嫹閿熷彨鎲嬫嫹
	public List<BuildingFloor> getAllBuildingFloor(){
		return buildingFloorService.findAll();
	}
	//閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熷彨鎲嬫嫹
	public List<RoomType> getAllRoomType(){
		return roomTypeService.findAll();
	}
	//閿熸枻鎷烽〉
	public List<PrHouse> findByEstatePaged(Integer estateId, Integer currage,
			Integer size) {
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		List<PrHouse> prHouses = prHouseMapper.findByEstatePaged(estateId, begin, end);
		setGuideProperty(prHouses);
		return prHouses;
	}
	public int getCountByEstate(Integer estateId) {
		
		return prHouseMapper.getCountByEstate(estateId);
	}
	
	
	public List<PrHouse> readByExcelStr(String [][] result,Users user){
		Integer estateId=user.getEstateId();
		
		List<PrHouse> prHouses=new ArrayList<PrHouse>();
		try {
			int rowLength = result.length;
			for(int i=0;i<rowLength;i++) {
				PrHouse prHouse=new PrHouse();
				
				prHouse.setEstateId(estateService.findByName(result[i][0]).getId());
				//权限判断
				if (estateId!=null&&prHouse.getEstateId()!=estateId) {
					return null;
				}
				Integer  building = ((Double)Double.parseDouble(result[i][1])).intValue();
				Integer unitId= ((Double)Double.parseDouble(result[i][2])).intValue();
				
				
				String id = buildingNoService.findByName(prHouse.getEstateId(),building+""+unitId+"单元").getId();
				prHouse.setBuildingNoId(id);
				prHouse.setRoomNo(Double.valueOf(result[i][3]).intValue()+"");
				prHouse.setBuildingFloorId(Double.valueOf(result[i][4]).intValue());
				
				prHouse.setRoomTypeId(roomTypeService.findtByName(result[i][5]).getId());
				
				prHouse.setRoomNum(Double.valueOf(result[i][6]).intValue());
				
				prHouse.setArea(BigDecimal.valueOf(Double.parseDouble(result[i][7])));
				/*prHouse.setPrice(BigDecimal.valueOf(Double.parseDouble(result[i][8])));*/
				prHouse.setMonthPrice(BigDecimal.valueOf(Double.parseDouble(result[i][8])));
				prHouse.setBackPrice(BigDecimal.valueOf(Double.parseDouble(result[i][9])));   //地下室面积
				
				Short sta=new Short(Double.valueOf(result[i][10]).intValue()+"") ;
				prHouse.setState(sta);
				
				//操作人
				prHouse.setCreateuser(user.getId().toString());
				prHouse.setCreatetime(new Date());
				prHouse.setUpdateuser(user.getId().toString());
				prHouse.setUpdatetime(new Date());
				
				//非法状态判断
				if (prHouse.getState()!=4&&prHouse.getState()!=6&&prHouse.getState()!=7) {
					return null;
				}
				
				if (prHouse.getState()==4) {
					prHouse.setRemark("新房源锁定");
				}
				
				prHouse.setNo(prHouse.getBuildingNoId()+"-"+prHouse.getRoomNo());
				
				prHouses.add(prHouse);
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return prHouses;
	}

	//閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	public int addAuto(List<PrHouse> prHouses) {
		int result=0;
		for (PrHouse prHouse : prHouses) {
			System.out.println("测试："+ prHouse.getRoomNo());
			Integer roomNoId= Integer.parseInt(prHouse.getRoomNo().substring(prHouse.getBuildingFloorId().toString().length()));
			prHouse.setRoomNoId(roomNoId);
			
			//查找是否已经存在
			PrHouse findByNo = findByNo(prHouse.getNo());
			if (findByNo!=null) {
				prHouse.setId(findByNo.getId());
				prHouse.setRoomNoId(roomNoId);
				prHouse.setHouseCode(findByNo.getHouseCode());
				prHouse.setMasterId(findByNo.getMasterId());
				prHouse.setState(findByNo.getState());
				if (prHouse.getState()!=4) {
					prHouse.setRemark(null);
				}
				if (prHouse.getHouseCode()==null) {
					String houseCode = getHouseCode(findByNo.getId());
					prHouse.setHouseCode(houseCode);
				}
				
				
				result=prHouseMapper.updateByPrimaryKey(prHouse);//修改
			}else {
				result = this.addPrHouse(prHouse);   //新增
				
			}
			if (result==-1) {
				return -1;
			}
		}
		return 1;
	}
	
	
	private String getHouseCode(int houseId){
		String id = houseId+"";
		int length=10-id.length();
		String before="";
		for (int i = 0; i < length; i++) {
			before+="0";
		}
		return before+id;
	}
	
	
	//閿熸枻鎷烽敓鎹锋唻鎷锋尓閿熺獤锟�
	public PrHouse findByNo(String no) {
		return prHouseMapper.findByNo(no);
	}

	//閺嶈宓侀弶鈥叉閺屻儴顕�
	@Override
	public List<PrHouse> findByCondition(HouseCondition condition) {
		 List<PrHouse> houses = prHouseMapper.findByCondition(condition);
		 setGuideProperty(houses);
		 return houses;
	}
	
	
	
	@Override
	public List<PrHouse> findByConditionPaged(HouseFileContion condition,Integer currage,Integer size) {
		
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrHouse> prHouses = prHouseMapper.findByConditionPaged(condition);
		setGuideProperty(prHouses);
		return prHouses;
	}

	@Override
	public List<PrHouse> findByFileCondition(HouseFileContion condition) {
		
		return prHouseMapper.findByFileCondition(condition);
	}

	@Override
	public Integer getStateById(int houseId) {
		return prHouseMapper.getStateById(houseId);
	}

	@Override
	public List<PrHouse> findHouseByConditionPaged(HouseCondition condition) {
		List<PrHouse> findHouseByConditionPaged = prHouseMapper.findHouseByConditionPaged(condition);
		setGuideProperty(findHouseByConditionPaged);
		return findHouseByConditionPaged;
	}

	@Override
	public Integer getHouseCountByConditionPaged(HouseCondition condition) {
		return prHouseMapper.getHouseCountByConditionPaged(condition); 
	}

	@Override
	public Integer getCountByEach(Integer state, Integer estateId, String buildingNoId, String buildingId) {
		return prHouseMapper.getCountByEach(new HouseCondition(estateId, buildingId, buildingNoId, state));
	}

	@Override
	public List<PrHouse> findByHouseRmdevCondition(HouseRmdevCondition condition) {
		return prHouseMapper.findByHouseRmdevCondition(condition);
	}

	@Override
	public List<PrHouse> findByFileConditionAndPaged(HouseFileContion condition, Integer currage, Integer size) {
		Integer begin=(currage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrHouse> houses = prHouseMapper.findByFileConditionAndPaged(condition);
		setGuideProperty(houses);
		return houses;
	}

	@Override
	public boolean plan(List<PrHouse> houses, Double rentMod, String reason, String decMan, Date decDate) {
			//循环遍历 houses 
		try {
			for (PrHouse prHouse : houses) {
				prHouse.setReason(reason);
				BigDecimal big =  new BigDecimal(rentMod);
				prHouse.setRentMod(big);
				prHouse.setDecDate(decDate);
				prHouse.setDecMan("session虚拟");
				
				
				prHouseMapper.updateByPrimaryKey(prHouse);
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public Integer doPlan() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		Date date = new Date();
		String str=null;
		String str2 = null;
		//查询所有有计划的 房子
		List<PrHouse> houses = prHouseMapper.findPlanHouse();
		for (PrHouse prHouse : houses) {
			str = sdf.format(prHouse.getDecDate()); 
			str2 = sdf.format(date); 
			System.out.println(str);
			System.out.println(str2);
			if(str.equals(str2)){
				//获取租金
				prHouse.getRentMod();
				prHouse.setMonthPrice(prHouse.getRentMod());
				prHouseMapper.updateByPrimaryKey(prHouse);
				
			}else{
				System.out.println("不调整租金");
				return 2;
				
			}
			
		}
		return 1;
	}

	@Override
	public boolean ExplainPlan(List<PrhRental> rentals) {
		try {
			for (PrhRental prhRental : rentals) {
				
				PrhMaster master = prhMasterService.findById(prhRental.getAccnt());
				PrHouse houses = prHouseMapper.selectByPrimaryKey(master.getHouseId());
				Short a=new Short("3");
				houses.setCheapPrice(new BigDecimal(houses.getState()));
				
				houses.setState(a);	
				prHouseMapper.updateByPrimaryKey(houses);
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public Integer findCountByFileCondition(HouseFileContion condition) {
		// TODO Auto-generated method stub
		return prHouseMapper.findCountByFileCondition(condition);
	}

	@Override
	public List<PrHouse> findByFileCondition2(HouseFileContion condition) {
		// TODO Auto-generated method stub
		 return prHouseMapper.findByFileCondition2(condition);
	}

	@Override
	public List<PrHouse> findByFileCondition2Paged(HouseFileContion condition, Integer currpage, Integer size) {
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<PrHouse> houses = prHouseMapper.findByFileConditionAndPaged2(condition);
		setGuideProperty(houses);
		return houses;
	}

	
	
	
	@Override
	public void test() {
		PrHouse findById1 = this.findById(298);
		PrHouse findById2 = this.findById(298);
		findById1.setState(Short.valueOf("8"));
		findById2.setState(Short.valueOf("8"));
		this.updatePrHouse(findById1);
		int a=0;
		int b=12/a;
		
		this.updatePrHouse(findById2);
		
	}

	@Override
	public List<PrHouse> findByBuildingNo(String buildingNoId) {
		return prHouseMapper.findByBuildingNo(buildingNoId);
	}




}
