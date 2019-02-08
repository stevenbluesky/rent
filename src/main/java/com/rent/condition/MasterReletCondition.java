package com.rent.condition;

import com.rent.common.utils.MyConvertUtil;

public class MasterReletCondition {
	
	private Integer begin;
	private Integer end;
	private Integer estateId;
	private String name;
	private String roomNo;
	private String buildingNoId;
	private String buildingId;
	
	private Integer hasCommonCard;
	
 	
	public MasterReletCondition() {
		super();
	}
	
	
	public MasterReletCondition(Integer currpage, Integer size, Integer estateId, String name, String roomNo) {
		
		super();
		if (name!=null&&name.length()==0) {
			name=null;
		}
		if (roomNo!=null&&roomNo.length()==0) {
			roomNo=null;
		}
		if (currpage==null) {
			currpage=1;
		}
		this.begin= MyConvertUtil.toPagedBeginEnd(currpage, size)[0];
		this.end= MyConvertUtil.toPagedBeginEnd(currpage, size)[1];
		
		this.estateId = estateId;
		this.name = name;
		this.roomNo = roomNo;
	}
	
	public MasterReletCondition(Integer begin, Integer end, Integer estateId, String name, String roomNo,
			String buildingNoId, String buildingId) {
		super();
		this.begin = begin;
		this.end = end;
		this.estateId = estateId;
		this.name = name;
		this.roomNo = roomNo;
		this.buildingNoId = buildingNoId;
		this.buildingId = buildingId;
	}


	public Integer getHasCommonCard() {
		return hasCommonCard;
	}


	public void setHasCommonCard(Integer hasCommonCard) {
		this.hasCommonCard = hasCommonCard;
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
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getBuildingNoId() {
		return buildingNoId;
	}
	public void setBuildingNoId(String buildingNoId) {
		this.buildingNoId = buildingNoId;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	
}
