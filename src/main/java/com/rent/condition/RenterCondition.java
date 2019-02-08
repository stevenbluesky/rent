package com.rent.condition;

public class RenterCondition {

	private	Integer estateId;
	private String   name;
	private String company;
	private String   no;	//单元
	private Integer  id  ;      //编号
	private Integer end;
	private Integer begin;
	public Integer getEstateId() {
		return estateId;
	}
	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public RenterCondition(Integer estateId, String name, String company, String no, Integer id, Integer end,
			Integer begin) {
		super();
		this.estateId = estateId;
		this.name = name;
		this.company = company;
		this.no = no;
		this.id = id;
		this.end = end;
		this.begin = begin;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public RenterCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
}
