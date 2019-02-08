package com.rent.entity;

public class NameAndId {

	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NameAndId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NameAndId(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}
