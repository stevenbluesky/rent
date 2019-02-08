package com.rent.modules.sys.entity;

import com.rent.common.persistence.DataEntity;

public class Tenement extends DataEntity<Repaire>{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String address;
	private String comments;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comment) {
		this.comments = comment;
	}
}
