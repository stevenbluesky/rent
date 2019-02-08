package com.rent.modules.sys.entity;

import com.rent.common.persistence.DataEntity;

public class Payment extends DataEntity<Payment>{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String paymentID;
	private String project;
	private Integer count;
	private String price;
	private String repaireID;
	
	public int getSum() {
		return Integer.parseInt(this.price) * this.count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRepaireID() {
		return repaireID;
	}
	public void setRepaireID(String repaireID) {
		this.repaireID = repaireID;
	}
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
}
