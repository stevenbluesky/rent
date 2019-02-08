package com.rent.condition;

import java.util.Date;

public class RenDaliyCondition {
	
	private Integer estateId;
	private String no;  //louhao
	private String name;
	private String company;
	private Integer accnt;
	private String sta; //首次缴费， 续租     其实是sta   状态列
	private String src;  //已结算  未结算    src 是是否缴费了    type 代表是否是续租
	private Integer begin;
	private Integer end;
	private String danyuanid;
	private Integer logmark;  //判断是否核算
	private Date today;
	public RenDaliyCondition(Integer estateId, String no, String name, String company, Integer accnt, String sta,
			String src, Integer begin, Integer end, String danyuanid, Integer logmark, Date today) {
		super();
		this.estateId = estateId;
		this.no = no;
		this.name = name;
		this.company = company;
		this.accnt = accnt;
		this.sta = sta;
		this.src = src;
		this.begin = begin;
		this.end = end;
		this.danyuanid = danyuanid;
		this.logmark = logmark;
		this.today = today;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public Integer getEstateId() {
		return estateId;
	}
	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public Integer getAccnt() {
		return accnt;
	}
	public void setAccnt(Integer accnt) {
		this.accnt = accnt;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
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
	public String getDanyuanid() {
		return danyuanid;
	}
	public void setDanyuanid(String danyuanid) {
		this.danyuanid = danyuanid;
	}
	public Integer getLogmark() {
		return logmark;
	}
	public void setLogmark(Integer logmark) {
		this.logmark = logmark;
	}
	public RenDaliyCondition(Integer estateId, String no, String name, String company, Integer accnt, String sta,
			String src, Integer begin, Integer end, String danyuanid, Integer logmark) {
		super();
		this.estateId = estateId;
		this.no = no;
		this.name = name;
		this.company = company;
		this.accnt = accnt;
		this.sta = sta;
		this.src = src;
		this.begin = begin;
		this.end = end;
		this.danyuanid = danyuanid;
		this.logmark = logmark;
	}
	public RenDaliyCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
