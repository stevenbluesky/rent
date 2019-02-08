package com.rent.condition;

public class TempCondition {

	private Integer roid;
	private Integer begin;
	private Integer end;
	
	
	
	public TempCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempCondition(Integer roid, Integer begin, Integer end) {
		super();
		this.roid = roid;
		this.begin = begin;
		this.end = end;
	}
	public Integer getRoid() {
		return roid;
	}
	public void setRoid(Integer roid) {
		this.roid = roid;
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
	
	
	
	
	
}
