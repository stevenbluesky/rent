package com.rent.condition;

import java.util.Date;

public class MasterCondition {
	
	public MasterCondition() {
		super();
	}
	
	

	public MasterCondition(Integer begin, Integer end, Integer estateId, String buildingNoId, String name,
			String company, String no, Date leaveDate, String sta) {
		super();
		this.begin = begin;
		this.end = end;
		this.estateId = estateId;
		this.buildingNoId = buildingNoId;
		this.name = name;
		this.company = company;
		this.no = no;
		this.leaveDate = leaveDate;
		this.sta = sta;
	}


	public MasterCondition(Integer begin, Integer end, Integer estateId, String buildingNoId, String buildingId,
			Integer typeId, Integer subsidyTypeId, Double minArea, Double maxArea) {
		super();
		this.begin = begin;
		this.end = end;
		this.estateId = estateId;
		this.buildingNoId = buildingNoId;
		this.buildingId = buildingId;
		this.typeId = typeId;
		this.subsidyTypeId = subsidyTypeId;
		this.minArea = minArea;
		this.maxArea = maxArea;
	}


	
	public MasterCondition(Integer estateId, String buildingNoId, String name, String company, 
			Date leaveDate) {
		super();
		this.estateId = estateId;
		this.buildingNoId = buildingNoId;
		this.name = name;
		this.company = company;
		this.leaveDate = leaveDate;
	}
	
	private Integer begin;
	private Integer end;
	private Integer estateId;
	private String buildingNoId;
	private String buildingId;
	private String name;
	private String company;
	private String roomNo;
	
	private String no;
	private Date leaveDate;
	private String sta;
	
	private Integer typeId;
	
	private Integer subsidyTypeId;
	private Double minArea;
	private Double maxArea;
	
	private Integer masterType;
	
	
	public Integer getMasterType() {
		return masterType;
	}



	public void setMasterType(Integer masterType) {
		this.masterType = masterType;
	}



	public Double getMinArea() {
		return minArea;
	}



	public void setMinArea(Double minArea) {
		this.minArea = minArea;
	}



	public Double getMaxArea() {
		return maxArea;
	}



	public void setMaxArea(Double maxArea) {
		this.maxArea = maxArea;
	}



	public Integer getSubsidyTypeId() {
		return subsidyTypeId;
	}



	public void setSubsidyTypeId(Integer subsidyTypeId) {
		this.subsidyTypeId = subsidyTypeId;
	}



	public Integer getTypeId() {
		return typeId;
	}



	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}



	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}



	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
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

	public String getBuildingNoId() {
		return buildingNoId;
	}
	public void setBuildingNoId(String buildingNoId) {
		this.buildingNoId = buildingNoId;
	}
	
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Integer getEstateId() {
		return estateId;
	}
	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	
	
}
