package com.rent.condition;

public class HouseCondition {
	
	
	public HouseCondition() {
		super();
	}
	
	public HouseCondition(String buildingNoId, Integer floor, Integer typeId, Double minArea, Double maxArea,
			Double minPrice, Double maxPrice) {
		super();
		this.buildingNoId = buildingNoId;
		this.floor = floor;
		this.typeId = typeId;
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public HouseCondition(Integer estateId, String buildingId, String buildingNoId, Integer floor, Integer typeId,
			Double minArea, Double maxArea, Double minPrice, Double maxPrice) {
		super();
		this.estateId = estateId;
		this.buildingId = buildingId;
		this.buildingNoId = buildingNoId;
		this.floor = floor;
		this.typeId = typeId;
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	
	
	public HouseCondition(Integer estateId, String buildingId, String buildingNoId, Integer floor, Integer typeId,
			Integer begin, Integer end) {
		super();
		this.estateId = estateId;
		this.buildingId = buildingId;
		this.buildingNoId = buildingNoId;
		this.floor = floor;
		this.typeId = typeId;
		this.begin = begin;
		this.end = end;
	}

	public HouseCondition(Integer estateId, String buildingId, String buildingNoId, Integer state) {
		super();
		this.estateId = estateId;
		this.buildingId = buildingId;
		this.buildingNoId = buildingNoId;
		this.state = state;
	}
	private Integer houseNature;
	
	private String no;
	private Integer estateId; 
	private String buildingId;
	private String buildingNoId;
	private Integer floor;
	private Integer typeId;
	private Double minArea;
	private Double maxArea; 
	private Double minPrice; 
	private Double maxPrice;
	private Integer begin;
	private Integer end;
	private Integer state;
	
	
	
	

	public Integer getHouseNature() {
		return houseNature;
	}

	public void setHouseNature(Integer houseNature) {
		this.houseNature = houseNature;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public void setBuildingNoId(String buildingNoId) {
		this.buildingNoId = buildingNoId;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public void setMinArea(Double minArea) {
		this.minArea = minArea;
	}
	public void setMaxArea(Double maxArea) {
		this.maxArea = maxArea;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getEstateId() {
		return estateId;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public String getBuildingNoId() {
		return buildingNoId;
	}
	public Integer getFloor() {
		return floor;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public Double getMinArea() {
		return minArea;
	}
	public Double getMaxArea() {
		return maxArea;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	
	
}
