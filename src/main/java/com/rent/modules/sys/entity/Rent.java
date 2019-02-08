package com.rent.modules.sys.entity;

import com.rent.common.persistence.DataEntity;
import com.rent.common.utils.MyConvertUtil;
import com.rent.common.utils.StringUtils;

public class Rent extends DataEntity<Rent>{

	private static final long serialVersionUID = 1L;
	private String id;
	private String userID;
	private String houseID;
	private String type;
	private String status;
	private String statusName;
	private String name;
	private String gender;
	private String idNumber;
	private String birthday;
	private String phone;
	private String company;
	private String tenement;
	private String building;
	private String buildingNO;
	private String floor;
	private Integer roomNum;
	private String roomType;
	private String roomSize;
	private Integer tenementID;
	private Integer floorID;
	private String buildingID;
	private String masterID;
	
	private void setPersonInfo() {
		if(this.status == null || this.status.trim().length() == 0){
			return ;
		}
		if(MyConvertUtil.getHouseState(Integer.parseInt(this.status)).equals("锁定")
		 || MyConvertUtil.getHouseState(Integer.parseInt(this.status)).equals("可租")){
			this.name = null;
			this.gender = null;
			this.idNumber = null;
			this.birthday = null;
			this.phone = null;
			this.company = null;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
		this.statusName =  StringUtils.getHouseState(status);
		setPersonInfo();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name != null) {
			name.trim();
		}
		this.name = name;
		setPersonInfo();
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
		setPersonInfo();
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
		setPersonInfo();
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
		setPersonInfo();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
		setPersonInfo();
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
		setPersonInfo();
	}
	public String getTenement() {
		return tenement;
	}
	public void setTenement(String tenement) {
		this.tenement = tenement;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getHouseID() {
		return houseID;
	}
	public void setHouseID(String houseID) {
		this.houseID = houseID;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getBuildingNO() {
		return buildingNO;
	}
	public void setBuildingNO(String buildingNO) {
		this.buildingNO = buildingNO;
	}
	public Integer getTenementID() {
		return tenementID;
	}
	public void setTenementID(Integer tenementID) {
		this.tenementID = tenementID;
	}
	public Integer getFloorID() {
		return floorID;
	}
	public void setFloorID(Integer floorID) {
		this.floorID = floorID;
	}
	public String getBuildingID() {
		return buildingID;
	}
	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}
	public String getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getMasterID() {
		return masterID;
	}

	public void setMasterID(String masterID) {
		this.masterID = masterID;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
