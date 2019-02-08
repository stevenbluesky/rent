package com.rent.condition;

public class renAcountCondition {

	private	Integer estateId;
	private String   name;
	private String company;
	private String buildno;     //楼号
	private String   danyuan;	//单元
	private String roomno;   //房号
	private String account1; // 核算的时候代表 判断是否核算使用 1代表核算  2 代表为核算						//日常和同的时候代表判断是否核算使用 1代表已结算  2 代表未结算
	private String account2;
	private String sta1;  //主单表的状态   登记
	private String sta2;  //主单表的状态   签约
	private String sta3;  //主单表的状态   在租
	private String sta4;  //主单表的状态   续租申请中
	private Integer begin;
	private Integer end;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBuildno() {
		return buildno;
	}
	public void setBuildno(String buildno) {
		this.buildno = buildno;
	}
	public String getDanyuan() {
		return danyuan;
	}
	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getAccount1() {
		return account1;
	}
	public void setAccount1(String account1) {
		this.account1 = account1;
	}
	public String getAccount2() {
		return account2;
	}
	public void setAccount2(String account2) {
		this.account2 = account2;
	}
	public String getSta1() {
		return sta1;
	}
	public void setSta1(String sta1) {
		this.sta1 = sta1;
	}
	public String getSta2() {
		return sta2;
	}
	public void setSta2(String sta2) {
		this.sta2 = sta2;
	}
	public String getSta3() {
		return sta3;
	}
	public void setSta3(String sta3) {
		this.sta3 = sta3;
	}
	public String getSta4() {
		return sta4;
	}
	public void setSta4(String sta4) {
		this.sta4 = sta4;
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
	public renAcountCondition(Integer estateId, String name, String company, String buildno, String danyuan,
			String roomno, String account1, String account2, String sta1, String sta2, String sta3, String sta4,
			Integer begin, Integer end) {
		super();
		this.estateId = estateId;
		this.name = name;
		this.company = company;
		this.buildno = buildno;
		this.danyuan = danyuan;
		this.roomno = roomno;
		this.account1 = account1;
		this.account2 = account2;
		this.sta1 = sta1;
		this.sta2 = sta2;
		this.sta3 = sta3;
		this.sta4 = sta4;
		this.begin = begin;
		this.end = end;
	}
	public renAcountCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
		
}

