package com.rent.condition;

public class FileCondition {

	
	private String sta;
	private String cla;
	private String name;
	private String company;
	
	private Integer begin;
	private Integer end;
	
	
	
	
	
	
	
	
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getCla() {
		return cla;
	}
	public void setCla(String cla) {
		this.cla = cla;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public FileCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileCondition(String sta, String cla, String name, String company, Integer begin, Integer end) {
		super();
		this.sta = sta;
		this.cla = cla;
		this.name = name;
		this.company = company;
		this.begin = begin;
		this.end = end;
	}
	
	
	
}
