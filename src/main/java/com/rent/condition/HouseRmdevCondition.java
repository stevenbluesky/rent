package com.rent.condition;

public class HouseRmdevCondition {
	
	private Integer estateId;
	private Integer roomtypeId;
	private String danyuanid;
	public Integer getEstateId() {
		return estateId;
	}
	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}
	public Integer getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(Integer roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public String getDanyuanid() {
		return danyuanid;
	}
	public void setDanyuanid(String danyuanid) {
		this.danyuanid = danyuanid;
	}
	public HouseRmdevCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseRmdevCondition(Integer estateId, Integer roomtypeId, String danyuanid) {
		super();
		this.estateId = estateId;
		this.roomtypeId = roomtypeId;
		this.danyuanid = danyuanid;
	}
	
	
}
