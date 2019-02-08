package com.rent.condition;

public class HouseFileContion {

	private String no;			//楼号
	private Integer estateId; //小区id
	private Integer roomtypeId; //房间类型id
	private String sta;
	private Integer begin;
	private Integer end;
	private String danyuanid;	//单元id
	
	

	public HouseFileContion(String no, Integer estateId, Integer roomtypeId, String sta, Integer begin, Integer end,
			String danyuanid) {
		super();
		this.no = no;
		this.estateId = estateId;
		this.roomtypeId = roomtypeId;
		this.sta = sta;
		this.begin = begin;
		this.end = end;
		this.danyuanid = danyuanid;
	}
	public String getDanyuanid() {
		return danyuanid;
	}
	public void setDanyuanid(String danyuanid) {
		this.danyuanid = danyuanid;
	}
	public HouseFileContion(String no, Integer estateId, Integer roomtypeId, String sta, Integer begin, Integer end) {
		super();
		this.no = no;
		this.estateId = estateId;
		this.roomtypeId = roomtypeId;
		this.sta = sta;
		this.begin = begin;
		this.end = end;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public HouseFileContion(String no, Integer estateId, Integer roomtypeId, String sta) {
		super();
		this.no = no;
		this.estateId = estateId;
		this.roomtypeId = roomtypeId;
		this.sta = sta;
	}
	public HouseFileContion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
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
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	
}
