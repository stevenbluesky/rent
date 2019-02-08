package com.rent.condition;

public class RmdevCondition {

	private Integer houseid;
	private String sta;
	private Integer begin;
	private Integer end;
	public Integer getHouseid() {
		return houseid;
	}
	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
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
	public RmdevCondition(Integer houseid, String sta, Integer begin, Integer end) {
		super();
		this.houseid = houseid;
		this.sta = sta;
		this.begin = begin;
		this.end = end;
	}
	public RmdevCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
