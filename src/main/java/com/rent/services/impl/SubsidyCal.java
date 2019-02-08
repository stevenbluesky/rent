package com.rent.services.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SubsidyCal {
	
	
	private Integer perNum;//人口  
	private Double perOneArea; //人均保障面积  15
	
	
	private Double minSafeArea; //最低保障面积    30
	private Double maxSafeArea; //最高保障面积    60
	private Double maxArea;     //最大面积           90
	private Double unitPrice;   //单价
	private Double area; //面积
	private Double percent;//百分比

	
	
	public SubsidyCal() {
		super();
	}
	public SubsidyCal(Integer perNum, Double perOneArea, Double minSafeArea, Double maxSafeArea, Double maxArea,
			Double unitPrice, Double area, Double percent) {
		super();
		this.perNum = perNum;
		this.perOneArea = perOneArea;
		this.minSafeArea = minSafeArea;
		this.maxSafeArea = maxSafeArea;
		this.maxArea = maxArea;
		this.unitPrice = unitPrice;
		this.area = area;
		this.percent = percent;
	}
	//计算保障面积
	public Double calSafeArea(){
		 Double safeArea = perNum*perOneArea;
		 
		 if (safeArea<minSafeArea) {
			safeArea=minSafeArea;
		  }
		 
		 if (safeArea>maxSafeArea) {
			safeArea=maxSafeArea;
		 }
		 
		 return safeArea;
	}
	//计算保障外给补贴的面积
	public Double calOutSafeArea(){
		Double outPrice=0.0;
		Double safeArea = calSafeArea();
		if (area>maxArea) {
			outPrice= maxArea-safeArea;
		}else{
			outPrice= area-safeArea;
		}
		outPrice=outPrice<0?0:outPrice;
		return outPrice;
	}
	//计算房子总价
	public Double calTotalPrice(){
		return area*unitPrice;
	}
	//计算保障内面积补贴
	public Double calInSubsidy(){
		Double safeArea = calSafeArea();
		return safeArea*unitPrice*percent;
	}
	//计算保障外面积补贴
	public Double calOutSubsidy(){
		Double outSafeArea = calOutSafeArea();
		return outSafeArea*unitPrice*percent;
	}
	
	public Double calRealPrice(){
		Double totalPrice = calTotalPrice();
		Double inSubsidy = calInSubsidy();
		Double outSubsidy=calOutSubsidy();
		Double should= totalPrice-inSubsidy-outSubsidy;
		return should>=0?should:0;
	}
	
	
	public Integer getPerNum() {
		return perNum;
	}
	public void setPerNum(Integer perNum) {
		this.perNum = perNum;
	}
	public Double getPerOneArea() {
		return perOneArea;
	}
	public void setPerOneArea(Double perOneArea) {
		this.perOneArea = perOneArea;
	}
	public Double getMinSafeArea() {
		return minSafeArea;
	}
	public void setMinSafeArea(Double minSafeArea) {
		this.minSafeArea = minSafeArea;
	}
	public Double getMaxSafeArea() {
		return maxSafeArea;
	}
	public void setMaxSafeArea(Double maxSafeArea) {
		this.maxSafeArea = maxSafeArea;
	}
	public Double getMaxArea() {
		return maxArea;
	}
	public void setMaxArea(Double maxArea) {
		this.maxArea = maxArea;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
	
	

}
