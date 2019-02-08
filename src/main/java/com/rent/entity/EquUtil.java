package com.rent.entity;

public class EquUtil {

	private Integer id;
	private String name;
	private Double price;
	
	
	
	public EquUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EquUtil(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	
}
