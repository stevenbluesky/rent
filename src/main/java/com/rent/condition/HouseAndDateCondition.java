package com.rent.condition;

import java.util.Date;

public class HouseAndDateCondition {

	private Integer accnt;
	private Date date;

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public HouseAndDateCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getAccnt() {
		return accnt;
	}
	public void setAccnt(Integer accnt) {
		this.accnt = accnt;
	}
	public HouseAndDateCondition(Integer accnt, Date date) {
		super();
		this.accnt = accnt;
		this.date = date;
	}
	
	
}
